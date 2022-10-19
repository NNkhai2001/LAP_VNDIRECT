import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.Arrays.stream;

/*
Coder:NNKhai
Date:09/12/2022
JSE.Unit06.Thread
*/
class CallableSample implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        AtomicInteger total = new AtomicInteger(0);
        IntStream.range(0, 10).forEach(number -> {
            System.out.println(Thread.currentThread().getName() + "-object " + this + " is running " + total.addAndGet(number));
            // System.out.println(Thread.currentThread().getName() + " running " + total.addAndGet(number));
            Random random = new Random();
            LongStream longStream = random.longs(100, 1000);
            long sleep = longStream.findFirst().getAsLong();

        });
        return total.get();
    }
}

class FeatureExampleTest {
    public static void main(String[] args) {
        CallableSample callableSample = new CallableSample();
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<Integer> future = executor.submit(callableSample);
        System.out.println("Future Done?" + future.isDone());

        Integer result = null;
        try {
            result = future.get(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Future Done? " + future.isDone() + "-result= " + result);

    }
}

class InvokeAllExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newWorkStealingPool();
        List<CallableSample> callable = Arrays.asList(
                new CallableSample(), new CallableSample()
        );
        try {
            Stream<Future<Integer>> stream = executor.invokeAll(callable).stream();
            Stream<Integer> resultStream = stream.map(future -> {
                try {
                    return future.get();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
            });
            Integer[] results = resultStream.toArray(Integer[]::new);
            stream(results).forEach(System.out::println);
            try {
                System.out.println("----------->" + executor.invokeAny(callable));
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}

class LockExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newWorkStealingPool(4);
        CallableSample sample = new CallableSample();
        List<CallableSample> callables = Arrays.asList(sample, sample, sample, sample);
        try {
            executor.invokeAll(callables);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class ReadWriteLockExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        HashMap<Object, Object> map = new HashMap<>();
        ReadWriteLock lock = new ReentrantReadWriteLock();
        executor.submit(() -> {
            System.out.println("Start writing");
            lock.writeLock().lock();
            try {
                TimeUnit.SECONDS.sleep(3);
                map.put("foo", "bar");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.writeLock().unlock();
                System.out.println("end writing");
            }
        });
        Runnable readTask = () -> {
            System.out.println("Start reading");
            lock.readLock().lock();
            try {
                System.out.println(map.get("foo"));
            } finally {
                System.out.println("end reading");
                lock.readLock().unlock();
            }
        };
        executor.submit(readTask);
        executor.submit(readTask);
    }
}

class DeadLockDemo extends Thread {
    private Object lock1;
    private Object lock2;

    public DeadLockDemo(Object lock1, Object lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    public void run() {
       synchronized (lock1) {
            System.out.println(Thread.currentThread().getName() + ":Holding " + lock1 + "...");
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "------->" + lock1 + ":" + lock2 + "...");
               synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + "------->" + lock1 + ":" + lock2 + "...");
                }
            } catch (InterruptedException e) {
               throw new RuntimeException(e);
            }

        }
    }

    public static void main(String[] args) {
        Object obj1 = new Object();
        Object obj2 = new Object();
        Thread thread1 = new DeadLockDemo(obj1, obj2);
        Thread thread2 = new DeadLockDemo(obj1, obj2);
        thread1.start();
        thread2.start();
    }
}
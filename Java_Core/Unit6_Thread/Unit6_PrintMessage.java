import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/*
Coder:NNKhai
Date:09/09/2022
JSE.Unit06.Thread
*/
import static java.util.Arrays.stream;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Unit6_PrintMessage implements Runnable {
    private String message;

    public Unit6_PrintMessage(String message) {
        this.message = message;
    }

    public synchronized void run() {
        String[] elements = message.split(" ");
        stream(elements).forEach(ele -> {
            try {
                Thread.sleep((int) (Math.random() * 3) * 1000);
                System.out.println(Thread.currentThread().getName() + " print " + ele);
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {

            }
        });
    }

    public static void main(String[] args) {
        // PrintMessage message = new PrintMessage("Say hello to everyone");
        new Thread(new Unit6_PrintMessage("say hello to everyone")).start();
    }
}

class PrintNumber implements Runnable {

    // private int number = 1;
    private boolean alive = true;

    private Integer number = new Integer(1);

    public int getNumber() {
        return number;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public  void run() {
        Thread current = Thread.currentThread();
        synchronized (number) {
//        while (alive) {
//            try {
//                number++;
//                System.out.println(current.getName() + "number is \"" + number + "\"");
//                Thread.sleep(800);
//            } catch (InterruptedException ex) {
//
//            }
//        }
            while (number < 30) {
                number++;
                System.out.println(current.getName() + "number is \"" + number + "\"");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
//       while (true) {
//           number++;
//           System.out.println(current.getName() + "number is \"" + number + "\"");
//           try {
//               Thread.sleep(300);
//           } catch (InterruptedException e) {
//               throw new RuntimeException(e);
//           }
//           if(number %10 ==0) break;
//       }
        }
        System.out.println(current.getName() + "is stoped!");
    }

    public static void main(String[] args) {
        PrintNumber number = new PrintNumber();
        Thread thread1 = new Thread(number);
        thread1.setName("Fsoft_Thread 1 ");
        thread1.start();
        Thread thread2 = new Thread(number);
        thread2.setName("Fsoft_Thread 2 ");
        thread2.start();
//        try {
//            Thread.currentThread().join();
//            while (thread.isAlive()) {
//                if(number.getNumber()%10 ==0) number.setAlive(false);
//                TimeUnit.SECONDS.sleep(1);
//            }
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

//        while (thread.isAlive()) {
//            if (number.getNumber() % 10 == 0)
//                number.setAlive(false);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException ex) {
//
//            }
//        }
//        PrintNumber number = new PrintNumber();
//        Callable<Object> value = Executors.callable(number);
//        System.out.println("Main say Hello");
//        try {
//            value.call();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("Main say goobye!");
    }

}

class ThreadNumberTest {
    public static void main(String[] args) {
        PrintNumber number = new PrintNumber();
        Thread thread = new Thread(number);
        thread.setName("Fsoft_Thread");
        thread.start();
        // thread.setDaemon(true);
        System.out.println("Main thread say hello");
        System.out.println("Main Thread say goodbye");
        while (thread.isAlive()) {
            if (number.getNumber() % 10 == 0)
                number.setAlive(false);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {

            }
        }
    }
}

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Unit6_TestThreadPool {
    public static void main(String[] args) {
        int corePoolSize = 5;
        int maximumPoolSize = 10;
        long keepAliveTime = 500;
        TimeUnit unit = TimeUnit.SECONDS;

        ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(100);

        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize, keepAliveTime, unit, workQueue, handler);
//        for (int i = 0; i < 10; i++) {
//            threadPoolExecutor.execute(new Run(i));
//        }
    }
}

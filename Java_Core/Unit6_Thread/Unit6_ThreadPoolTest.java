import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/*
Coder:NNKhai
Date:09/09/2022
JSE.Unit06.Thread
*/
public  class Unit6_ThreadPoolTest {
    public static void main(String[] args) {
        PrintNumber number = new PrintNumber();
        ExecutorService executor =  Executors.newFixedThreadPool(3);
        IntStream.range(0,6).forEach(i ->executor.submit(number));
    }
}

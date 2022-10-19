
import java.util.Arrays;
import static java.util.Arrays.stream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/*
Coder:NNKhai
Date:09/09/2022
JSE.Unit06.Thread
*/

class SimpleThreadSample {

    public static void main(String[] args) {
        new Thread(
                () -> {
                    // stream(args).forEach(ele -> System.out.println(ele));
                    // }
                    // ).start();
                    Arrays.stream(args).forEach(ele -> {
                        try {
                            Thread.sleep(1000);
                            System.out.println(ele);
                        } catch (InterruptedException ex) {

                        }
                    });
                }).start();
    }
}


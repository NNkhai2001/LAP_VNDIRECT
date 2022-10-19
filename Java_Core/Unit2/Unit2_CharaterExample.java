
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Unit2_CharaterExample {

    // private static int countDigit(String value) {
    //     int i = 0;
    //     int counter = 0;
    //     while (i < value.length()) {
    //         char c = value.charAt(i);
    //         if (Character.isWhitespace(c)) {
    //             counter++;
    //         }
    //         i++;
    //     }
    //     return counter;
    // }

    // public static int countDigit1(String value) {
    //     AtomicInteger counter = new AtomicInteger(0);
    //     IntStream stream = value.chars();
    //     stream.forEach(c -> {
    //         if (Character.isDigit(c)) {
    //             counter.incrementAndGet();
    //         }
    //     });
    //     return counter.get();
    // }

    static int toNumber(String value) {
        try {
            Integer integer = Integer.parseInt(value);
            return integer.intValue();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return -1;
    }
    // 

    public static void main(String[] args) {
        // countDigit
        // System.out.println("There are " + countDigit(args[0])
        // + " isWhitespace in the text.");
        // countDigit1
        //System.out.println(countDigit1(args[0]));
        // toNumber
        int number = toNumber("34");
        System.out.println("Number = " + number);
        number = toNumber("as");
        System.out.println("Number = " + number);
    }
}

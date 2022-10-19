import java.util.Arrays;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

class SortArrayExample {
    public static void main(String[] args) {
        Integer[] values = { 2, 4, 7, 1, 3, 5, 9, 11, 3 };

        Arrays.sort(values, (Integer o1, Integer o2) -> {
            return o2 - o1;
        });
        Stream<Integer> stream = Arrays.stream(values);
        /*
         * C1:
         * stream.forEach(value -> System.out.println(value));
         */
        /*
         * C2
         * Consumer<Integer> c = (Integer value) -> {
         * System.out.println(value);
         * };
         * stream.forEach(c);
         */

        // ArrayStreamExample2
        /*
         * Gia tri max trong value
         * Optional<Integer> max = stream.max((Integer o1, Integer o2) -> {
         * return o1 - o2;
         * });
         * System.out.println("Gia tri lon nhat= " + max.get());
         * stream = Arrays.stream(values);
         */
        /*
         * Gia tri trong values >5
         * Predicate<Integer> predicate = (Integer value) -> {return value >5;};
         * Stream<Integer> older = stream.filter(predicate);
         * older.forEach((Integer value) -> { System.out.println(value);});
         */
        Predicate<Integer> predicate = (Integer value) -> {
            return value > 5;
        };
        Stream<Integer> older = stream.filter(predicate);
        older.forEach((Integer value) -> {
            System.out.println(value);
        });
    }
}

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Unit9_Lambda_FunctionTest {
    public static void main(String[] args) {
        /*C1
        List students = new ArrayList<>();
        Consumer<Unit9_Lambda_Student> c = (Unit9_Lambda_Student student)
        ->{
        if(student.getAge() >23) student.setAge(23);
        students.add(student);
        };
        c.accept(new Unit9_Lambda_Student(34, "Nguyen A"));
        System.out.println(students.get(0));

         */
        String[] names = {"Tran Van A", "Nguyen Thi B", "Nguyen Thi C", "Ta Van C"};
        int[] ages = {23, 45, 12, 67};
        IntStream intStream = IntStream.rangeClosed(0, names.length - 1);
        Stream stream = intStream.mapToObj(value -> new Unit9_Lambda_Student(ages[value], names[value]));
        Consumer<Unit9_Lambda_Student> c = (Unit9_Lambda_Student student) -> {
            System.out.println(student);
        };
        //stream.forEach(c);

        Function<Unit9_Lambda_Student, String> jsonToFunction = (Unit9_Lambda_Student student) -> {
           StringBuilder builder = new StringBuilder();
           builder.append("Student{\n");
           builder.append("\tid: ").append(student.getId()).append('\n');
           builder.append("\tname: ").append(student.getName()).append('\n');
           builder.append("\tage: ").append(student.getAge()).append('\n');
           builder.append("}");
           return  builder.toString();

        };
        c = (Unit9_Lambda_Student student) -> {
            System.out.println(jsonToFunction.apply(student));
        };
        //stream.forEach(c);
        Predicate<Unit9_Lambda_Student> predicate = (Unit9_Lambda_Student student) ->{
          return student.getAge() >30;
        };
        Stream<Unit9_Lambda_Student> older = stream.filter(predicate);
        older.forEach(c);

        Supplier<Unit9_Lambda_Student> supplier = () ->
        {return new Unit9_Lambda_Student(27,"Tran Thi A");};
        System.out.println(jsonToFunction.apply(supplier.get()));
    }
}
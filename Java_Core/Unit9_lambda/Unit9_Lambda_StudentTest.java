b import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Filter;
import java.util.stream.Stream;

public class Unit9_Lambda_StudentTest {
    public static List<Unit9_Lambda_Student> filter(List<Unit9_Lambda_Student> students, Unit9_Lambda_Student.Filter<Unit9_Lambda_Student> pred) {
        List list = new ArrayList();
        for (Unit9_Lambda_Student student : students
        ) {
            if (pred.valid(student)) list.add(student);

        }
        return list;
    }

    public static void main(String[] args) {
        List<Unit9_Lambda_Student> students = new ArrayList<Unit9_Lambda_Student>();
        students.add(new Unit9_Lambda_Student(23, "Tran Van A"));
        students.add(new Unit9_Lambda_Student(34, "Tran Thi B"));
        students.add(new Unit9_Lambda_Student(15, "Tran Thi C"));
        students.add(new Unit9_Lambda_Student(46, "Ta Van C"));
        /*C1
        Unit9_Lambda_Student.Filter<Unit9_Lambda_Student> older =
                student -> student.getAge() >=30;
        List<Unit9_Lambda_Student> filtered = filter(students, older);
        for (Unit9_Lambda_Student student: filtered
             ) {
            System.out.println(student);
        }
    filtered.forEach(student -> System.out.println(student));
         */
        /*C2
        Stream<Unit9_Lambda_Student> stream = students.stream().filter(student ->student.getAge()>=30);
        stream.forEach( student -> System.out.println(student) );

         */
        /*C3
        Collections.sort(students,
                (Unit9_Lambda_Student student1,Unit9_Lambda_Student student2)
    -> student2.getAge() - student1.getAge());
        students.forEach(student -> System.out.println(student));

         */
        /*C4
        Stream<Unit9_Lambda_Student> stream = students.stream().sorted(
                (student1,student2) -> student2.getAge() - student1.getAge()
        );
       stream.forEach(student -> System.out.println(student));

         */
        Comparator<Unit9_Lambda_Student> comparator = (student1 , student2) ->student2.getAge() - student1.getAge();
        Stream<Unit9_Lambda_Student> stream = students.stream().sorted(comparator);
        stream.forEach(student -> System.out.println(student));

        System.out.println("\nMax is: "+students.stream().max(comparator));
        int sum = students.stream().mapToInt(Unit9_Lambda_Student::getAge).sum();
        System.out.println("\nAverage of age is: "+sum/students.size());
        students.parallelStream().forEach((it) -> System.out.println(
                Thread.currentThread().getName()+" Hello "+it.getName()));
    }
}
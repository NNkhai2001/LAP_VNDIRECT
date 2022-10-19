import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.IntSummaryStatistics;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//vector
class VectorExample {
    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<Integer>();
        vector.add(1);
        vector.add(14);
        vector.add(8);
        vector.add(3);
        vector.add(12);

        System.out.println("Size of vector: " + vector.size());
        System.out.println("Element at 2: " + vector.get(2));
    }
}

// ArrayListExample
class ArrayListExample {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        Collections.addAll(list, args);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Element at " + i + " is " + list.get(i));
        }
        // IntStream.range(0, list.size()).forEach(i -> {
        // System.out.println("Element at "+i+" is "+list.get(i));
        // });
    }
}

class LinkedListExample {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<String>();
        for (String element : args) {
            list.add(element);
        }
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println("===>" + iterator.next());
        }
    }
}

class LinkedListExample2 {
    public static void main(String[] args) {
        List list = new LinkedList<>(Arrays.asList(args));
        list.forEach(value -> {
            System.out.println(value);
        });
    }
}

class SetExample {
    public static void main(String[] args) {
        Set<Integer> numbers = new TreeSet<Integer>();
        numbers.add(1);
        numbers.add(14);
        numbers.add(8);
        numbers.add(3);
        numbers.add(12);
        numbers.add(8);
        System.out.println("Size of set: " + numbers.size());
        for (Integer integer : numbers) {
            System.out.println(integer);
        }
    }
}

class MapExample {
    public static void main(String[] args) {
        Map<Integer, String> map = new Hashtable<Integer, String>();
        map.put(3, "Nguyen Van A");
        map.put(1, "Nguyen Van B");
        map.put(2, "Nguyen Van C");
        map.put(4, "Nguyen Van X");

        System.out.println(map.get(3));
        map.put(3, "Nguyen Thi A");
        System.out.println(map.get(3));
    }
}

class TreeMapExample {
    public static void main(String[] args) {
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(3, "Nguyen Van A");
        map.put(1, "Nguyen Van B");
        map.put(2, "Nguyen Van C");
        map.put(4, "Nguyen Van X");

        Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}

class CollectionsExample1 {
    public static void main(String[] args) {
        Short[] values = { 1, 2, 4, 5, 6, 7, 8, 9 };
        List<Short> list = new ArrayList<Short>();
        Collections.addAll(list, values);
        Collections.reverse(list);

        list.toArray(values);
        for (int i = 0; i < values.length; i++) {
            System.out.print(values[i] + ",");
        }

    }
}

class CollectionsExample2 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        Collections.addAll(list, "Tu", "An", "Hoa", "Binh");
        // List<Integer> list = new ArrayList<Integer>();
        // Collections.addAll(list, 1, 5, 6, 8, 2, 4);
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + ",");
        }
        System.out.println();
        System.out.println("Vi tri thu " + Collections.binarySearch(list, "An"));
    }
}

class CollectionOperation {
    public static void main(String[] args) {
        ArrayList<Integer> listOfIntegers = new ArrayList<Integer>(Arrays.asList(4, 7, 1, 3, 8, 9, 2, 6, 10));
        Comparator<Integer> comparator = Integer::compare;
        Collections.sort(listOfIntegers, comparator);

        listOfIntegers.stream().filter(v -> v > 5).forEach(v -> {
            System.out.println(v);
        });
        Collector<Integer, ?, IntSummaryStatistics> collector = Collectors.summarizingInt(Integer::intValue);
        IntSummaryStatistics summaryStatistics = listOfIntegers.stream().collect(collector);
        System.out.println("Total= " + summaryStatistics.getSum());
        System.out.println("Overage = " + summaryStatistics.getAverage());
        Consumer<Integer> myConsumer = n -> {
            System.out.println("User input value= " + n);
            if (n < 5) {
                System.out.println("Invalid value!");
                return;
            }
            listOfIntegers.add(n);
            System.out.println("values: ");
            listOfIntegers.forEach(x -> System.out.print(x + "-"));
        };
        // myConsumer.accept(15);
        Predicate<Integer> tester = v -> v > 5;
        Consumer<Integer> mycConsumer = n -> listOfIntegers.add(n);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Please input a number:");
            int value = scanner.nextInt();
            if (value < 0)
                break;
            if (tester.test(value))
                myConsumer.accept(value);
            // myConsumer.accept(value);
            System.out.println();
        }

    }
}

class ConcurrenctTest {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Collections.addAll(list, 1, 3, 4, 2, 3, 6, 3, 3, 8);
        System.out.println("Before remove: Size of list = " + list.size());
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == 3)
                list.remove(i);
        }
        System.out.println("After remove: Size of list= " + list.size());
    }
}

class ConcurrenctTest2 {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Collections.addAll(list, 1, 3, 4, 2, 3, 6, 3, 3, 8);
        System.out.println("Before remove: Size of list= " + list.size());
        list.forEach(v -> {
            if (v == 3)
                list.remove(v);
        });
        System.out.println("After remove:Size of list= " + list.size());
    }
}

class ConcurrenctTest3 {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Collections.addAll(list, 1, 3, 4, 2, 3, 6, 3, 3, 8);
        System.out.println("Before remove: Size of list= " + list.size());
        //remove with iterator
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == 3)
            iterator.remove();
        }
        //remove with remove if
        list.removeIf(item -> {
            return item ==3;
        });
        //remove with singleton
        list.removeAll(Collections.singleton(3));
        System.out.println("After remove:Size of list= " + list.size());
    }
}
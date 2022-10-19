package Unit10_Collection2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class SynchronizedExample {
    static void addData(List<Integer> list) {
         IntStream.range(0,1000).forEach(index -> {
            try{
                list.add(index);
            }catch (Exception exp) {
                System.err.println(exp.toString());
            }
        });

    }

    public static void main(String[] args) {
        ArrayList<List<Integer>> values = new ArrayList<List<Integer>>();
        for (int i = 0; i < 10; i++) {
            List<Integer> list = new ArrayList<>();
            new Thread(() -> addData(list)).start();
            new Thread(() -> addData(list)).start();
            values.add(list);
        }
        try {
            Thread.sleep(20000);
            values.forEach(list -> System.out.println("Number items of list --->"+list.size()));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
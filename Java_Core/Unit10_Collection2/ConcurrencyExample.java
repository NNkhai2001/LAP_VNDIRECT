package Unit10_Collection2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class ConcurrencyExample {
    public static void addData(List list) {
        while (true) {
            int random = (int)(Math.random()*100);
            list.add(random);
            System.out.println("Add new data: "+random);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
    static void  printData(List list) {
        while (true) {
            try {
                System.out.println("===============");
                list.forEach(value -> System.out.println("value= "+value));
                Thread.sleep(80);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);

            }

        }
    }

    public static void main(String[] args) {
        List list = new ArrayList();
        new Thread(() -> addData(list)).start();
        new Thread(() -> printData(list)).start();
    }

}
package Unit11_Advanced;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
class Totalcalculator {
    int TOTAL = 3;
    @Test(34)
    long getTotal(short value) {return TOTAL+value;}
    @Test(100)
    private  void print(short value) {
        System.out.println("gia tri them vao la "+value);
    }
}
class AnotationTest {
    public static void main(String[] args) {
         Method[] methods = Totalcalculator.class.getDeclaredMethods();
        Totalcalculator obj = new Totalcalculator();
        Arrays.stream(methods).forEach(method -> {
            Test test = method.getAnnotation(Test.class);
            if(test == null) return;
            System.out.println("test method "+method.getName());
            try {
                System.out.println(method.invoke(obj,new Object[]{test.value()}));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        });

    }
}


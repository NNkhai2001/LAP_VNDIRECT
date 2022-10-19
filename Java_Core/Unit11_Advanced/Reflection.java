package Unit11_Advanced;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.logging.MemoryHandler;

class TotalCalculator {
    private  static int TOTAL = 3;

    private int getTOTAL(int value) {
        return TOTAL + value;
    }
}

class ReflectionTest {

    static void explore(Object obj) {
        Class<?> clazz = obj.getClass();
        System.out.println("clazz name:" + clazz.getName());
        ///*   Reflection Example : Field Accessible
        try {
            Field field = clazz.getDeclaredField("TOTAL");
            field.setAccessible(true);
            System.out.println("Total value is " + field.get(obj));
            field.set(obj, 25);
            System.out.println("total value is 2 " + field.get(obj));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // */
         /* Reflection Example - Method Accessible
        try {
            Method method  = clazz.getDeclaredMethod("getTOTAL", new Class[]{int.class});
            method.setAccessible(true);
            System.out.println("method return value= " + method.invoke(obj, new Object[]{5}));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

          */
        /* Reflection Example: Final Field
        try {
            Field field = clazz.getDeclaredField("TOTAL");
            field.setAccessible(true);
            System.out.println("Total value is " + field.get(obj));
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            field.setInt(obj, 23);
            System.out.println("modified value of the total field is " + field.get(obj));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

         */
    }
    public static void main(String[] args) {
        try {
            TotalCalculator obj = TotalCalculator.class.newInstance();
            explore(obj);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}


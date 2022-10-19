package Unit11_Advanced;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

class TotalCalculator1 {
    int TOTAL = 3;
    public long getTotal(short value) {
        return TOTAL + value;
    }
    static String calculate(){
        return "OK";
    }
}
class MethodHandlerExample {
    public static void main(String[] args) throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle mh = lookup.findVirtual(Unit11_Advanced.TotalCalculator1.class, "getTotal",
                MethodType.methodType(long.class, short.class));
        Unit11_Advanced.TotalCalculator1 obj = Unit11_Advanced.TotalCalculator1.class.newInstance();
        System.out.println(mh.invoke(obj,(short)23));
        Class<?> clazz = obj.getClass();
        MethodHandle mh2 = lookup.findStatic(clazz,"calculate",MethodType.methodType(String.class));
        System.out.println((String)mh2.invokeExact());
//        obj = (TotalCalculator1) new Object();
//        mh = lookup.findSetter(clazz,"TOTAL",int.class);
//        mh.invoke(obj,15);
//        mh = lookup.findVirtual(Unit11_Advanced.TotalCalculator1.class,"getTotal",MethodType.methodType(long.class,short.class));
//        System.out.println(mh.invoke(obj,(short)23));
        mh = MethodHandles.insertArguments(mh,1,(short)10);
        System.out.println("Total = %d" +mh.invoke(obj));

    }
}
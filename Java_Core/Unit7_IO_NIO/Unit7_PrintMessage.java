import java.io.*;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.TimeUnit;

import static java.util.Arrays.stream;

public class Unit7_PrintMessage implements Runnable, Serializable {
    private String message;
  public Unit7_PrintMessage(String message) {
        this.message = message;
    }
    public void run() {
        String[] elements = message.split(" ");
        stream(elements).forEach(ele -> {
            System.out.println(ele);
        });
    }
}

class ObjectWriterExample {
    public static void main(String[] args) throws Exception {
        File folder = new File("C:\\Temp");
        ObjectOutputStream output = null;
        FileOutputStream fileOutput = new FileOutputStream(new File(folder, "my_object.txt"));
        output = new ObjectOutputStream(fileOutput);
        //Unit7_PrintMessage PriUnit7PrintMessage = new Unit7_PrintMessage("");
        output.writeObject(new Unit7_PrintMessage("Can Noi Noi"));
        output.close();
       // new Unit7_PrintMessage("dasdasd sdsad sadasd as asd as");
    }
}
class  ObjectReaderExample {
    public static void main(String[] args) throws Exception{
        File folder = new File("C:\\Temp");
        ObjectInputStream inputStream = null;
        inputStream = new ObjectInputStream(
                new FileInputStream(new File(folder, "my_object"))
        );
        Object obj = inputStream.readObject();
        Method method = obj.getClass().getMethod("run",new Class[0]);
        method.invoke(obj, new Object[0]);
        inputStream.close();
    }
}

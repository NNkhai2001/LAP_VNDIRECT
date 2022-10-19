import java.awt.*;
import java.io.File;
import java.io.RandomAccessFile;

class Unit7_RandomAccessExample {
    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Temp\\hanoijava.txt");
        try (RandomAccessFile randomAccess = new RandomAccessFile(file, "rw")) {
            randomAccess.seek(5);
            byte[] bytes = new byte[4*102];
            int read = randomAccess.read(bytes);
            System.out.println(new String(bytes,0,read,"utf8"));
            randomAccess.seek(5);
            randomAccess.write("\r\n".getBytes());
            randomAccess.writeChars("123");
            Desktop.getDesktop().open(file);
        }
    }
}
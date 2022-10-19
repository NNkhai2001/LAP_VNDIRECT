import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class ReaderWriterExample {
    public static void main(String[] args) {
        File file = new File("C:\\Temp\\hanoijava.txt");
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write("\r\n");
            writer.write("Tran Thi B");

            FileReader reader = null;
            reader = new FileReader(file);
            char[] buffer = new char[4 * 1024];
            int read = -1;
            StringBuilder builder = new StringBuilder();
            while ((read = reader.read(buffer)) != -1) {
                builder.append(buffer, 0, read);
            }
            System.out.println(builder);
            Desktop.getDesktop().open(file);
           // writer.close();
           // reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
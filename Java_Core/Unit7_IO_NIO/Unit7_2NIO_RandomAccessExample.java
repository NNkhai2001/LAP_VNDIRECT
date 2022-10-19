import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

class RandomAccessFileExample {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("C:\\Temp\\hanoijava.txt");
        ByteBuffer buffer = ByteBuffer.allocate(10);
        try (FileChannel fc = FileChannel.open(path, StandardOpenOption.READ,
                StandardOpenOption.WRITE)) {
            fc.read(buffer);
            System.out.println(new String(buffer.array()));
            fc.position(20);
            String s = "zzz";
            byte[] bytes = s.getBytes();
            fc.write(ByteBuffer.wrap(bytes));


        }

    }
}
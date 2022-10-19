import java.awt.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

class ReadFileNIOExample {
    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Temp\\hanoijava.txt");
        FileInputStream fileInputStream = null;
        FileChannel fileChannel = null;
        try {
            fileInputStream = new FileInputStream(file);
            fileChannel = fileInputStream.getChannel();
            long size = fileChannel.size();
            ByteBuffer buffer = ByteBuffer.allocate((int) size);
            fileChannel.read(buffer);
            buffer.rewind();
            System.out.println(new String(buffer.array(), "utf8"));
        } finally {
            if (fileChannel != null) fileChannel.close();
            if (fileInputStream != null) fileInputStream.hashCode();
        }
    }
}

class LockFileNIOExample {
    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Temp\\hanoijava.txt");
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        FileChannel channel = raf.getChannel();
        FileLock lock = channel.tryLock(0, Long.MAX_VALUE, false);
        Desktop.getDesktop().edit(file);
        Thread.sleep(1000);
        lock.release();
    }
}

class CopyFileNIOExample {
    public static void main(String[] args) throws Exception {
        File sourceFile = new File("C:\\Temp\\hanoijava.txt");
        File desFile = new File("C:\\Temp\\hanoijava2.txt");
        FileChannel srcChannel = null;
        FileChannel desChannel = null;

        srcChannel = new FileInputStream(sourceFile).getChannel();
        desChannel = new FileOutputStream(desFile).getChannel();
        srcChannel.transferTo(0, srcChannel.size(), desChannel);

        Desktop.getDesktop().open(sourceFile.getParentFile());
    }
}

class SearchFileNIOExample {
    public static void main(String[] args) {


        Charset charset = Charset.forName("utf8");
        Pattern pattern = Pattern.compile("T", Pattern.CASE_INSENSITIVE);
        File file = new File("C:\\Temp\\hanoijava.txt");

        {
            try {
                FileInputStream stream = new FileInputStream(file);
                FileChannel channel = stream.getChannel();
                ByteBuffer bytes = channel.map(
                        FileChannel.MapMode.READ_ONLY, 0, channel.size());
                CharBuffer chars = charset.decode(bytes);

                Matcher matcher = pattern.matcher(chars);
                if (matcher.find()) {
                    System.out.println("Found at " + Integer.toString(matcher.start()));
                    System.out.println("Value " + chars.subSequence(matcher.start(), matcher.end()) + "");
                } else {
                    System.out.println("Not found");
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
import java.io.File;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

/*
Coder:NNKhai
Date:09/12/2022
JSE.Unit07.IO
*/
public class Unit7_FileExample {
    private static long getSize(File file) {
        if (file.isFile()) return file.length();
//        File[] files = file.listFiles();
//        int length = 0;
//        for (int i = 0; i < files.length; i++) {
//            if (files[i].isDirectory()) {
//                length +=getSize(files[i]);
//
//                continue;
//            }
//            length +=files[i].length();
//
//        }
//        return  length;
         AtomicLong length = new AtomicLong(0);
        Arrays.stream(file.listFiles()).forEach(f ->{
            length.getAndSet(length.longValue() + (f.isDirectory()? getSize(f): f.length()));
        });
        return length.longValue();
    }

    public static void main(String[] args) {
        File file = new File("C:\\Temp");
//        if(file.isFile()) {
//            System.out.println("This is file");
//        } else {
//            System.out.println("This is folder");
//        }
//        System.out.println("This is" + (file.isFile() ? " file" : " folder") + "!");
//        File[] files = file.listFiles();
//        for (File f : files
//        ) {
//            System.out.println(f.getName() + ":" + f.length() + " bytes");
//        }
        System.out.println(getSize(file));
        System.out.println("Size: "+(double)getSize(file)/(1024*1024)+ " MB");
    }
}


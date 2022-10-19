import java.io.*;

/*
Coder:NNKhai
Date:09/12/2022
JSE.Unit07.IO
*/
public class Unit7_FileOutputExample {
    public static void main(String[] args) {
        String file = "C:" + File.separator + "Temp" + File.separator + "hanoijava.txt";
                    //OutputStream
//        FileOutputStream outputStream = null;
//        try {
//            outputStream = new FileOutputStream(file);
//            byte[] bytes = "Hello Ngoc Khai ".getBytes();
//            outputStream.write(bytes);
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } finally {
//            if (outputStream != null) {
//                try {
//                    outputStream.close();
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }
            //InputStream
        try(FileInputStream inputStream = new FileInputStream(file)) {
            byte[] bytes = new byte[4*1024];
            int read = -1;
            StringBuilder builder = new StringBuilder();
            while ((read = inputStream.read(bytes)) != -1) {
                builder.append(new String(bytes,0,read));
            }
            System.out.println(builder);
//            inputStream.read(bytes);
//            System.out.println(new String(bytes));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

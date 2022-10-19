import java.io.UnsupportedEncodingException;

/*
Coder:NNKhai
Date:09/12/2022
JSE.Unit07.IO
*/
class Decoding {
    public static void main(String[] args) throws UnsupportedEncodingException {
        int [] values = {120,105,110,32,99,104,-61,-96,111};
        byte[] bytes = new byte[9];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) values[i];
        }
        System.out.println(new String(bytes,"utf8"));
    }
}
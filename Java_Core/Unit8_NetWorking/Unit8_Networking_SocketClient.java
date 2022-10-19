import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

class SocketClientExample {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 9245);
        System.out.println("CLIENT start sending...");
        try (DataInputStream inputStream = new DataInputStream(socket.getInputStream());
             DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream()
        )) {
            outputStream.writeUTF("Hello Server");
            System.out.println("Server say: " + inputStream.readUTF());
        } finally {
            socket.close();
        }
    }

}
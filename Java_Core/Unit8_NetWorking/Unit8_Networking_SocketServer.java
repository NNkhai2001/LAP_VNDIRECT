import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class SocketServerExample {
    public SocketServerExample(int serverPort) {
        try {

            ServerSocket serverSocket = new ServerSocket(serverPort);
            System.out.println("Server Listening...");
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(() -> {
                    try (DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                         DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream())
                    ) {
                        System.out.println("Client say: " + inputStream.readUTF());
                        outputStream.writeUTF("I'm a socket server!");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
    new SocketServerExample(9245);

    }
}
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;

class DatagramServerExample implements Runnable {
    DatagramSocket socket = null;
    public DatagramServerExample() {
        try {
            socket = new DatagramSocket(4445);
            System.out.println("DATA SERVER listening...");
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }
    public void run() {
        DatagramPacket packet = null;
        try {
            byte[] bytes = new byte[1024];
            packet = new DatagramPacket(bytes, bytes.length);
            socket.receive(packet);
            System.out.println("From Client: " + new String(packet.getData(), 0, packet.getLength()));
            bytes = "Server say hello".getBytes();
            socket.send(new DatagramPacket(bytes, bytes.length, packet.getAddress(), packet.getPort()));


        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            socket.close();
        }
    }

    public static void main(String[] args) {
       new Thread(new DatagramServerExample()).start();
    }
}
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Unit8_Networking_Interface extends Remote {
    String say(String name) throws RemoteException;
}
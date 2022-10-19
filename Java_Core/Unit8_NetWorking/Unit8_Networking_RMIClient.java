import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

 public class Unit8_Networking_RMIClient {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registy = LocateRegistry.getRegistry("localhost", 8000);
        Unit8_Networking_Interface stub = (Unit8_Networking_Interface) registy.lookup("Unit8_Networking_Interface");
        System.out.println("response: " + stub.say(args[0]));
    }
}
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
public class Unit8_Networking_RMIServer implements  Unit8_Networking_Interface {
    @Override
    public  String say(String name) throws RemoteException {
        return "Hello: "+name;
    }
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        System.setProperty("java.rmi.server.hostname", "127.0.0.1");
        Unit8_Networking_RMIServer obj = new Unit8_Networking_RMIServer();
        Unit8_Networking_Interface stub = (Unit8_Networking_Interface) UnicastRemoteObject.exportObject(obj,0);
        Registry registy = LocateRegistry.getRegistry("127.0.0.1", 8000);
        registy.bind("Unit8_Networking_RMIServer",stub);
        System.out.println("Server ready");
    }
}


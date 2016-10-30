package ie.gmit.rmiModels;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server {
	
public static void main(String[] args) throws Exception {
		
		StringService service = new StringServiceImpl();//create new resultator instance 
		LocateRegistry.createRegistry(1099);
		Naming.rebind("stringService", service);
	
		System.out.println("Server ready.");
	}
	
}

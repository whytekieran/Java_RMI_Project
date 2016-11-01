package ie.gmit.sw;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import ie.gmit.rmiModels.StringService;
import ie.gmit.rmiModels.StringServiceImpl;

//The RMI server
public class Servant {
	
//Main method on RMI server side
public static void main(String[] args) throws Exception {
		
		//Create instance of string server implementation, of string service type. Program to abstraction. ;)
		StringService service = new StringServiceImpl();//create new resultator instance 
		LocateRegistry.createRegistry(1099);			//Setup the registry
		Naming.rebind("stringService", service);		//Bind the service to this name...this is how we find it.
	
		System.out.println("Server ready.");			//Nice little "all good" message :)
	}
	
}

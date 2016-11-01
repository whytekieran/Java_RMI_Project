package ie.gmit.rmiModels;

import java.rmi.Remote;
import java.rmi.RemoteException;

//Remote interface for the String service. 
//The remote interface exposes the public service methods that may be invoked by a remote object.
public interface StringService extends Remote {
	public Resultator compare(String s, String t, String algo) throws RemoteException; 
}

package ie.gmit.rmiModels;

import java.rmi.Remote;
import java.rmi.RemoteException;

//Resultator interface with getters and setters for the result and the processed status.
public interface Resultator extends Remote{
	public String getResult() throws RemoteException; 
	public void setResult(String result) throws RemoteException; 
	public boolean isProcessed() throws RemoteException; 
	public void setProcessed() throws RemoteException; 
}

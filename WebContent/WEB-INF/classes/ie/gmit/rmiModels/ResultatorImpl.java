package ie.gmit.rmiModels;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

//Class implementing resultator. Constructor just sets result and processed to default values.
public class ResultatorImpl extends UnicastRemoteObject implements Resultator {
	
	//Instance variables
	private static final long serialVersionUID = 1L;
	private String result;
	private boolean processed = false;
	
	//Constructor
	public ResultatorImpl() throws RemoteException{
		this.result = null;
		this.processed = false;
	}
	
	//Getters and Setters
	@Override
	public String getResult() throws RemoteException {
		return this.result;
	}

	@Override
	public void setResult(String result) throws RemoteException {
		this.result = result;
	}

	@Override
	public boolean isProcessed() throws RemoteException {
		return this.processed;
	}

	@Override
	public void setProcessed() throws RemoteException {
		this.processed = true;
	}	
}

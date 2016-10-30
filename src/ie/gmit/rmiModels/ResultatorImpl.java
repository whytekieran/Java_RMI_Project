package ie.gmit.rmiModels;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ResultatorImpl extends UnicastRemoteObject implements Resultator {
	
	private static final long serialVersionUID = 1L;
	private String result;
	private boolean processed = false;
	
	public ResultatorImpl() throws RemoteException{
		this.result = null;
		this.processed = false;
	}
	
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

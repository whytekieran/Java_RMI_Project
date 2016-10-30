package ie.gmit.rmiModels;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

//may possibly need to implement runnable here
public class StringServiceImpl extends UnicastRemoteObject implements StringService {

	private Resultator r;
	
	private static final long serialVersionUID = 1L;

	public StringServiceImpl() throws RemoteException{
	}

	@Override
	public Resultator compare(String s, String t, String algo) throws RemoteException {
		r = new ResultatorImpl(); //using this line and empty constructor above we create new instance each time.
		
		//UNFINISHED
		//StringCompareRunnable job = new StringCompareRunnable(s, t, r, algo);
		//Thread worker = new Thread(job);
		//worker.start();
		
		/*try {
			Thread.sleep(3000);
			r.setProcessed();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
			
		return r;
	}
	
	//May not need these, fine for now
	public String getResult() throws RemoteException {
		return r.getResult();
	}

	public void setResult(String result) throws RemoteException {
		r.setResult(result);
	}

	public boolean isProcessed() throws RemoteException {
		return r.isProcessed();
	}

	public void setProcessed() throws RemoteException {
		r.setProcessed();
	}
}

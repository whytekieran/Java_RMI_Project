package ie.gmit.rmiModels;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

//Here the string service remote interface is implemented.
public class StringServiceImpl extends UnicastRemoteObject implements StringService {

	private Resultator r;//Composed of a Resultator
	
	private static final long serialVersionUID = 1L;

	public StringServiceImpl() throws RemoteException{
	}

	@Override
	//When we call remotely call declare from the client
	public Resultator compare(String str1, String str2, String algo) throws RemoteException {
		//using this line below and empty constructor above we create new ResultatorImpl instance each time.
		r = new ResultatorImpl(); 
		
		//Pass the values into a runnable and start it in a thread. Let the thread handle the comparing.
		StringCompareRunnable compareJob = new StringCompareRunnable(str1, str2, r, algo);
		Thread compareWorker = new Thread(compareJob);
		compareWorker.start();
			
		return r; //While thread is running the current version of the resultator is returned.
	}
}

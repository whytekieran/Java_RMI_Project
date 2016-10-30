package ie.gmit.rmiModels;

import java.rmi.RemoteException;

public class StringCompareRunnable implements Runnable {
	private String str1;
	private String str2;
	private Resultator r;
	private String algo;
	private String a; 
	
	public StringCompareRunnable(String str1, String str2, Resultator r, String algo){
		this.str1 = str1;
		this.str2 = str2;
		this.r = r;
		this.a = algo;//just holds the name of the algorithm for comparison
	}

	@Override
	public void run() {
		
		//STRING COMPARING TO BE DONE HERE CAN IMPLEMENT IT LATER
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		//I know its silly here but just an example
		if(algo == algo){
			
			//int distance = a.distance(str1, str2);
			try {
				//r.setResult(""+distance);
				r.setProcessed();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}

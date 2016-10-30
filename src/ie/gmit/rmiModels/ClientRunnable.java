package ie.gmit.rmiModels;

import java.rmi.Naming;
import java.util.Map;

import ie.gmit.queueModels.Queueable;
import ie.gmit.queueModels.StringTask;

public class ClientRunnable implements Runnable {
	
	Queueable<StringTask> inQueue;
	Map<String, Resultator> outQueue;

	public ClientRunnable(Queueable<StringTask> inQueue, Map<String, Resultator> outQueue){
		this.inQueue = inQueue;
		this.outQueue = outQueue;
	}
	
	//UNFINISHED
	@Override
	public void run() {
		StringTask currentTask = getNextTask();
		try 
		{
			StringService service = (StringService) Naming.lookup("rmi://localhost:1099/stringService");
			Resultator r = service.compare(currentTask.getStr1(), currentTask.getStr2(), currentTask.getAlgo());
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public StringTask getNextTask(){
		return this.inQueue.take();
	}
}

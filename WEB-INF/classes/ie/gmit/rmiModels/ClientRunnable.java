package ie.gmit.rmiModels;

import java.rmi.Naming;
import java.util.Map;

import ie.gmit.queueModels.Queueable;
import ie.gmit.queueModels.StringTask;

//Runnable for the client thread. There will only be one of these threads running separately from the service
//handler. Its responsibility is to continuously check the in-queue from task and send them to the server via
//java RMI. I could have created many of these runnables but i only wanted one to be shared amongst all the 
//requests. This will purposely create a slight bottle neck and slow things down a little. Just for show.
public class ClientRunnable implements Runnable {
	
	Queueable<StringTask> inQueue;
	Map<String, Resultator> outQueue;

	//Accepts the in and out queue - both are concurrent and shared between all requests
	public ClientRunnable(Queueable<StringTask> inQueue, Map<String, Resultator> outQueue){
		this.inQueue = inQueue;
		this.outQueue = outQueue;
	}
	
	//When the thread starts this runnable to method will execute.
	@Override
	public void run() {
		
		try 
		{
			//First, connect to the RMI service.
			StringService service = (StringService) Naming.lookup("rmi://localhost:1099/stringService");
			
			//Just keep looping
			while(true){
				StringTask currentTask = getNextTask(); //get task from in-queue, wait if none are there
				//Call remote method, returns a remote object (Pass by Reference ;D)
				Resultator r = service.compare(currentTask.getStr1(), currentTask.getStr2(), currentTask.getAlgo());
				updateOutQueue(currentTask.getTaskNumber(), r);//Update out queue with current resultator
				
				//Keep doing this do-while until the task is processed. May seem slightly silly but again im 
				//trying to slow things a little bit.
				do{
					//Result got, but final processing (thread.sleep()) may not be done. The compare runnable on
					//server side does a thread.sleep() twice to simulate work. The first sleep is before any
					//algorithm is ran. (7seconds) the second is between getting the result and calling 
					//setProcessed(). So we could have a result but processing not completely finished. Basically
					//just adding an additional status the resultator can have.
					if(r.getResult() != null){
						updateOutQueue(currentTask.getTaskNumber(), r);//update current resultator status
					}
					
					//Just wait until Resultator is processed, keep polling, silly but gives asynchronous feeling
					//which is what this project is mainly about.
					Thread.sleep(5000);
				}
				while(r.isProcessed() == false);
				
				//At this point it should be processed so update the out-queue. Then loop around and get next
				//string task from the in-queue.
				updateOutQueue(currentTask.getTaskNumber(), r);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Two methods below. One to update the out queue that the client is polling. The other to take from the in-queue
	
	public void updateOutQueue(String taskNum, Resultator r){
		outQueue.put(taskNum, r);
	}
	
	public StringTask getNextTask(){
		return this.inQueue.take();
	}
}

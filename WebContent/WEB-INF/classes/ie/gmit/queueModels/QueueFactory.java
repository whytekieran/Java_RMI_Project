package ie.gmit.queueModels;

//Factory/Singleton class
public class QueueFactory {
	
	//Singleton class
	private static QueueFactory qf = new QueueFactory();
	
	//Private constructor forces user to call getInstance() to get the factory object. 
	private QueueFactory(){
		
	}
	
	public static QueueFactory getInstance(){
		return qf;
	}
	
	@SuppressWarnings("rawtypes")
	//One user has a factory instance can then call getQueue() and pass in a QueueType which is an enum.
	//Only one enum type but you could imagine having more.
	public Queueable getQueue(QueueType type){
		
		//Could easily imagine having more than one type of Queueable which could be returned.
		if(type == QueueType.STRING_TASK_QUEUE){
			return new TaskQueue<StringTask>();
		}
		
		return new TaskQueue<StringTask>();
	}
}

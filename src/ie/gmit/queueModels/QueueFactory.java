package ie.gmit.queueModels;

public class QueueFactory {
	
	//Singleton class
	private static QueueFactory qf = new QueueFactory();
	
	private QueueFactory(){
		
	}
	
	public static QueueFactory getInstance(){
		return qf;
	}
	
	@SuppressWarnings("rawtypes")
	public Queueable getQueue(QueueType type){
		
		//Could easily imagine having more than one type of Queueable which could be returned.
		if(type == QueueType.STRING_TASK_QUEUE){
			return new TaskQueue<StringTask>();
		}
		
		return new TaskQueue<StringTask>();
	}
}

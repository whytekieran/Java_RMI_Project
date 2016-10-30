package ie.gmit.sw;

public class StringTask {

	private String taskNumber;
	private String algo;
	private String str1;
	private String str2;
	
	//Constructor
	public StringTask(String taskNumber, String algo, String str1, String str2){
		this.taskNumber = taskNumber;
		this.algo = algo;
		this.str1 = str1;
		this.str2 = str2;
	}

	//Getters and Setters
	public String getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(String taskNumber) {
		this.taskNumber = taskNumber;
	}

	public String getAlgo() {
		return algo;
	}

	public void setAlgo(String algo) {
		this.algo = algo;
	}

	public String getStr1() {
		return str1;
	}

	public void setStr1(String str1) {
		this.str1 = str1;
	}

	public String getStr2() {
		return str2;
	}

	public void setStr2(String str2) {
		this.str2 = str2;
	}

	//Used if we need to compare two objects. Any custom classes (StringTask) used in custom queue should have
	//ways of comparing them. May not always be needed but good practice.
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StringTask otherTask = (StringTask) obj;
		if (this.taskNumber == null) 
		{
			if (otherTask.taskNumber != null)
				return false;
		} 
		else if (!this.taskNumber.equals(otherTask.taskNumber))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		return this.taskNumber.hashCode();
	}
}

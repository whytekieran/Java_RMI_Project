package ie.gmit.middleService;

import java.io.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.*;
import javax.servlet.http.*;

import ie.gmit.queueModels.QueueFactory;
import ie.gmit.queueModels.QueueType;
import ie.gmit.queueModels.Queueable;
import ie.gmit.queueModels.StringTask;
import ie.gmit.rmiModels.ClientRunnable;
import ie.gmit.rmiModels.Resultator;
import ie.gmit.rmiModels.ResultatorImpl; 

public class ServiceHandler extends HttpServlet {

	//Version id for any serialization
	private static final long serialVersionUID = 1L;
	private String remoteHost = null;						//Will be initialized to 127.0.0.1
	private volatile static long jobNumber = 0;				
	
	//Create a queue factory then use it to get a TaskQueue<StringTask>
	private static QueueFactory qf = QueueFactory.getInstance();
	@SuppressWarnings("unchecked")
	private static Queueable<StringTask> inQueue = qf.getQueue(QueueType.STRING_TASK_QUEUE);
	//Out queue holds string ids and result objects, client can check result object for feedback on status
	private static Map<String, Resultator> outQueue = new ConcurrentHashMap<String, Resultator>();
	//Create a client runnable...will only run once, only want one client running in separate thread
	//could have created multiple clients but trying to slow things down a little. Client runnable
	//gets two parameters, the in and out queues which are both concurrent and static. (shared)
	private static ClientRunnable clientJob = new ClientRunnable(inQueue, outQueue);
	private static Thread clientWorker = new Thread(clientJob);
	
	public void init() throws ServletException {
		ServletContext ctx = super.getServletContext();
		remoteHost = ctx.getInitParameter("RMI_SERVER"); //Reads the value from the <context-param> in web.xml
		
		//If the thread is not alive, start it, otherwise do nothing. I only want ONE client thread. This will slow
		//things a little and give more of an Asynchronous feeling. I could have easily implemented a client 
		//runnable thread for each request and done more to make this efficient but I'm trying to slow things 
		//and give that asynchronous feeling.
		if(clientWorker.isAlive() != true){
			clientWorker.start();
		}
	}

	//Everything inside here...and inside doPost is thread safe. One thread for each request.
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		boolean stringCompareComplete = false;	//Set this to true if task is done so we don't keep sending polls
		
		//Initialize some request variables with the submitted form info from the client request.
		String algorithm = req.getParameter("cmbAlgorithm");
		String str1 = req.getParameter("txtS");
		String str2 = req.getParameter("txtT");
		String taskNumber = req.getParameter("frmTaskNumber");//will be null first time around but polling after that
		
		out.print("<html><head><title>Distributed Systems Assignment</title>");		
		out.print("</head>");		
		out.print("<body>");
		
		if (taskNumber == null){
			++jobNumber;
			taskNumber = new String("T" + jobNumber);
			inQueue.offer(new StringTask(taskNumber, algorithm, str1, str2));
			//This Resultator below is just a placeholder to avoid a null pointer exception. When this task comes up 
			//on the client RMI queue and compare() is called. This placeholder will immediately be replaced by
			//an instance of the Resultator remote object. 
			Resultator r = new ResultatorImpl();
			outQueue.put(taskNumber, r);
		}else{
			//Check out-queue for finished job
			Resultator r = outQueue.get(taskNumber);
			if(r != null && r.isProcessed() && r.getResult() != null){
				out.print("YOUR REQUEST HAS BEEN PROCESSED AND HERE IS THE RESULT: "+r.getResult());
				outQueue.remove(taskNumber);		//Can take it off the out-queue now
				stringCompareComplete = true; 		//This task is complete so set to true
			}
			else if(r != null && r.isProcessed() == false && r.getResult() != null){
				out.print("WERE ALMOST THERE - THANKS FOR YOUR PATIENCE");
			}
			else{
				out.print("YOUR RESULT IS CURRENTLY BEING PROCESSED - PLEASE WAIT");
			}
		}
		
		//If the task is complete there is no need to send the form again, just output a thank you message
		if(stringCompareComplete){
			out.print("<br>THANK YOU FOR USING THE SERVICE");
		}
		else//if task is not complete poll again
		{
			out.print("<H1>Processing request for Job#: " + taskNumber + "</H1>");
			out.print("<div id=\"r\"></div>");
		
			out.print("<font color=\"#993333\"><b>");
			out.print("RMI Server is located at " + remoteHost);
			out.print("<br>Algorithm: " + algorithm);		
			out.print("<br>String <i>s</i> : " + str1);
			out.print("<br>String <i>t</i> : " + str2);
		
			//Form sends every 10 seconds to simulate a client polling. Keeps going until task is completed.
			out.print("<form name=\"frmRequestDetails\">");
			out.print("<input name=\"cmbAlgorithm\" type=\"hidden\" value=\"" + algorithm + "\">");
			out.print("<input name=\"txtS\" type=\"hidden\" value=\"" + str1 + "\">");
			out.print("<input name=\"txtT\" type=\"hidden\" value=\"" + str2 + "\">");
			out.print("<input name=\"frmTaskNumber\" type=\"hidden\" value=\"" + taskNumber + "\">");
			out.print("</form>");								
			out.print("</body>");	
			out.print("</html>");	
		
			out.print("<script>");
			//Here is where we submit the form
			out.print("var wait=setTimeout(\"document.frmRequestDetails.submit();\", 10000);");
			out.print("</script>");
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
 	}
}
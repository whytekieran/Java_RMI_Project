package ie.gmit.rmiModels;

import ie.gmit.stringAlgos.DamerauLevenshtein;
import ie.gmit.stringAlgos.HammingDistance;
import ie.gmit.stringAlgos.Hirschberg;
import ie.gmit.stringAlgos.Levenshtein;
import uk.ac.shef.wit.simmetrics.similaritymetrics.JaroWinkler; 
import uk.ac.shef.wit.simmetrics.similaritymetrics.EuclideanDistance;
import uk.ac.shef.wit.simmetrics.similaritymetrics.NeedlemanWunch;
import uk.ac.shef.wit.simmetrics.similaritymetrics.SmithWaterman;

//Runnable which runs in its own thread on the server. Called inside the compare() method. Allows the Resultator
//to be returned while computation on it continues.
public class StringCompareRunnable implements Runnable {
	//Instance variables to retrieve string task values
	private String str1;
	private String str2;
	private Resultator r;
	private String algo;
	//Different Algorithm Objects, some from you John, others copied from online and referenced and some use
	//the simmetrics library. This jar is inside WEB-INF -> lib
	private Levenshtein ld = new Levenshtein();
	private HammingDistance hd = new HammingDistance();
	private DamerauLevenshtein dl = new DamerauLevenshtein();
	private JaroWinkler algorithmJ = new JaroWinkler();
	private EuclideanDistance algorithmE = new EuclideanDistance();
	private NeedlemanWunch algorithmN = new NeedlemanWunch();
	private SmithWaterman algorithmS = new SmithWaterman();
	private Hirschberg algorithmH = new Hirschberg();
	
	//Constructor accepts values from string task object. Client runnable grabs them and passes them into compare()
	//which in turn then passes them to this runnable..
	public StringCompareRunnable(String str1, String str2, Resultator r, String algo){
		this.str1 = str1;
		this.str2 = str2;
		this.r = r;
		this.algo = algo;//just holds the name of the algorithm for comparison
	}

	//Run method, essentially the main method of the runnable.
	@Override
	public void run() {
		
		//Different algorithms return the result in different formats
		int distance;
		float distanceF;
		String distanceS;
		
		//Just simulating some work here
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		//Decide with string comparison algorithm to run. The code in each selection is basically the same
		if(algo.equalsIgnoreCase("Levenshtein Distance")){
			
			distance = ld.distance(str1, str2);
			try {
				r.setResult("Levenshtein Distance is: "+distance);
				Thread.sleep(7000);//More processing done here
				r.setProcessed();//All done :)
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(algo.equalsIgnoreCase("Hamming Distance"))
		{
			distance = hd.distance(str1, str2);
			try {
				r.setResult("Hamming Distance is: "+distance);
				Thread.sleep(7000);//More processing done here
				r.setProcessed();//All done :)
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(algo.equalsIgnoreCase("Damerau-Levenshtein Distance"))
		{
			distance = dl.distance(str1, str2);
			try {
				r.setResult("Hamming Distance is: "+distance);
				Thread.sleep(7000);//More processing done here
				r.setProcessed();//All done :)
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(algo.equalsIgnoreCase("JaroaWinkler Distance"))
		{
			 distanceF = algorithmJ.getSimilarity(str1, str2);
			 try {
				r.setResult("JaroaWinkler Distance is: "+distanceF);
				Thread.sleep(7000);//More processing done here
				r.setProcessed();//All done :)
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(algo.equalsIgnoreCase("Euclidean Distance"))
		{
			distanceF = algorithmE.getSimilarity(str1, str2);
			 try {
				r.setResult("Euclidean Distance is: "+distanceF);
				Thread.sleep(7000);//More processing done here
				r.setProcessed();//All done :)
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(algo.equalsIgnoreCase("Needleman-Wunsch"))
		{
			distanceF = algorithmN.getSimilarity(str1, str2);
			 try {
				r.setResult("Needleman-Wunsch Distance is: "+distanceF);
				Thread.sleep(7000);//More processing done here
				r.setProcessed();//All done :)
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(algo.equalsIgnoreCase("Smith Waterman"))
		{
			distanceF = algorithmS.getSimilarity(str1, str2);
			 try {
				r.setResult("Smith Waterman Distance is: "+distanceF);
				Thread.sleep(7000);//More processing done here
				r.setProcessed();//All done :)
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(algo.equalsIgnoreCase("Hirschberg's Algorithm"))
		{
			int str1Length = str1.length();
			int str2Length = str2.length();
			
			distanceS = algorithmH.algC(str1Length, str2Length, str1, str2);
			 try {
				r.setResult("Hirschberg's optimal sequence alignment is: "+distanceS);
				Thread.sleep(7000);//More processing done here
				r.setProcessed();//All done :)
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}//end run()
}//end class

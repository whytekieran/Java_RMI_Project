package ie.gmit.stringAlgos;

//John Healy, Professor at GMIT issued this algorithm as part of basic project starter parts
public class HammingDistance {
	
	public int distance(String s, String t) {
		if (s.length() != t.length()) return -1; //Similar length strings only
		int counter = 0;
		
		for (int i = 0; i < s.length(); ++i){
			if (s.charAt(i) != t.charAt(i)) counter++;
		}
		return  counter;
	}
	
}

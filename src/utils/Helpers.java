package utils;

import java.math.BigInteger;
import java.util.ArrayList;

public class Helpers {
	
	private static Helpers instance;
	
	public static synchronized Helpers getInstance() {
		if (instance == null) {
			instance = new Helpers();
		}
		return instance;
	} 
	
	private Helpers(){	}	
	
	public int diffValues(String[] s){
	    ArrayList<String> diffNum = new ArrayList<String>();
	    for(int i=0; i<s.length; i++){	        
	    	if(!diffNum.contains(s[i])){
	            diffNum.add(s[i]);
	        }	    	
	    }
	    return diffNum.size();
	}
	
}

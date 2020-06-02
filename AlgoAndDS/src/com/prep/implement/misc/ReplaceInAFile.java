package com.prep.implement.misc;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ReplaceInAFile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileInputStream iStream = null;
		String search = "textFiles/a\\.txt";  // <- changed to work with String.replaceAll()
		String replacement = "something/bob.txt";
		//file reading
		InputStreamReader isr = null;
		String content;
		try {
			iStream= new FileInputStream("log.txt");
			
			isr = new InputStreamReader(iStream);
		    BufferedReader br = new BufferedReader(isr);

		    while ((content = br.readLine()) != null) {
		    	content.replaceAll(search, replacement);
		        // do something with the resulting line
		    }
		}catch(Exception ex){
			
		}
	}
}

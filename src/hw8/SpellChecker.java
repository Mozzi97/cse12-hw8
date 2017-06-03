/*
 * Name: Jinxiao Chen
 * ID:A14236655
 * Login:cs12xii
 */

package hw8;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SpellChecker {
	private HashTable table;
	private StringBuilder sb;
	private ArrayList<String> list;
	private ArrayList<String> collected;
	
	public SpellChecker(String inputfile, String checkword){
		table = new HashTable(100,"out.txt");
		
		list = new ArrayList<String>();
		collected = new ArrayList<String>();
		this.populateinput(inputfile);
		this.populatecheck(checkword);
		
	}
	
	public void populateinput(String fname) {
		File file = new File(fname);
		try {
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				String nextLine = scanner.nextLine();
				if(nextLine.isEmpty()) {
					return;
				}
				
				table.insert(nextLine);

			
			}
			scanner.close();
		}
		catch(FileNotFoundException e) {
			
		}
		
	}
	
	public void populatecheck(String fname) {
		File file = new File(fname);
		try {
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				String nextLine = scanner.nextLine().toLowerCase();
				if(nextLine.isEmpty()) {
					return;
				}			
				list.add(nextLine);
			}
			scanner.close();
		}
		catch(FileNotFoundException e) {
			
		}
		
	}
	
	public void check(){
		for(int i = 0; i < list.size(); i++){
			String thisstr = list.get(i);	
			if(table.contains(list.get(i))){
				System.out.println(list.get(i) + ": ok");
			}
			else{
				System.out.print(list.get(i) + ": ");
				
				
				//1: 1 character inside is wrong
				String tempWord=""; 
				String space = "";
			    for(int j=0;j<thisstr.length();j++){
			    	
			    	char cur; 
			    	sb = new StringBuilder(thisstr);
			    	for(int k=97;k<123;k++){
			    		cur=(char)k;
			    		sb.setCharAt(j, cur);
			    		tempWord = sb.toString();
			    		if(table.contains(tempWord)){
			    			collected.add(tempWord);
			    		}
			    		tempWord = "";
			    	}
			    }
			    
			    //2. insert 1 character into the word
			    for(int j=0;j<=thisstr.length();j++){
			    	char cur;
			    	
			    	for(int k=97;k<123;k++){
			    		cur=(char)k;
			    		tempWord = thisstr;
			    		sb = new StringBuilder(tempWord);
			    		String temp = sb.insert(j,cur).toString();
			    		if(table.contains(temp) && !collected.contains(temp)){
			    			collected.add(temp);
			    			temp = "";
			    		}
			    		tempWord="";
			    	}
			    	
			    }
			    
			    //3. delete 1 character in the word
			    for(int j=0;j<thisstr.length();j++){
			    	sb = new StringBuilder(thisstr);
			    	tempWord = sb.deleteCharAt(j).toString();
			    	if(table.contains(tempWord)){
			    		collected.add(tempWord);
			    	}
			    	tempWord="";
			    }
			    
			    //4. a pair of adjacent transposed letters
			    for(int j=0;j<thisstr.length()-1;j++){
			    	
			    	char pre = thisstr.charAt(j);
			    	char next = thisstr.charAt(j+1);
			    	tempWord = thisstr;
			    	StringBuilder bud = new StringBuilder(tempWord);
			    	bud.setCharAt(j, next);
			    	bud.setCharAt(j+1, pre);
			    	
			    	if(table.contains(bud.toString())){
			    		collected.add(bud.toString());
			    	}
			    	tempWord="";
			    }
			    
			    //5. every pair of strings that can be made by inserting a space into the word
			    for(int j=1;j<thisstr.length();j++){
			    	
			    	sb = new StringBuilder(thisstr);
			    	String strword = sb.insert(j, " ").toString();
			    	String[] strlist = strword.split(" ");
			    	String str1 = strlist[0];
			    	String str2 = strlist[1];
			    	if(table.contains(str1) && table.contains(str2)){
			    		
			    		space =str1 + " " + str2;
			    		collected.add(space);
			    	}
			    	
			    	
			    }

			    
			    if(collected.isEmpty()){
			    	System.out.print("not found\n");
			    }
			    
			    for(int a = 0; a<collected.size(); a++){
			    	if(a != collected.size() -1){
			    		System.out.print(collected.get(a) + ", ");
			    	}
			    	else{
			    		System.out.print(collected.get(a)+"\n");
			    		
			    	}
			    }
			    collected.clear();
			}
		}//check finish
		
		
		
		
		
		
	}
	
	
	public static void main(String [ ] args){
		SpellChecker check1 = new SpellChecker(args[0],args[1]);
		check1.check();
	}
	
	
}


package hw8;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SpellChecker {
	private HashTable table;
	private ArrayList<String> list;
	
	public SpellChecker(String inputfile, String checkword){
		table = new HashTable(10000,"out.txt");
		list = new ArrayList<String>();
		this.populateinput(inputfile);
		this.populatecheck(checkword);
		
		this.check(list);
	}
	
	public void populateinput(String fname) {
		File file = new File(fname);
		try {
			Scanner scanner = new Scanner(file);
			scanner.nextLine();
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
			scanner.nextLine();
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
	
	public void check(ArrayList<String> list){
		for(int i = 0; i < list.size(); i++){

			if(table.contains(list.get(i))){
				System.out.println(list.get(i) + ": ok");
			}

		}
	}
	
	
}


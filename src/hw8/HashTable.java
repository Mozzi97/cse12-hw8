/*
 * Name: Jinxiao Chen
 * ID:A14236655
 * Login:cs12xii
 */
package hw8;

import java.io.*;

import hw8.HashTable.LinkedList.Node;

public class HashTable implements IHashTable {
	
	//You will need a HashTable of LinkedLists. 
	
	private int nelems;  //Number of element stored in the hash table
	private int expand;  //Number of times that the table has been expanded
	private int collision;  //Number of collisions since last expansion
	private String statsFileName;     //FilePath for the file to write statistics upon every rehash
	private boolean printStats = false;   //Boolean to decide whether to write statistics to file or not after rehashing
	private double factor;
	private Node found;
	private Node prev;
	
	private LinkedList[] table;

	private int tableindex = 0;
	private int listindex = 0;
	private int longest = 0;
	private int numlongest = 0;
	private FileWriter fw;
	
	//You are allowed to add more :)
	
	public class LinkedList{
		Node head = new Node(null);  // head of list
		private int nsize;

	    class Node{
	        Node next;
			private String data;
	        
	        Node(String value){
	        	this.data = value; next = null; 
	        	
	        }

			public String getElement() {
				return data;				
			}
	    }
	    
		private void push(String value){

		    Node new_node = new Node(value);
		 
		    if (head == null){
		        head = new Node(value);
		        
		        nsize++;
		        return;
		    }
		    else{
		    new_node.next = null;
		 
		    Node last = head; 
		    while (last.next != null){
		        last = last.next;
		    }
		    last.next = new_node;
		    nsize++;
		    return;
		    }
		}

		public int size() {
			return nsize;
		}

		public Node get(int j) {
			Node myNode = head;
			if(j<nsize){
				for(int i=0;i<=j;i++){
					myNode = myNode.next;
					
				}
			}
			return myNode;
		}
		
	}
	
	/**
	 * Constructor for hash table
	 * @param Initial size of the hash table
	 * @throws IOException 
	 */
	public HashTable(int size) {
		
		//Initialize

		table = new LinkedList[size];
		for(int i = 0; i<size; i++){
			table[i]=new LinkedList();
		}
		this.nelems = 0;

	}
	
	/**
	 * Constructor for hash table
	 * @param Initial size of the hash table
	 * @param File path to write statistics
	 * @throws IOException 
	 */
	public HashTable(int size, String fileName){
		
		//Set printStats to true and statsFileName to fileName

		
		table = new LinkedList[size];
		for(int i = 0; i<size; i++){
			table[i]=new LinkedList();
		}

		this.printStats = true;

		this.statsFileName = fileName;
		try{
			fw = new FileWriter(statsFileName,true);
		}
		catch(IOException fx){
			
		}
	}

	/** Insert the string value into the hash table
	 * 
	 * @param value value to insert
	 * @return true if the value was inserted, false if the value was already present
	 */
	public boolean insert(String value){
		if(value == null){
			throw new NullPointerException();
		}
		factor = (double)nelems/table.length;
		if( factor > 0.75 && printStats == true){
			this.expand ++;
			this.findlong();
			
			try {
				this.printStatistics();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			this.rehash();
		}
		else{
			int hashval = 0;
			for(int j = 0; j<value.length();j++){
				int letter = value.charAt(j);
				hashval = (hashval*27+letter)%table.length;

			}

			if(table[hashval].size()>0){
				collision ++;
				
			}
			else if(this.contains(value)){
				return false;
			}
			table[hashval].push(value);
			nelems++;
			return true;
		}
		
		return false;

		
	}


	/** Delete the given value from the hash table
	 * 
	 * @param value value to delete
	 * @throws NullPointerException if value is null
	 * @return true if the value was deleted, false if the value was not found
	 */
	public boolean delete(String value)throws NullPointerException {
		if(value == null){
			throw new NullPointerException();
		}
		if(!this.contains(value)){
			return false;
		}
		else{
			if(found.next == null){
				for(int i = 0; i<=listindex-1;i++){
					prev = table[tableindex].get(i);
				}
				prev.next = null;
			}
			else if(found.next != null){
				for(int i = 0; i<=listindex-1;i++){
					prev = table[tableindex].get(i);
				}
				prev.next = found.next;
			}
			nelems --;
			return true;
		}
		
	}

	/** Check if the given value is present in the hash table
	 * 
	 * @param value value to look up
	 * @throws NullPointerException if value is null
	 * @return true if the value was found, false if the value was not found
	 */
	public boolean contains(String value)throws NullPointerException {
		if(value == null){
			throw new NullPointerException();
		}
		
		for(int i = 0; i<table.length; i++){
			for(int j = 0; j<table[i].size();j++){
				if(table[i].get(j).getElement().equals(value)){
					found = table[i].get(j);
					tableindex = i;
					listindex = j;
					return true;
				}
			}
		}
		
		
		return false;
		

	}
	

	/** Print the contents of the hash table. Print nothing if table is empty
	 */
	public void printTable() {
		for(int i = 0; i<table.length; i++){
			System.out.print(i + ": ");
			for(int j = 0; j<table[i].size();j++){
				System.out.print(table[i].get(j).getElement() + " ");
			}
			System.out.println("\n");
		}
	}
	
	
	/**
	 * Return the number of elements currently stored in the hashtable
	 * @return nelems
	 */
	public int getSize() {
		return nelems;
	}
	
	//Helper methods can make your code more efficient and look neater.
	//We expect AT LEAST the follwoing helper methods in your code
	//rehash() to expand and rehash the items into the table when load factor goes over the threshold.
	//printStatistics() to print the statistics after each expansion. This method will be called from insert/rehash only if printStats=true
	private int findlong(){
		if(nelems == 0){
			return 0;
		}
		longest = table[0].size();
		for(int i = 1;i<table.length;i++){
			if(longest<table[i].size()){
				longest = table[i].size();
			}
		}
		for(int i = 1;i<table.length;i++){
			if(longest==table[i].size()){
				numlongest ++;
			}
		}
		return numlongest;
	}
	private void rehash(){
		this.collision = 0;
		this.factor = 0;
		this.longest = 0;
		this.numlongest = 0;
		LinkedList[] newtable = new LinkedList[table.length*2];
		
		for(int i = 0; i<newtable.length; i++){
			newtable[i]=new LinkedList();
		}
		for(int i = 0; i<table.length;i++){
			newtable[i]=table[i];
		}
		table = newtable;
		
		
		
	}
	
	private void printStatistics() throws IOException{
		try{

			fw.write(expand + " resizes, load factor " + (double) Math.round
					(factor * 100) / 100 + ", " + collision + " collisions, " 
					+ longest + " longest chain"+"\n");
			fw.flush();

		}
		catch(IOException fx){
			System.out.println(fx);
		}
	}

}
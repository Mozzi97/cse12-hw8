/*
 * Name: Jinxiao Chen
 * ID:A14236655
 * Login:cs12xii
 */

package hw8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Ransom {
	private HashTable table;
	private String[] ransom;
	
	

	public Ransom(String magazine[], String ransom[]){
		table = new HashTable(100,"output.txt");
		
		for(int i=0; i<magazine.length; i++){
			table.insert(magazine[i]);
        }
		this.ransom = ransom;
	}


	
	private void check(){

		
		for(int i=0; i<ransom.length; i++){
			if(!table.contains(ransom[i])){
				System.out.println("No");
				return;
			}
        }
		System.out.println("Yes");

	}
	
	
	public static void main(String [ ] args){
		
	        Scanner in = new Scanner(System.in);
	        int m = in.nextInt();
	        int n = in.nextInt();
	        String magazine[] = new String[m];
	        for(int magazine_i=0; magazine_i < m; magazine_i++){
	            magazine[magazine_i] = in.next();
	        }
	        String ransom[] = new String[n];
	        for(int ransom_i=0; ransom_i < n; ransom_i++){
	            ransom[ransom_i] = in.next();
	        }

	        
	        Ransom ran = new Ransom(magazine,ransom);
	        ran.check();
	        in.close();
	}
}


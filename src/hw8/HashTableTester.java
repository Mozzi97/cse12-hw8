/*
 * Name: Jinxiao Chen
 * ID:A14236655
 * Login:cs12xii
 */
package hw8;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HashTableTester {
	HashTable table;
	
	
	
	
	
	 @Before
	  public void setUp(){
		table = new HashTable(10,"out.txt");
		
	  }

	@Test
	public void testinsert() {
		table.insert("ca");
		
		assertEquals("Check insert",1,table.getSize());
	}
	
	@Test
	public void testsize() {
		assertEquals("Check insert",0,table.getSize());
	}
	
	@Test
	public void testdelete() {
		table.delete("ca");
		table.insert("cb");
		
		assertEquals("Check insert",1,table.getSize());
	}
	
	@Test
	public void testcontains(){
		table.insert("ca");
		assertEquals("Check insert",true,table.contains("ca"));
	}
	
	@Test
	public void testrehash(){
		table.insert("ca");
		table.insert("cb");
		table.insert("cc");
		table.insert("cd");
		table.insert("ce");
		table.insert("cf");
		table.insert("cg");
		table.insert("ch");
		table.insert("ci");
		table.insert("cj");
		table.insert("cjb");
		table.insert("cjr");
		table.insert("cjg");
		table.insert("cjj");
		table.insert("cjrk");
		table.insert("ck");
		table.insert("cl");
		table.insert("cn");
		table.insert("cm");
		table.printTable();
		assertEquals("Check insert",true,table.contains("ca"));
	}

}

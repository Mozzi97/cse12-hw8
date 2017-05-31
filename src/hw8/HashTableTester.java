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
		table = new HashTable(10000,"out.txt");
		
	  }

	@Test
	public void testinsert() {
		table.insert("c");
		assertEquals("Check insert",1,table.getSize());
	}
	
	@Test
	public void testsize() {
		assertEquals("Check insert",0,table.getSize());
	}
	
	@Test
	public void testdelete() {
		table.delete("c");
		assertEquals("Check insert",0,table.getSize());
	}
	
	@Test
	public void testcontains(){
		assertEquals("Check insert",true,table.contains("c"));
	}

}

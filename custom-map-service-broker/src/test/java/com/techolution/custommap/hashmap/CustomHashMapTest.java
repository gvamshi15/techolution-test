package com.techolution.custommap.hashmap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class CustomHashMapTest {

	@Test
	public void shouldCreateCustomMapWithSizeZero() {
		CustomHashMap<String, String> customHashMap = new CustomHashMap<String, String>();
		assertEquals(0, customHashMap.size());
	}
	
	@Test
	public void shouldCreateCustomMapWithCustomCapacity() {
		CustomHashMap<String, String> customHashMap = new CustomHashMap<String, String>(20);
		assertEquals(20, customHashMap.getCapacity());
	}
	
	
	@Test
	public void shouldStoreElements() {
		CustomHashMap<String, String> customHashMap = new CustomHashMap<String, String>();
		customHashMap.store("test", "test");
		assertEquals(1, customHashMap.size());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldNotStoreNullKey() {
		CustomHashMap<String, String> customHashMap = new CustomHashMap<String, String>();
		customHashMap.store(null, "test");
		assertEquals(1, customHashMap.size());
	}
	
	@Test
	public void shouldNotStoreNullValue() {
		CustomHashMap<String, String> customHashMap = new CustomHashMap<String, String>();
		customHashMap.store("test", null);
		assertEquals(1, customHashMap.size());
	}

	
	@Test
	public void shouldNotStoreDuplicates() {
		CustomHashMap<String, String> customHashMap = new CustomHashMap<String, String>();
		customHashMap.store("test1", "test");
		customHashMap.store("test1", "test");
		assertEquals(1, customHashMap.size());
	}
	
	@Test
	public void shouldFindStoredKeyValuePair() {
		CustomHashMap<String, String> customHashMap = new CustomHashMap<String, String>();
		customHashMap.store("test1", "test1");
		customHashMap.store("test2", "test2");
		assertEquals("test1", customHashMap.find("test1"));
	}
	
	@Test
	public void shouldRemoveStoredKeyValuePair() {
		CustomHashMap<String, String> customHashMap = new CustomHashMap<String, String>();
		customHashMap.store("test1", "test1");
		customHashMap.store("test2", "test2");
		assertEquals(2, customHashMap.size());
		customHashMap.remove("test1");
		assertEquals(1, customHashMap.size());
		assertNull(customHashMap.find("test1"));
	}

}

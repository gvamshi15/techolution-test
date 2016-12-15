package com.techolution.custommap.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CustomHashMapServiceImplTest {
	
	private CustomHashMapService<String, String, String> service ;
	
	@Before
	public void setup() {
		service = new CustomHashMapServiceImpl<>();
	}

	@Test
	public void shouldStoreAndFindKeyValuePairs() {
		service.put("1", "key", "value");
		assertEquals("value", service.get("1", "key"));
	}
	
	@Test
	public void shouldNotThrowExceptionIfKeyIsNotFound() {
		//service.put("1", "key", "value");
		assertEquals(null, service.get("1", "key"));
	}
	
	@Test
	public void shouldUpdateKeyValuePairsIfExists() {
		service.put("1", "key", "value");
		service.put("1", "key", "value2");
		assertEquals("value2", service.get("1", "key"));
	}
	
	@Test
	public void shouldRemoveStoredKeyValuePairs() {
		service.put("1", "key", "value");
		service.delete("1", "key");
		assertEquals(null, service.get("1", "key"));
	}
	
	@Test
	public void shouldStoreAndFindKeyValuePairsBasedOnInstanceId() {
		service.put("1", "key", "value1");
		service.put("2", "key", "value2");
		assertEquals("value1", service.get("1", "key"));
		assertEquals("value2", service.get("2", "key"));
	}

}

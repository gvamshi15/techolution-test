package com.techolution.custommap.service;

import org.springframework.stereotype.Service;

import com.techolution.custommap.hashmap.CustomHashMap;

@Service
public class CustomHashMapServiceImpl<I, K, V> implements CustomHashMapService<I, K, V> {

	private CustomHashMap<I, CustomHashMap<K,V>> serviceInstanceMap;
	
	public CustomHashMapServiceImpl() {
		serviceInstanceMap= new CustomHashMap<>();
	}

	@Override
	public void put(I instanceId, K key, V value) {
		serviceInstanceMap.find(instanceId).store(key, value);
	}

	@Override
	public V get(I instanceId, K key) {
		return serviceInstanceMap.find(instanceId).find(key);
	}

	@Override
	public void delete(I instanceId, K key) {
		serviceInstanceMap.find(instanceId).remove(key);
	}
	


}

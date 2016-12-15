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
		CustomHashMap<K,V> keyValueMap = serviceInstanceMap.find(instanceId);
		if (keyValueMap == null) {
			keyValueMap = new CustomHashMap<>();
		}
		keyValueMap.store(key, value);
		serviceInstanceMap.store(instanceId, keyValueMap);
	}

	@Override
	public V get(I instanceId, K key) {
		CustomHashMap<K,V> keyValueMap = serviceInstanceMap.find(instanceId);
		if (keyValueMap == null) {
			keyValueMap = new CustomHashMap<>();
			serviceInstanceMap.store(instanceId, keyValueMap);
			return null;
		}
		return keyValueMap.find(key);
	}

	@Override
	public void delete(I instanceId, K key) {
		CustomHashMap<K,V> keyValueMap = serviceInstanceMap.find(instanceId);
		if (keyValueMap != null) {
			keyValueMap.remove(key);
		}
	}
	


}

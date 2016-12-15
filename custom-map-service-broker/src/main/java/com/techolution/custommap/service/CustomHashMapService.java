package com.techolution.custommap.service;

public interface CustomHashMapService<I, K, V> {

	public void put(I instance, K key, V value);

	public V get(I instance, K key);

	public void delete(I instance, K key);

}

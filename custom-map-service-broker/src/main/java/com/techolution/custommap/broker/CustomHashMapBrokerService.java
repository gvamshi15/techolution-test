package com.techolution.custommap.broker;

import java.util.List;

import com.techolution.custommap.domain.Catalog;
import com.techolution.custommap.domain.ServiceBinding;

public interface CustomHashMapBrokerService<K, V> {

	public void put(K key, V value);

	public V get(K key);

	public void delete(K key);

	public List<V> findAll();

	public Catalog getCatalog();

	public ServiceBinding getServiceBinding(String key);

	public void deleteBinding(String id);

	public void saveBinding(String id, ServiceBinding serviceBinding);

}

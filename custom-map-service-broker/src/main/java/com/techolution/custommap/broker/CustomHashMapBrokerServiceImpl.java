package com.techolution.custommap.broker;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.techolution.custommap.domain.Catalog;
import com.techolution.custommap.domain.Plan;
import com.techolution.custommap.domain.ServiceBinding;
import com.techolution.custommap.hashmap.CustomHashMap;

@Service
public class CustomHashMapBrokerServiceImpl<K, V> implements CustomHashMapBrokerService<K, V> {
	
	private CustomHashMap<K,V> serviceInstanceMap;
	private CustomHashMap<String,ServiceBinding> serviceBindingMap;
	private CustomHashMap<String, Catalog> catalog;
	
	public CustomHashMapBrokerServiceImpl() {
		serviceInstanceMap= new CustomHashMap<>();
		serviceBindingMap = new CustomHashMap<>();
		catalog = new CustomHashMap<>();
		catalog.store("catalog", createCatalog());
	}

	@Override
	public void put(K key, V value) {
		serviceInstanceMap.store(key, value);
	}

	@Override
	public V get(K key) {
		return serviceInstanceMap.find(key);
	}

	@Override
	public void delete(K key) {
		serviceInstanceMap.remove(key);
	}

	@Override
	public List<V> findAll() {
		return serviceInstanceMap.findAll();
	}
	
	@Override
	public Catalog getCatalog() {
		return catalog.find("catalog");
	}


	@Override
	public ServiceBinding getServiceBinding(String key) {
		return serviceBindingMap.find(key);
	}

	@Override
	public void deleteBinding(String id) {
		serviceBindingMap.remove(id);
		
	}

	@Override
	public void saveBinding(String id, ServiceBinding serviceBinding) {
		serviceBindingMap.store(id, serviceBinding);
	}
	
	
	private Catalog createCatalog() {
		Catalog catalog = new Catalog();
    	List<com.techolution.custommap.domain.Service> services = new ArrayList<>();
    	com.techolution.custommap.domain.Service service = new com.techolution.custommap.domain.Service();
    	UUID uuid = UUID.randomUUID();
    	service.setId(uuid.toString());
    	service.setDescription("Custom HashMap as Service");
    	service.setName("CustomHashMap-"+uuid.toString());
    	
    	Plan plan = new Plan();
    	plan.setId(uuid.toString());
    	plan.setName("basic");
    	plan.setDescription("Basic Plan");
    	service.addPlan(plan);
    	services.add(service);
    	catalog.setServices(services);
		return catalog;
	}

}

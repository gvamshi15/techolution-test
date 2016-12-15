package com.techolution.custommap.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.app.ApplicationInstanceInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techolution.custommap.broker.CustomHashMapBrokerService;
import com.techolution.custommap.domain.Catalog;
import com.techolution.custommap.domain.Credential;
import com.techolution.custommap.domain.Credentials;
import com.techolution.custommap.domain.ServiceBinding;
import com.techolution.custommap.domain.ServiceInstance;

@RestController
public class CustomHashMapServiceBrokerController {

	@Autowired
	private CustomHashMapBrokerService<String, ServiceInstance> customHashMapBrokerService;
	
    @Autowired
    private Cloud cloud;

	@RequestMapping("/v2/catalog")
	public Catalog catalog() {
		return customHashMapBrokerService.getCatalog();
	}

	@RequestMapping(value = "/v2/service_instances/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> createServiceInstance(@PathVariable("id") String id,
			@RequestBody ServiceInstance serviceInstance) {
		serviceInstance.setId(id);
		customHashMapBrokerService.put(id, serviceInstance);
		return new ResponseEntity<>("{}", HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/v2/service_instances/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteServiceInstance(@PathVariable("id") String id,
			@RequestParam("service_id") String serviceId, @RequestParam("plan_id") String planId) {
		customHashMapBrokerService.delete(id);
		return new ResponseEntity<>("{}", HttpStatus.OK);
	}

	@RequestMapping(value = "/v2/service_instances/{instanceId}/service_bindings/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> createServiceBinding(@PathVariable("instanceId") String instanceId,
			@PathVariable("id") String id, @RequestBody ServiceBinding serviceBinding) {
		if (customHashMapBrokerService.get(instanceId) == null) {
			return new ResponseEntity<Object>(
					"{\"description\":\"Service instance " + instanceId + " does not exist!\"", HttpStatus.BAD_REQUEST);
		}

		serviceBinding.setId(id);
		serviceBinding.setInstanceId(instanceId);

		String pwd = UUID.randomUUID().toString();
		Credential credential = new Credential();
		credential.setId(pwd);
		credential.setUri("http://" + myUri() + "/customhashmap/" + instanceId);
		credential.setUsername("scott");
		credential.setPassword("tiger");
		serviceBinding.setCredentials(credential);
		customHashMapBrokerService.saveBinding(id, serviceBinding);
		Credentials credentials = new Credentials();
		credentials.setCredentials(credential);
		return new ResponseEntity<Object>(credentials, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/v2/service_instances/{instanceId}/service_bindings/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteServiceBinding(@PathVariable("instanceId") String instanceId,
			@PathVariable("id") String id, @RequestParam("service_id") String serviceId,
			@RequestParam("plan_id") String planId) {
		customHashMapBrokerService.deleteBinding(id);
		return new ResponseEntity<>("{}", HttpStatus.OK);
	}
	
    @SuppressWarnings("unchecked")
	private String myUri() {
        ApplicationInstanceInfo applicationInstanceInfo = cloud.getApplicationInstanceInfo();
        List<Object> uris = (List<Object>) applicationInstanceInfo.getProperties().get("uris");
        return uris.get(0).toString();
    }


}

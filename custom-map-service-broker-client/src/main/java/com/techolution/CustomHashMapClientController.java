package com.techolution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;

@RestController
public class CustomHashMapClientController {

	private final static Logger LOG = LoggerFactory.getLogger(CustomHashMapClientController.class);

	@Autowired
	private RestOperations restTemplate;
	
	@Autowired
	private HaashServiceInfo haashServiceInfo;


	@RequestMapping(value = "/CustomHashmap/info", method = RequestMethod.GET)
	public HaashServiceInfo info() {
		return haashServiceInfo;
	}

	private HttpHeaders httpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		String credentials = haashServiceInfo.getUsername() + ":" + haashServiceInfo.getPassword();
		String basicCredentials = new String(Base64Utils.encode(credentials.getBytes()));
		headers.add("Authorization", "Basic " + basicCredentials);
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}

	private HttpEntity<String> getEntity(String message) {
		return new HttpEntity<String>(message, httpHeaders());
	}

	@RequestMapping(value = "/CustomHashmap/{key}", method = RequestMethod.PUT)
	public ResponseEntity<String> put(@PathVariable("key") String key, @RequestBody String value) {
		LOG.info("Service Info: {}", haashServiceInfo );
		restTemplate.put(haashServiceInfo.getUri() + "/" + key, getEntity(value));
		return new ResponseEntity<>("{}", HttpStatus.CREATED);
	}

	@RequestMapping(value = "/CustomHashmap/{key}", method = RequestMethod.GET)
	public ResponseEntity<String> put(@PathVariable("key") String key) {
		String response = restTemplate.getForObject(haashServiceInfo.getUri() + "/{key}", String.class, key);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}

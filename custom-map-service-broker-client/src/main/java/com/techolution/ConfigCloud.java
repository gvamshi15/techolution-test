package com.techolution;

import java.util.List;

import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudException;
import org.springframework.cloud.CloudFactory;
import org.springframework.cloud.service.ServiceInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

/**
 * Java Configuration class that fetches the runtime information when running in
 * a Cloud environment and makes it available as 3 Spring Beans: Bound Services,
 * Application/Instance information and Cloud Properties.
 */
@Configuration
public class ConfigCloud {

	/**
	 * Return The Spring Cloud meta-data object.
	 * 
	 * @return The Cloud object for the current environment or null if not
	 *         running in a known cloud environment
	 */
	@Bean
	public Cloud cloud() {
		try {
			return new CloudFactory().getCloud();
		} catch (CloudException e) {
			// Not running in a known cloud environment
			return null;
		}
	}

	
	@Bean
	public HaashServiceInfo haashServiceInfo(Cloud cloud) {
		HaashServiceInfo haashServiceInfo = null;
		List<ServiceInfo> serviceInfos = cloud.getServiceInfos();
		for (ServiceInfo serviceInfo : serviceInfos) {
			if (serviceInfo instanceof HaashServiceInfo) {
				haashServiceInfo = (HaashServiceInfo) serviceInfo;
			}
		}
		// throw new RuntimeException("Unable to find bound HaaSh instance!");
		return haashServiceInfo;
	}
	
	@Bean
	public RestOperations restTemplate() {
		return new RestTemplate();
	}

}
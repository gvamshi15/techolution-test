package com.techolution;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.service.ServiceInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomHashMapClientController {
	
	private final static Logger LOG = LoggerFactory.getLogger(CustomHashMapClientController.class);
	
	@Autowired
    private Cloud cloud;

    
	public HaashServiceInfo haashServiceInfo() {
		List<ServiceInfo> serviceInfos = cloud.getServiceInfos();
		LOG.info("Service Info: {}", serviceInfos);
		for (ServiceInfo serviceInfo : serviceInfos) {
			if (serviceInfo instanceof HaashServiceInfo) {
				return (HaashServiceInfo) serviceInfo;
			}
		}
		throw new RuntimeException("Unable to find bound HaaSh instance!");
	}

	
	@RequestMapping(value="/customhashmap/info", method=RequestMethod.GET)
    public HaashServiceInfo info() {
        return haashServiceInfo();
    }


}

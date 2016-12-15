package com.techolution;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.cloudfoundry.CloudFoundryServiceInfoCreator;
import org.springframework.cloud.cloudfoundry.Tags;

public class HaashServiceInfoCreator extends CloudFoundryServiceInfoCreator<HaashServiceInfo> {
	
	private final static Logger LOG = LoggerFactory.getLogger(HaashServiceInfoCreator.class);
	
    public HaashServiceInfoCreator() {
        super(new Tags("CustomHashMap"));
    }
    
    
    @Override
    public HaashServiceInfo createServiceInfo(Map<String, Object> serviceData) {
        Map<String, Object> credentials = (Map<String, Object>) serviceData.get("credentials");

        String id = (String) serviceData.get("name");
        String uri = (String) credentials.get("uri");
        String username = (String) credentials.get("username");
        String password = (String) credentials.get("password");
        
        return new HaashServiceInfo(id, uri, username, password);
    }

    @Override
    public boolean accept(Map<String, Object> serviceData) {
        Map<String, Object> credentials = (Map<String, Object>) serviceData.get("credentials");
        String uri = (String) credentials.get("uri");
        String username = (String) credentials.get("username");
        String password = (String) credentials.get("password");
        return username != null &&
                password != null &&
                uri != null;
    }
}

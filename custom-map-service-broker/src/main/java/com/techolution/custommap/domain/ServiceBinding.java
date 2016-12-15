package com.techolution.custommap.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
public class ServiceBinding {

    private String id;

    private String instanceId;

    @JsonSerialize
    @JsonProperty("service_id")
    private String serviceId;

    @JsonSerialize
    @JsonProperty("plan_id")
    private String planId;

    @JsonSerialize
    @JsonProperty("app_guid")
    private String appGuid;

    @JsonSerialize
    @JsonProperty("parameters")
    private String parameters;

    private Credential credentials;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getAppGuid() {
        return appGuid;
    }

    public void setAppGuid(String appGuid) {
        this.appGuid = appGuid;
    }

    public Credential getCredentials() {
        return credentials;
    }

    public void setCredentials(Credential credentials) {
        this.credentials = credentials;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceBinding that = (ServiceBinding) o;

        if (!appGuid.equals(that.appGuid)) return false;
        if (!id.equals(that.id)) return false;
        if (!instanceId.equals(that.instanceId)) return false;
        if (!planId.equals(that.planId)) return false;
        if (!serviceId.equals(that.serviceId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + instanceId.hashCode();
        result = 31 * result + serviceId.hashCode();
        result = 31 * result + planId.hashCode();
        result = 31 * result + appGuid.hashCode();
        return result;
    }
}

package com.techolution.custommap.domain;

import java.util.HashSet;
import java.util.Set;

public class Service {

    private String id;

    private String name;

    private String description;

    private boolean bindable;

    private Set<Plan> plans = new HashSet<>();

    private final Metadata metadata;

    public Service() {
        this.metadata = new Metadata();
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isBindable() {
        return bindable;
    }

    public void setBindable(boolean bindable) {
        this.bindable = bindable;
    }

    public Set<Plan> getPlans() {
        return plans;
    }

    public void setPlans(Set<Plan> plans) {
        this.plans = plans;
    }

    public void addPlan(Plan plan) {
        this.plans.add(plan);
    }
}

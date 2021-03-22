package com.goltsov.model.objects;

import java.util.Map;

public class Parameters {
    private Map<String, String> common;
    private ServicesPa services;

    public Parameters() {
    }

    public Map<String, String> getCommon() {
        return common;
    }

    public void setCommon(Map<String, String> common) {
        this.common = common;
    }

    public ServicesPa getServices() {
        return services;
    }

    public void setServices(ServicesPa services) {
        this.services = services;
    }

    @Override
    public String toString() {
        return "Parameters{" +
                "common=" + common +
                ", services=" + services +
                '}';
    }
}

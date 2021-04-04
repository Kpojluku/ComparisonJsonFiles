package com.goltsov.model.objects;

import java.util.LinkedHashMap;


public class Parameters {
    private LinkedHashMap<String, String> common;
    private ServicesPa services;

    public Parameters() {
    }

    public LinkedHashMap<String, String> getCommon() {
        return common;
    }

    public void setCommon(LinkedHashMap<String, String> common) {
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

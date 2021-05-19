package com.goltsov.model.objects;

import java.util.LinkedHashMap;
import java.util.Map;


public class Parameters {
    private LinkedHashMap<String, String> common;
    private Map<String, Map<String, String>> services = new LinkedHashMap<>();

    public Parameters() {
    }

    public Map<String, Map<String, String>> getServices() {
        return services;
    }

    public void setServices(Map<String, Map<String, String>> services) {
        this.services = services;
    }

    public LinkedHashMap<String, String> getCommon() {
        return common;
    }

    public void setCommon(LinkedHashMap<String, String> common) {
        this.common = common;
    }


    @Override
    public String toString() {
        return "Parameters{" +
                "common=" + common +
                ", services=" + services +
                '}';
    }
}

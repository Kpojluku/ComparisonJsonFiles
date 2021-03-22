package com.goltsov.model.objects;

import java.util.Map;

public class ServicesPa {
    Map<String, String> service_name;

    public ServicesPa() {
    }

    public Map<String, String> getService_name() {
        return service_name;
    }

    public void setService_name(Map<String, String> service_name) {
        this.service_name = service_name;
    }

    @Override
    public String toString() {
        return "ServicesPa{" +
                "service_name=" + service_name +
                '}';
    }
}

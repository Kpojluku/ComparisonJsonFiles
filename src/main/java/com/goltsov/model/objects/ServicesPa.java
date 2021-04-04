package com.goltsov.model.objects;

import java.util.LinkedHashMap;


public class ServicesPa {
    LinkedHashMap<String, String> service_name;

    public ServicesPa() {
    }

    public LinkedHashMap<String, String> getService_name() {
        return service_name;
    }

    public void setService_name(LinkedHashMap<String, String> service_name) {
        this.service_name = service_name;
    }

    @Override
    public String toString() {
        return "ServicesPa{" +
                "service_name=" + service_name +
                '}';
    }
}

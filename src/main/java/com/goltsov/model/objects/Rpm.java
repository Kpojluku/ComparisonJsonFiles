package com.goltsov.model.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rpm {
    private String url;
    private String rpm_repository_name;
    private Hashes hashes;
    @JsonProperty("service-short-name")
    private String service_short_name;

    public Rpm() {
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setRpm_repository_name(String rpm_repository_name) {
        this.rpm_repository_name = rpm_repository_name;
    }

    public void setHashes(Hashes hashes) {
        this.hashes = hashes;
    }

    public void setService_short_name(String service_short_name) {
        this.service_short_name = service_short_name;
    }

    public String getUrl() {
        return url;
    }

    public String getRpm_repository_name() {
        return rpm_repository_name;
    }

    public Hashes getHashes() {
        return hashes;
    }

    public String getService_short_name() {
        return service_short_name;
    }

    @Override
    public String toString() {
        return "Rpm{" +
                "url='" + url + '\'' +
                ", rpm_repository_name='" + rpm_repository_name + '\'' +
                ", hashes=" + hashes +
                ", service_short_name='" + service_short_name + '\'' +
                '}';
    }
}

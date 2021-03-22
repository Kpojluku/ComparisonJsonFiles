package com.goltsov.model.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class Artifacts {
    private Mvn [] mvn;
    @JsonProperty("service-short-name")
    private String service_short_name;
    private String service_name;
    private Hashes hashes;
    private String[] file;
    private String target_repository;

    public Artifacts() {
    }

    public Mvn[] getMvn() {
        return mvn;
    }

    public void setMvn(Mvn[] mvn) {
        this.mvn = mvn;
    }

    public String getService_short_name() {
        return service_short_name;
    }

    public void setService_short_name(String service_short_name) {
        this.service_short_name = service_short_name;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public Hashes getHashes() {
        return hashes;
    }

    public void setHashes(Hashes hashes) {
        this.hashes = hashes;
    }

    public String[] getFile() {
        return file;
    }

    public void setFile(String[] file) {
        this.file = file;
    }

    public String getTarget_repository() {
        return target_repository;
    }

    public void setTarget_repository(String target_repository) {
        this.target_repository = target_repository;
    }

    @Override
    public String toString() {
        return "Artifacts{" +
                "mvn=" + Arrays.toString(mvn) +
                ", service_short_name='" + service_short_name + '\'' +
                ", service_name='" + service_name + '\'' +
                ", hashes=" + hashes +
                ", file=" + Arrays.toString(file) +
                ", target_repository='" + target_repository + '\'' +
                '}';
    }
}

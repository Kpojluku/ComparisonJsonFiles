package com.goltsov.model.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Script {
    @JsonProperty("service-short-name")
    private String service_short_name;
    @JsonProperty("start-point")
    private String start_point;
    @JsonProperty("end-point")
    private String end_point;
    private String script_name;
    private Hashes hashes;
    private String url;

    public void setService_short_name(String service_short_name) {
        this.service_short_name = service_short_name;
    }

    public void setStart_point(String start_point) {
        this.start_point = start_point;
    }

    public void setEnd_point(String end_point) {
        this.end_point = end_point;
    }

    public void setScript_name(String script_name) {
        this.script_name = script_name;
    }

    public void setHashes(Hashes hashes) {
        this.hashes = hashes;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getService_short_name() {
        return service_short_name;
    }

    public String getStart_point() {
        return start_point;
    }

    public String getEnd_point() {
        return end_point;
    }

    public String getScript_name() {
        return script_name;
    }

    public Hashes getHashes() {
        return hashes;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Script{" +
                "service_short_name='" + service_short_name + '\'' +
                ", start_point='" + start_point + '\'' +
                ", end_point='" + end_point + '\'' +
                ", script_name='" + script_name + '\'' +
                ", hashes=" + hashes +
                ", url='" + url + '\'' +
                '}';
    }
}

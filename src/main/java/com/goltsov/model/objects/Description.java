package com.goltsov.model.objects;

public class Description {
    private Integer version;

    public Description() {
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Description{" +
                "version=" + version +
                '}';
    }
}

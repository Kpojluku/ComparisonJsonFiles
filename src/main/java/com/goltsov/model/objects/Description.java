package com.goltsov.model.objects;

public class Description {
    private int version;

    public Description() {
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Description{" +
                "version=" + version +
                '}';
    }
}

package com.goltsov.model.objects;

public class Metadata {
    Description description;
    Application application;

    public Metadata() {
    }

    public Description getDescription() {
        return description;
    }

    public Application getApplication() {
        return application;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    @Override
    public String toString() {
        return "Metadata{" +
                "description=" + description +
                ", application=" + application +
                '}';
    }
}

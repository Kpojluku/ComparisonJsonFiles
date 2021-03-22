package com.goltsov.model.objects;

public class Application {
    private String name;

    public Application() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Application{" +
                "name='" + name + '\'' +
                '}';
    }
}

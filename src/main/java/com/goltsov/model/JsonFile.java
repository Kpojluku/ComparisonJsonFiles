package com.goltsov.model;

import com.goltsov.model.objects.*;

import java.util.Arrays;

public class JsonFile {
    private Metadata metadata;
    private Services[] services;
    private Artifacts[] artifacts;
    private Script[] script;
    private Rpm rpm;
    private Parameters parameters;

    public JsonFile() {
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public Services[] getServices() {
        return services;
    }

    public void setServices(Services[] services) {
        this.services = services;
    }

    public Artifacts[] getArtifacts() {
        return artifacts;
    }

    public void setArtifacts(Artifacts[] artifacts) {
        this.artifacts = artifacts;
    }

    public Script[] getScript() {
        return script;
    }

    public void setScript(Script[] script) {
        this.script = script;
    }

    public Rpm getRpm() {
        return rpm;
    }

    public void setRpm(Rpm rpm) {
        this.rpm = rpm;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "JsonFile{" +
                "metadata=" + metadata + "\n" +
                ", services=" + Arrays.toString(services) + "\n" +
                ", artifacts=" + Arrays.toString(artifacts) + "\n" +
                ", script=" + Arrays.toString(script) + "\n" +
                ", rpm=" + rpm + "\n" +
                ", parameters=" + parameters +
                '}';
    }
}

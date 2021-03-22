package com.goltsov.model.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Services {
    @JsonProperty("service-short-name")
    private String service_short_name;
    private String service_name;
    private String artifact_type;
    private String docker_registry;
    private String docker_image_name;
    private String docker_tag;
    private boolean force;
    private String github_repository;
    private String github_branch;
    private String github_hash;
    private Hashes hashes;

    public void setService_short_name(String service_short_name) {
        this.service_short_name = service_short_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public void setArtifact_type(String artifact_type) {
        this.artifact_type = artifact_type;
    }

    public void setDocker_registry(String docker_registry) {
        this.docker_registry = docker_registry;
    }

    public void setDocker_image_name(String docker_image_name) {
        this.docker_image_name = docker_image_name;
    }

    public void setDocker_tag(String docker_tag) {
        this.docker_tag = docker_tag;
    }

    public void setForce(boolean force) {
        this.force = force;
    }

    public void setGithub_repository(String github_repository) {
        this.github_repository = github_repository;
    }

    public void setGithub_branch(String github_branch) {
        this.github_branch = github_branch;
    }

    public void setGithub_hash(String github_hash) {
        this.github_hash = github_hash;
    }

    public void setHashes(Hashes hashes) {
        this.hashes = hashes;
    }

    public String getService_short_name() {
        return service_short_name;
    }

    public String getService_name() {
        return service_name;
    }

    public String getArtifact_type() {
        return artifact_type;
    }

    public String getDocker_registry() {
        return docker_registry;
    }

    public String getDocker_image_name() {
        return docker_image_name;
    }

    public String getDocker_tag() {
        return docker_tag;
    }

    public boolean isForce() {
        return force;
    }

    public String getGithub_repository() {
        return github_repository;
    }

    public String getGithub_branch() {
        return github_branch;
    }

    public String getGithub_hash() {
        return github_hash;
    }

    public Hashes getHashes() {
        return hashes;
    }

    @Override
    public String toString() {
        return "Services{" +
                "service_short_name='" + service_short_name + '\'' +
                ", service_name='" + service_name + '\'' +
                ", artifact_type='" + artifact_type + '\'' +
                ", docker_registry='" + docker_registry + '\'' +
                ", docker_image_name='" + docker_image_name + '\'' +
                ", docker_tag='" + docker_tag + '\'' +
                ", force=" + force +
                ", github_repository='" + github_repository + '\'' +
                ", github_branch='" + github_branch + '\'' +
                ", github_hash='" + github_hash + '\'' +
                ", hashes=" + hashes +
                '}';
    }
}

package com.goltsov.model;

public class KeyFields {
    private boolean servicesDoesNotExist;
    private boolean metadataDoesNotExist;
    private boolean artifactsDoesNotExist;
    private boolean scriptDoesNotExist;
    private boolean rpmDoesNotExist;
    private Integer[] docker_image_name;
    private Integer[] docker_tag;
    private boolean MvnMissing;
    private Integer[] groupId;
    private Integer[] artifactId;
    private Integer[] version;
    private Integer[] mvn_type;
    private Integer[] files;
    private Integer[] urlScript;
    private Integer[] urlRpm;

    public boolean isRpmDoesNotExist() {
        return rpmDoesNotExist;
    }

    public void setRpmDoesNotExist(boolean rpmDoesNotExist) {
        this.rpmDoesNotExist = rpmDoesNotExist;
    }

    public boolean isScriptDoesNotExist() {
        return scriptDoesNotExist;
    }

    public void setScriptDoesNotExist(boolean scriptDoesNotExist) {
        this.scriptDoesNotExist = scriptDoesNotExist;
    }

    public boolean isArtifactsDoesNotExist() {
        return artifactsDoesNotExist;
    }

    public void setArtifactsDoesNotExist(boolean artifactsDoesNotExist) {
        this.artifactsDoesNotExist = artifactsDoesNotExist;
    }

    public boolean isMetadataDoesNotExist() {
        return metadataDoesNotExist;
    }

    public void setMetadataDoesNotExist(boolean metadataDoesNotExist) {
        this.metadataDoesNotExist = metadataDoesNotExist;
    }

    public boolean isServicesDoesNotExist() {
        return servicesDoesNotExist;
    }

    public void setServicesDoesNotExist(boolean servicesDoesNotExist) {
        this.servicesDoesNotExist = servicesDoesNotExist;
    }

    public Integer[] getUrlScript() {
        return urlScript;
    }

    public void setUrlScript(Integer[] urlScript) {
        this.urlScript = urlScript;
    }

    public Integer[] getUrlRpm() {
        return urlRpm;
    }

    public void setUrlRpm(Integer[] urlRpm) {
        this.urlRpm = urlRpm;
    }

    public boolean isMvnMissing() {
        return MvnMissing;
    }

    public void setMvnMissing(boolean mvnMissing) {
        MvnMissing = mvnMissing;
    }

    public Integer[] getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer[] groupId) {
        this.groupId = groupId;
    }

    public Integer[] getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(Integer[] artifactId) {
        this.artifactId = artifactId;
    }

    public Integer[] getVersion() {
        return version;
    }

    public void setVersion(Integer[] version) {
        this.version = version;
    }

    public Integer[] getMvn_type() {
        return mvn_type;
    }

    public void setMvn_type(Integer[] mvn_type) {
        this.mvn_type = mvn_type;
    }

    public Integer[] getFiles() {
        return files;
    }

    public void setFiles(Integer[] files) {
        this.files = files;
    }

    public Integer[] getDocker_image_name() {
        return docker_image_name;
    }

    public void setDocker_image_name(Integer[] docker_image_name) {
        this.docker_image_name = docker_image_name;
    }

    public Integer[] getDocker_tag() {
        return docker_tag;
    }

    public void setDocker_tag(Integer[] docker_tag) {
        this.docker_tag = docker_tag;
    }
}

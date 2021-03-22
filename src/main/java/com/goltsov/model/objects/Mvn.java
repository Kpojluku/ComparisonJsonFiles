package com.goltsov.model.objects;

public class Mvn {
    private String groupId;
    private String artifactId;
    private String version;
    private String service_name;
    private String classifier;
    private String mvn_type;
    private String mvn_repository;
    private Hashes hashes;

    public Mvn() {
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public void setClassifier(String classifier) {
        this.classifier = classifier;
    }

    public void setMvn_type(String mvn_type) {
        this.mvn_type = mvn_type;
    }

    public void setMvn_repository(String mvn_repository) {
        this.mvn_repository = mvn_repository;
    }

    public void setHashes(Hashes hashes) {
        this.hashes = hashes;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public String getVersion() {
        return version;
    }

    public String getService_name() {
        return service_name;
    }

    public String getClassifier() {
        return classifier;
    }

    public String getMvn_type() {
        return mvn_type;
    }

    public String getMvn_repository() {
        return mvn_repository;
    }

    public Hashes getHashes() {
        return hashes;
    }

    @Override
    public String toString() {
        return "Mvn{" +
                "groupId='" + groupId + '\'' +
                ", artifactId='" + artifactId + '\'' +
                ", version='" + version + '\'' +
                ", service_name='" + service_name + '\'' +
                ", classifier='" + classifier + '\'' +
                ", mvn_type='" + mvn_type + '\'' +
                ", mvn_repository='" + mvn_repository + '\'' +
                ", hashes=" + hashes +
                '}';
    }
}

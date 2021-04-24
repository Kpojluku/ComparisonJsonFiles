package com.goltsov.model;

public class TechInformation {
    private int servicesLength;
    private int artifactsLength;
    private int scriptLength;
    private int commonLength;
    private int rpmLength;
    private String[] commonKeys1;
    private String[] commonValues1;
    private String[] commonKeys2;
    private String[] commonValues2;
    private int serviceNameLength;
    private String[] serviceKeys1;
    private String[] serviceValues1;
    private String[] serviceKeys2;
    private String[] serviceValues2;
    private boolean MetadataVersionEqual;
    private boolean MetadataNameEqual;
    private int mvnLength;
    private int minMvnLength;
    private boolean[] isMvnEqual;


    public int getRpmLength() {
        return rpmLength;
    }

    public void setRpmLength(int rpmLength) {
        this.rpmLength = rpmLength;
    }

    public boolean[] getIsMvnEqual() {
        return isMvnEqual;
    }

    public void setIsMvnEqual(boolean[] isMvnEqual) {
        this.isMvnEqual = isMvnEqual;
    }

    public int getMinMvnLength() {
        return minMvnLength;
    }

    public void setMinMvnLength(int minMvnLength) {
        this.minMvnLength = minMvnLength;
    }

    public int getMvnLength() {
        return mvnLength;
    }

    public void setMvnLength(int mvnLength) {
        this.mvnLength = mvnLength;
    }

    public boolean isMetadataNameEqual() {
        return MetadataNameEqual;
    }

    public void setMetadataNameEqual(boolean metadataNameEqual) {
        MetadataNameEqual = metadataNameEqual;
    }

    public boolean isMetadataVersionEqual() {
        return MetadataVersionEqual;
    }

    public void setMetadataVersionEqual(boolean metadataVersionEqual) {
        MetadataVersionEqual = metadataVersionEqual;
    }

    public int getServiceNameLength() {
        return serviceNameLength;
    }

    public void setServiceNameLength(int serviceNameLength) {
        this.serviceNameLength = serviceNameLength;
    }

    public String[] getServiceKeys1() {
        return serviceKeys1;
    }

    public void setServiceKeys1(String[] serviceKeys1) {
        this.serviceKeys1 = serviceKeys1;
    }

    public String[] getServiceValues1() {
        return serviceValues1;
    }

    public void setServiceValues1(String[] serviceValues1) {
        this.serviceValues1 = serviceValues1;
    }

    public String[] getServiceKeys2() {
        return serviceKeys2;
    }

    public void setServiceKeys2(String[] serviceKeys2) {
        this.serviceKeys2 = serviceKeys2;
    }

    public String[] getServiceValues2() {
        return serviceValues2;
    }

    public void setServiceValues2(String[] serviceValues2) {
        this.serviceValues2 = serviceValues2;
    }

    public String[] getCommonKeys1() {
        return commonKeys1;
    }

    public void setCommonKeys1(String[] commonKeys1) {
        this.commonKeys1 = commonKeys1;
    }

    public String[] getCommonValues1() {
        return commonValues1;
    }

    public void setCommonValues1(String[] commonValues1) {
        this.commonValues1 = commonValues1;
    }

    public String[] getCommonKeys2() {
        return commonKeys2;
    }

    public void setCommonKeys2(String[] commonKeys2) {
        this.commonKeys2 = commonKeys2;
    }

    public String[] getCommonValues2() {
        return commonValues2;
    }

    public void setCommonValues2(String[] commonValues2) {
        this.commonValues2 = commonValues2;
    }

    public int getCommonLength() {
        return commonLength;
    }

    public void setCommonLength(int commonLength) {
        this.commonLength = commonLength;
    }

    public int getScriptLength() {
        return scriptLength;
    }

    public void setScriptLength(int scriptLength) {
        this.scriptLength = scriptLength;
    }

    public int getServicesLength() {
        return servicesLength;
    }

    public void setServicesLength(int servicesLength) {
        this.servicesLength = servicesLength;
    }

    public int getArtifactsLength() {
        return artifactsLength;
    }

    public void setArtifactsLength(int artifactsLength) {
        this.artifactsLength = artifactsLength;
    }
}

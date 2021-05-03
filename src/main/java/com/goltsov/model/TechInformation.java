package com.goltsov.model;

import java.util.ArrayList;

public class TechInformation {
    private int servicesLength;
    private int artifactsLength;
    private int scriptLength;
    private int commonLength;
    private int rpmLength;
    private String[] commonKeys1;
    private String[] commonKeys2;
    private int serviceNameLength;
    private int minServiceNameLength;
    private String[] serviceKeys1;
    private String[] serviceKeys2;
    private boolean MetadataVersionEqual;
    private boolean MetadataNameEqual;
    private int mvnLength;
    private int minMvnLength;
    private boolean[] isMvnEqual;
    private ArrayList<String[]> servicePrmKeys1;
    private ArrayList<String[]> servicePrmKeys2;
    private int[] maxServPrmArraysLength;
    private String lightskyblue = "background: lightskyblue;";
    private String indianred = "background: indianred;";
    private String greenyellow = "background: greenyellow;";
    private String valueIsNull = "background: #ff9300;";

    public String getValueIsNull() {
        return valueIsNull;
    }

    public void setValueIsNull(String valueIsNull) {
        this.valueIsNull = valueIsNull;
    }

    public String getGreenyellow() {
        return greenyellow;
    }

    public void setGreenyellow(String greenyellow) {
        this.greenyellow = greenyellow;
    }

    public String getIndianred() {
        return indianred;
    }

    public void setIndianred(String indianred) {
        this.indianred = indianred;
    }

    public String getLightskyblue() {
        return lightskyblue;
    }

    public void setLightskyblue(String lightskyblue) {
        this.lightskyblue = lightskyblue;
    }

    public int[] getMaxServPrmArraysLength() {
        return maxServPrmArraysLength;
    }

    public void setMaxServPrmArraysLength(int[] maxServPrmArraysLength) {
        this.maxServPrmArraysLength = maxServPrmArraysLength;
    }

    public ArrayList<String[]> getServicePrmKeys1() {
        return servicePrmKeys1;
    }

    public void setServicePrmKeys1(ArrayList<String[]> serviceParamKeys1) {
        this.servicePrmKeys1 = serviceParamKeys1;
    }

    public ArrayList<String[]> getServicePrmKeys2() {
        return servicePrmKeys2;
    }

    public void setServicePrmKeys2(ArrayList<String[]> serviceParamKeys2) {
        this.servicePrmKeys2 = serviceParamKeys2;
    }

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

    public int getMinServiceNameLength() {
        return minServiceNameLength;
    }

    public void setMinServiceNameLength(int minServiceNameLength) {
        this.minServiceNameLength = minServiceNameLength;
    }

    public String[] getServiceKeys1() {
        return serviceKeys1;
    }

    public void setServiceKeys1(String[] serviceKeys1) {
        this.serviceKeys1 = serviceKeys1;
    }

    public String[] getServiceKeys2() {
        return serviceKeys2;
    }

    public void setServiceKeys2(String[] serviceKeys2) {
        this.serviceKeys2 = serviceKeys2;
    }

    public String[] getCommonKeys1() {
        return commonKeys1;
    }

    public void setCommonKeys1(String[] commonKeys1) {
        this.commonKeys1 = commonKeys1;
    }

    public String[] getCommonKeys2() {
        return commonKeys2;
    }

    public void setCommonKeys2(String[] commonKeys2) {
        this.commonKeys2 = commonKeys2;
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

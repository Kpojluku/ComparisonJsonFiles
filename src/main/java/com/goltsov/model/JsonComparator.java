package com.goltsov.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JsonComparator {

    public JsonFile getJsonFile(MultipartFile file) throws IOException {
        String content = new String(file.getBytes(), StandardCharsets.UTF_8);
        return getJsonFile(content);
    }

    public JsonFile getJsonFile(String json) throws JsonProcessingException {
        JsonFile jsonFile;
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        jsonFile = mapper.readValue(json, JsonFile.class);

        return jsonFile;
    }

    public void setJsonFile(JsonFile jsonFile, JsonFile finalFile) {
        finalFile.setMetadata(jsonFile.getMetadata());
        finalFile.setServices(jsonFile.getServices());
        finalFile.setArtifacts(jsonFile.getArtifacts());
        finalFile.setScript(jsonFile.getScript());
        finalFile.setRpm(jsonFile.getRpm());
        finalFile.setParameters(jsonFile.getParameters());
    }

    public String compare(JsonFile jsonFile1, JsonFile jsonFile2, TechInformation techInformation) {

        setMaxServicesLength(jsonFile1, jsonFile2, techInformation);

        setMaxArtifactsLength(jsonFile1, jsonFile2, techInformation);

        setMaxScriptLength(jsonFile1, jsonFile2, techInformation);

        setMaxCommonLength(jsonFile1, jsonFile2, techInformation);

        setMaxServiceNameLength(jsonFile1, jsonFile2, techInformation);

        return "report";
    }

    private void setMaxServiceNameLength(JsonFile jsonFile1, JsonFile jsonFile2, TechInformation techInformation) {
        if (jsonFile1.getParameters() != null && jsonFile1.getParameters().getServices() != null &&
                jsonFile1.getParameters().getServices().getService_name() != null
        ) {

            techInformation.setServiceKeys1(jsonFile1.getParameters().getServices().getService_name().keySet().toArray(new String[0]));
            techInformation.setServiceValues1(jsonFile1.getParameters().getServices().getService_name().values().toArray(new String[0]));

            techInformation.setServiceNameLength(jsonFile1.getParameters().getServices().getService_name().size());
        }
        if (jsonFile2.getParameters() != null &&
                jsonFile2.getParameters().getServices() != null &&
                jsonFile2.getParameters().getServices().getService_name() != null) {

            techInformation.setServiceKeys2(jsonFile2.getParameters().getServices().getService_name().keySet().toArray(new String[0]));
            techInformation.setServiceValues2(jsonFile2.getParameters().getServices().getService_name().values().toArray(new String[0]));

            if (jsonFile2.getParameters().getServices().getService_name().size() > techInformation.getServiceNameLength()) {
                techInformation.setServiceNameLength(jsonFile2.getParameters().getServices().getService_name().size());
            }
        }

    }

    private void setMaxCommonLength(JsonFile jsonFile1, JsonFile jsonFile2, TechInformation techInformation) {
        if (jsonFile1.getParameters() != null && jsonFile1.getParameters().getCommon() != null) {

            techInformation.setCommonKeys1(jsonFile1.getParameters().getCommon().keySet().toArray(new String[0]));
            techInformation.setCommonValues1(jsonFile1.getParameters().getCommon().values().toArray(new String[0]));

            techInformation.setCommonLength(jsonFile1.getParameters().getCommon().size());
        }
        if (jsonFile2.getParameters() != null &&
                jsonFile2.getParameters().getCommon() != null) {
            techInformation.setCommonKeys2(jsonFile2.getParameters().getCommon().keySet().toArray(new String[0]));
            techInformation.setCommonValues2(jsonFile2.getParameters().getCommon().values().toArray(new String[0]));

            if (jsonFile2.getParameters().getCommon().size() > techInformation.getCommonLength()) {
                techInformation.setCommonLength(jsonFile2.getParameters().getCommon().size());
            }
        }
    }

    private void setMaxScriptLength(JsonFile jsonFile1, JsonFile jsonFile2, TechInformation techInformation) {
        if (jsonFile1.getScript() != null) {
            techInformation.setScriptLength(jsonFile1.getScript().length);
        }
        if (jsonFile2.getScript() != null && jsonFile2.getScript().length > techInformation.getScriptLength()) {
            techInformation.setScriptLength(jsonFile2.getScript().length);
        }
    }

    private void setMaxArtifactsLength(JsonFile jsonFile1, JsonFile jsonFile2, TechInformation techInformation) {
        if (jsonFile1.getArtifacts() != null) {
            techInformation.setArtifactsLength(jsonFile1.getArtifacts().length);
        }
        if (jsonFile2.getArtifacts() != null && jsonFile2.getArtifacts().length > techInformation.getArtifactsLength()) {
            techInformation.setArtifactsLength(jsonFile2.getArtifacts().length);
        }
    }

    private void setMaxServicesLength(JsonFile jsonFile1, JsonFile jsonFile2, TechInformation techInformation) {
        if (jsonFile1.getServices() != null) {
            techInformation.setServicesLength(jsonFile1.getServices().length);
        }
        if (jsonFile2.getServices() != null && jsonFile2.getServices().length > techInformation.getServicesLength()) {
            techInformation.setServicesLength(jsonFile2.getServices().length);
        }
    }


}

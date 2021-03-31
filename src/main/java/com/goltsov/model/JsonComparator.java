package com.goltsov.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goltsov.model.objects.TechInformation;
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

        if (jsonFile1.getServices() != null) {
            techInformation.setServicesLength(jsonFile1.getServices().length);
        }
        if (jsonFile2.getServices() != null && jsonFile2.getServices().length > techInformation.getServicesLength()) {
            techInformation.setServicesLength(jsonFile2.getServices().length);
        }


        return "report";
    }


}

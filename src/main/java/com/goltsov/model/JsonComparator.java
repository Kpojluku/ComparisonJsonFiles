package com.goltsov.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JsonComparator {

public JsonFile getJsonFile(MultipartFile file)throws IOException {
    String content = new String(file.getBytes(), StandardCharsets.UTF_8);
    return getJsonFile(content);
}
    public JsonFile getJsonFile(String json){
    JsonFile jsonFile = new JsonFile();
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        try {
            jsonFile = mapper.readValue(json, JsonFile.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return jsonFile;
    }
}

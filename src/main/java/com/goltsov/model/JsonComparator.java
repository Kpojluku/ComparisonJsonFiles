package com.goltsov.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goltsov.model.objects.Mvn;
import com.goltsov.model.objects.Rpm;
import com.goltsov.model.objects.Script;
import com.goltsov.model.objects.Services;
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

        setMaxRpmLength(jsonFile1, jsonFile2, techInformation);

        techInformation.setMetadataVersionEqual(jsonFile1.getMetadata().getDescription().getVersion() ==
                jsonFile2.getMetadata().getDescription().getVersion());

        techInformation.setMetadataNameEqual(jsonFile1.getMetadata().getApplication().getName().
                equals(jsonFile2.getMetadata().getApplication().getName()));


        //сортировка массива для Services
        if (jsonFile2.getServices().length >= jsonFile1.getServices().length) {
            sortServices(jsonFile1, jsonFile2);
        } else {
            sortServices(jsonFile2, jsonFile1);
        }

        //сортировка массива универсально
        // sortServices2(jsonFile1, jsonFile2);

        //сортировка массива для mvn
        if (jsonFile2.getArtifacts()[0].getMvn().length >= jsonFile1.getArtifacts()[0].getMvn().length) {
            sortMvn(jsonFile1, jsonFile2);
        } else {
            sortMvn(jsonFile2, jsonFile1);
        }

        //сортировка массива для script
        if (jsonFile2.getScript().length >= jsonFile1.getScript().length) {
            sortScript(jsonFile1, jsonFile2);
        } else {
            sortScript(jsonFile2, jsonFile1);
        }
        //сортировка массива для rpm
        if (jsonFile2.getRpm().length >= jsonFile1.getRpm().length) {
            sortRpm(jsonFile1, jsonFile2);
        } else {
            sortRpm(jsonFile2, jsonFile1);
        }

        // для artifacts максимальный размер массива mvn
        if (jsonFile1.getArtifacts() != null) {
            techInformation.setMvnLength(jsonFile1.getArtifacts()[0].getMvn().length);
        }
        if (jsonFile2.getArtifacts() != null && jsonFile2.getArtifacts()[0].getMvn().length > techInformation.getMvnLength()) {
            techInformation.setMvnLength(jsonFile2.getArtifacts()[0].getMvn().length);
        }
        // минимальный размер
        if (jsonFile1.getArtifacts() != null) {
            techInformation.setMinMvnLength(jsonFile1.getArtifacts()[0].getMvn().length);
        }
        if (jsonFile2.getArtifacts() != null && jsonFile2.getArtifacts()[0].getMvn().length < techInformation.getMinMvnLength()) {
            techInformation.setMinMvnLength(jsonFile2.getArtifacts()[0].getMvn().length);
        }
        // заполняем массив isMvnEqual , чтобы узнать какие зависимости отличаются
        boolean[] checkMvn = new boolean[techInformation.getMinMvnLength()];
        for (int i = 0; i < techInformation.getMinMvnLength(); i++) {
            checkMvn[i] = jsonFile1.getArtifacts()[0].getMvn()[i].getGroupId().equals(jsonFile2.getArtifacts()[0].getMvn()[i].getGroupId()) &&
                    jsonFile1.getArtifacts()[0].getMvn()[i].getArtifactId().equals(jsonFile2.getArtifacts()[0].getMvn()[i].getArtifactId()) &&
                    jsonFile1.getArtifacts()[0].getMvn()[i].getVersion().equals(jsonFile2.getArtifacts()[0].getMvn()[i].getVersion()) &&
                    jsonFile1.getArtifacts()[0].getMvn()[i].getMvn_type().equals(jsonFile2.getArtifacts()[0].getMvn()[i].getMvn_type());
        }
        techInformation.setIsMvnEqual(checkMvn);


        return "report";
    }


    private void setMaxRpmLength(JsonFile jsonFile1, JsonFile jsonFile2, TechInformation techInformation) {
        if (jsonFile1.getRpm() != null) {
            techInformation.setRpmLength(jsonFile1.getRpm().length);
        }
        if (jsonFile2.getRpm() != null && jsonFile2.getRpm().length > techInformation.getRpmLength()) {
            techInformation.setRpmLength(jsonFile2.getRpm().length);
        }
    }


    private void sortServices2(JsonFile jsonFile1, JsonFile jsonFile2) {
        String[] keys1 = new String[jsonFile1.getServices().length];
        for (int i = 0; i < keys1.length; i++) {
            keys1[i] = jsonFile1.getServices()[i].getDocker_image_name() + jsonFile1.getServices()[i].getDocker_tag();
        }
        String[] keys2 = new String[jsonFile2.getServices().length];
        for (int i = 0; i < keys2.length; i++) {
            keys2[i] = jsonFile2.getServices()[i].getDocker_image_name() + jsonFile2.getServices()[i].getDocker_tag();
        }
        if (jsonFile2.getServices().length >= jsonFile1.getServices().length) {
            sortArr(jsonFile1.getServices(), jsonFile2.getServices(), keys1, keys2);
        } else {
            sortArr(jsonFile2.getServices(), jsonFile1.getServices(), keys2, keys1);
        }
    }

    private void sortArr(Object[] arr1, Object[] arr2, String[] keys1, String[] keys2) {
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                if (keys1[i].equals(keys2[j])) {
                    Object tmp2 = arr2[i];
                    arr2[i] = arr2[j];
                    arr2[j] = tmp2;
                }
            }
        }
    }

    private void sortServices(JsonFile jsonFile1, JsonFile jsonFile2) {
        //сортировка массива для Services
        for (int i = 0; i < jsonFile1.getServices().length; i++) {
            for (int j = 0; j < jsonFile2.getServices().length; j++) {
                if (jsonFile1.getServices()[i].getDocker_image_name().equals(jsonFile2.getServices()[j].getDocker_image_name())
                        && jsonFile1.getServices()[i].getDocker_tag().equals(jsonFile2.getServices()[j].getDocker_tag())) {
                    Services tmp2 = jsonFile2.getServices()[i];
                    jsonFile2.getServices()[i] = jsonFile2.getServices()[j];
                    jsonFile2.getServices()[j] = tmp2;
                }
            }
        }
    }
    private void sortMvn(JsonFile jsonFile1, JsonFile jsonFile2) {
        for (int i = 0; i < jsonFile1.getArtifacts()[0].getMvn().length; i++) {
            for (int j = 0; j < jsonFile2.getArtifacts()[0].getMvn().length; j++) {
                if (jsonFile1.getArtifacts()[0].getMvn()[i].getGroupId().equals(jsonFile2.getArtifacts()[0].getMvn()[j].getGroupId()) &&
                        jsonFile1.getArtifacts()[0].getMvn()[i].getArtifactId().equals(jsonFile2.getArtifacts()[0].getMvn()[j].getArtifactId()) &&
                        jsonFile1.getArtifacts()[0].getMvn()[i].getVersion().equals(jsonFile2.getArtifacts()[0].getMvn()[j].getVersion()) &&
                        jsonFile1.getArtifacts()[0].getMvn()[i].getMvn_type().equals(jsonFile2.getArtifacts()[0].getMvn()[j].getMvn_type())) {
                    Mvn tmp2 = jsonFile2.getArtifacts()[0].getMvn()[i];
                    jsonFile2.getArtifacts()[0].getMvn()[i] = jsonFile2.getArtifacts()[0].getMvn()[j];
                    jsonFile2.getArtifacts()[0].getMvn()[j] = tmp2;
                }
            }
        }
    }
    private void sortScript(JsonFile jsonFile1, JsonFile jsonFile2) {
        for (int i = 0; i < jsonFile1.getScript().length; i++) {
            for (int j = 0; j < jsonFile2.getScript().length; j++) {
                if (jsonFile1.getScript()[i].getUrl().equals(jsonFile2.getScript()[j].getUrl())) {
                    Script tmp2 = jsonFile2.getScript()[i];
                    jsonFile2.getScript()[i] = jsonFile2.getScript()[j];
                    jsonFile2.getScript()[j] = tmp2;
                }
            }
        }
    }

    private void sortRpm(JsonFile jsonFile1, JsonFile jsonFile2) {
        for (int i = 0; i < jsonFile1.getRpm().length; i++) {
            for (int j = 0; j < jsonFile2.getRpm().length; j++) {
                if (jsonFile1.getRpm()[i].getUrl().equals(jsonFile2.getRpm()[j].getUrl())) {
                    Rpm tmp2 = jsonFile2.getRpm()[i];
                    jsonFile2.getRpm()[i] = jsonFile2.getRpm()[j];
                    jsonFile2.getRpm()[j] = tmp2;
                }
            }
        }
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

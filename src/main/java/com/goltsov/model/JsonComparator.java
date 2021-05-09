package com.goltsov.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goltsov.model.objects.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;

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

    public String compare(JsonFile jsonFile1, JsonFile jsonFile2, TechInformation techInformation,
                          KeyFields keyFields1, KeyFields keyFields2) {

        if (checkMandatoryFields(jsonFile1, keyFields1)) {
            checkMandatoryFields(jsonFile2, keyFields2);
            return "mandatoryFields";
        } else if (checkMandatoryFields(jsonFile2, keyFields2)) {
            checkMandatoryFields(jsonFile1, keyFields1);
            return "mandatoryFields";
        }

        setMaxServicesLength(jsonFile1, jsonFile2, techInformation);

        setMaxArtifactsLength(jsonFile1, jsonFile2, techInformation);

        setMaxScriptLength(jsonFile1, jsonFile2, techInformation);

        setMaxCommonLength(jsonFile1, jsonFile2, techInformation);

        setMaxServiceNameLength(jsonFile1, jsonFile2, techInformation);

        setMinServiceNameLength(jsonFile1, jsonFile2, techInformation);

        setMaxRpmLength(jsonFile1, jsonFile2, techInformation);

        //Проверка на равенство metadata - version, name
        checkMetadata(jsonFile1, jsonFile2, techInformation);


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
        //Сортировка массива для Artifacts
        if (jsonFile2.getArtifacts().length >= jsonFile1.getArtifacts().length) {
            sortArtifacts(jsonFile1, jsonFile2);
        } else {
            sortArtifacts(jsonFile2, jsonFile1);
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

        //сортировка массива ключей для Common
        if (techInformation.getCommonKeys2().length >= techInformation.getCommonKeys1().length) {
            sortKeys(techInformation.getCommonKeys1(), techInformation.getCommonKeys2());
        } else {
            sortKeys(techInformation.getCommonKeys2(), techInformation.getCommonKeys1());
        }

        //сортировка массива ключей для Services
        if (techInformation.getServiceKeys2().length >= techInformation.getServiceKeys1().length) {
            sortKeys(techInformation.getServiceKeys1(), techInformation.getServiceKeys2());
        } else {
            sortKeys(techInformation.getServiceKeys2(), techInformation.getServiceKeys1());
        }

        // сортируем ключи параметров и сохраняем в список servicePrmKeys
        sortServicesPrmKeys(jsonFile1, jsonFile2, techInformation);


        // для artifacts максимальный размер массива mvn
        setMaxMvnLength(jsonFile1, jsonFile2, techInformation);
        // минимальный размер для массива Mvn
        setMinMvnLength(jsonFile1, jsonFile2, techInformation);
        // заполняем массив isMvnEqual , чтобы узнать какие зависимости отличаются
        fillIsMvnEqualArr(jsonFile1, jsonFile2, techInformation);


        return "report";
    }

    private boolean checkMandatoryFields(JsonFile jsonFile, KeyFields keyFields) {
        boolean result = false;
        // Проверка Services
        checkServicesFields(jsonFile, keyFields);
        if (checkServicesFields(jsonFile, keyFields)) {
            result = true;
        }
        // Проверка Artifacts
        if (checkArtifactsFields(jsonFile, keyFields)) {
            result = true;
        }
        if (checkScriptFields(jsonFile, keyFields)) {
            result = true;
        }
        if (checkRpmFields(jsonFile, keyFields)) {
            result = true;
        }

        return result;
    }

    private boolean checkRpmFields(JsonFile json, KeyFields keyFields) {
        boolean result = false;
        ArrayList<Integer> url = new ArrayList<>();
        for (int i = 0; i < json.getRpm().length; i++) {
            if (json.getRpm()[i].getUrl() == null) {
                url.add(i);
                result = true;
            }
        }
        keyFields.setUrlRpm(url.toArray(new Integer[0]));
        return result;
    }

    private boolean checkScriptFields(JsonFile json, KeyFields keyFields) {
        boolean result = false;
        ArrayList<Integer> url = new ArrayList<>();
        for (int i = 0; i < json.getScript().length; i++) {
            if (json.getScript()[i].getUrl() == null) {
                url.add(i);
                result = true;
            }
        }
        keyFields.setUrlScript(url.toArray(new Integer[0]));
        return result;
    }

    private boolean checkArtifactsFields(JsonFile json, KeyFields keyFields) {
        boolean result = false;
        if (json.getArtifacts().length > 0 && json.getArtifacts()[0].getMvn() == null) {
            keyFields.setMvnMissing(true);
            result = true;
        }
        ArrayList<Integer> groupId = new ArrayList<>();
        ArrayList<Integer> artifactId = new ArrayList<>();
        ArrayList<Integer> version = new ArrayList<>();
        ArrayList<Integer> mvn_type = new ArrayList<>();
        ArrayList<Integer> file = new ArrayList<>();
        if (!result) {
            for (int i = 0; i < json.getArtifacts()[0].getMvn().length; i++) {
                if (json.getArtifacts()[0].getMvn()[i].getGroupId() == null) {
                    groupId.add(i);
                    result = true;
                }
                if (json.getArtifacts()[0].getMvn()[i].getArtifactId() == null) {
                    artifactId.add(i);
                    result = true;
                }
                if (json.getArtifacts()[0].getMvn()[i].getVersion() == null) {
                    version.add(i);
                    result = true;
                }
                if (json.getArtifacts()[0].getMvn()[i].getMvn_type() == null) {
                    mvn_type.add(i);
                    result = true;
                }
            }
        }
        for (int i = 1; i < json.getArtifacts().length; i++) {
            if (json.getArtifacts()[i].getFile() == null) {
                file.add(i);
                result = true;
            }
        }
        keyFields.setGroupId(groupId.toArray(new Integer[0]));
        keyFields.setArtifactId(artifactId.toArray(new Integer[0]));
        keyFields.setVersion(version.toArray(new Integer[0]));
        keyFields.setMvn_type(mvn_type.toArray(new Integer[0]));
        keyFields.setFiles(file.toArray(new Integer[0]));
        return result;
    }

    private boolean checkServicesFields(JsonFile json, KeyFields keyFields) {
        boolean result = false;
        ArrayList<Integer> docker_image = new ArrayList<>();
        ArrayList<Integer> docker_tag = new ArrayList<>();
        for (int i = 0; i < json.getServices().length; i++) {
            if (json.getServices()[i].getDocker_image_name() == null) {
                // записываем элемент массива i, в числовой массив
                // который хранит инфомарцию о нулевых значениях
                docker_image.add(i);
                result = true;
            }
            if (json.getServices()[i].getDocker_tag() == null) {
                docker_tag.add(i);
                result = true;
            }
        }
        keyFields.setDocker_image_name(docker_image.toArray(new Integer[0]));
        keyFields.setDocker_tag(docker_tag.toArray(new Integer[0]));
        return result;
    }

    private void checkMetadata(JsonFile jsonFile1, JsonFile jsonFile2, TechInformation techInformation) {
        //Проверка на равенство metadata.description.version
        techInformation.setMetadataVersionEqual(jsonFile1.getMetadata().getDescription().getVersion().
                equals(jsonFile2.getMetadata().getDescription().getVersion()));
        //Проверка на равенство metadata.application.name
        techInformation.setMetadataNameEqual(jsonFile1.getMetadata().getApplication().getName().
                equals(jsonFile2.getMetadata().getApplication().getName()));
    }

    private void setMaxMvnLength(JsonFile jsonFile1, JsonFile jsonFile2, TechInformation techInformation) {
        if (jsonFile1.getArtifacts() != null) {
            techInformation.setMvnLength(jsonFile1.getArtifacts()[0].getMvn().length);
        }
        if (jsonFile2.getArtifacts() != null && jsonFile2.getArtifacts()[0].getMvn().length > techInformation.getMvnLength()) {
            techInformation.setMvnLength(jsonFile2.getArtifacts()[0].getMvn().length);
        }
    }

    private void setMinMvnLength(JsonFile jsonFile1, JsonFile jsonFile2, TechInformation techInformation) {
        if (jsonFile1.getArtifacts() != null) {
            techInformation.setMinMvnLength(jsonFile1.getArtifacts()[0].getMvn().length);
        }
        if (jsonFile2.getArtifacts() != null && jsonFile2.getArtifacts()[0].getMvn().length < techInformation.getMinMvnLength()) {
            techInformation.setMinMvnLength(jsonFile2.getArtifacts()[0].getMvn().length);
        }
    }

    private void fillIsMvnEqualArr(JsonFile jsonFile1, JsonFile jsonFile2, TechInformation techInformation) {
        boolean[] checkMvn = new boolean[techInformation.getMinMvnLength()];
        for (int i = 0; i < techInformation.getMinMvnLength(); i++) {
            checkMvn[i] = jsonFile1.getArtifacts()[0].getMvn()[i].getGroupId().equals(jsonFile2.getArtifacts()[0].getMvn()[i].getGroupId()) &&
                    jsonFile1.getArtifacts()[0].getMvn()[i].getArtifactId().equals(jsonFile2.getArtifacts()[0].getMvn()[i].getArtifactId()) &&
                    jsonFile1.getArtifacts()[0].getMvn()[i].getVersion().equals(jsonFile2.getArtifacts()[0].getMvn()[i].getVersion()) &&
                    jsonFile1.getArtifacts()[0].getMvn()[i].getMvn_type().equals(jsonFile2.getArtifacts()[0].getMvn()[i].getMvn_type());
        }
        techInformation.setIsMvnEqual(checkMvn);
    }

    private void sortServicesPrmKeys(JsonFile jsonFile1, JsonFile jsonFile2, TechInformation techInformation) {
        //Список, который хранит ключи - параметры Сервисов для первого файла
        ArrayList<String[]> serviceParamKeys1 = new ArrayList<>();
        ArrayList<String[]> serviceParamKeys2 = new ArrayList<>();
        sortAndAddPrmToList(jsonFile1, serviceParamKeys1, techInformation.getServiceKeys1());
        sortAndAddPrmToList(jsonFile2, serviceParamKeys2, techInformation.getServiceKeys2());

        // цикл по минимальному количеству элементов в Services для сортировки ключей параметров
        for (int i = 0; i < techInformation.getMinServiceNameLength(); i++) {
            if (techInformation.getServiceKeys1()[i].equals(techInformation.getServiceKeys2()[i])) {
                if (serviceParamKeys2.get(i).length >= serviceParamKeys1.get(i).length) {
                    sortKeys(serviceParamKeys1.get(i), serviceParamKeys2.get(i));
                } else {
                    sortKeys(serviceParamKeys2.get(i), serviceParamKeys1.get(i));
                }
            }
        }

        //вспомогательная переменная, хранящая максимальное количество services params из двух файлов
        int[] maxServPrmArraysLength;
        int maxSize = serviceParamKeys1.size();
        ArrayList<String[]> serviceParamKeysMax = serviceParamKeys1;
        if (serviceParamKeys2.size() > maxSize) {
            maxSize = serviceParamKeys2.size();
            serviceParamKeysMax = serviceParamKeys2;
        }
        maxServPrmArraysLength = new int[maxSize];
        for (int i = 0; i < maxSize; i++) {
            if (i < serviceParamKeys1.size() && i < serviceParamKeys2.size()) {
                maxServPrmArraysLength[i] = serviceParamKeys1.get(i).length;
                if (serviceParamKeys2.get(i).length > serviceParamKeys1.get(i).length) {
                    maxServPrmArraysLength[i] = serviceParamKeys2.get(i).length;
                }
            } else {
                maxServPrmArraysLength[i] = serviceParamKeysMax.get(i).length;
            }
        }

        techInformation.setMaxServPrmArraysLength(maxServPrmArraysLength);
        techInformation.setServicePrmKeys1(serviceParamKeys1);
        techInformation.setServicePrmKeys2(serviceParamKeys2);
    }

    // метод добавляет в список массивы ключей параметров в том порядке, что и в массиве serviceKeys хранятся общие ключи
    private void sortAndAddPrmToList(JsonFile jsonFile, ArrayList<String[]> serviceParamKeys, String[] serviceKeys) {
        for (int i = 0; i < jsonFile.getParameters().getServices().size(); i++) {
            int j = 0;
            String[] prmKeys = new String[jsonFile.getParameters().getServices().get(serviceKeys[i]).size()];
            for (Map.Entry<String, String> pair : jsonFile.getParameters().getServices().get(serviceKeys[i]).entrySet()) {
                prmKeys[j] = pair.getKey();
                j++;
            }
            serviceParamKeys.add(prmKeys);
        }
    }

    private void sortArtifacts(JsonFile jsonFile1, JsonFile jsonFile2) {
        for (int i = 1; i < jsonFile1.getArtifacts().length; i++) {
            for (int j = 1; j < jsonFile2.getArtifacts().length; j++) {
                if (jsonFile1.getArtifacts()[i].getFile()[0].equals(jsonFile2.getArtifacts()[j].getFile()[0])) {
                    Artifacts tmp2 = jsonFile2.getArtifacts()[i];
                    jsonFile2.getArtifacts()[i] = jsonFile2.getArtifacts()[j];
                    jsonFile2.getArtifacts()[j] = tmp2;
                }
            }
        }
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
                    Object tmp = arr2[i];
                    arr2[i] = arr2[j];
                    arr2[j] = tmp;
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
                    Services tmp = jsonFile2.getServices()[i];
                    jsonFile2.getServices()[i] = jsonFile2.getServices()[j];
                    jsonFile2.getServices()[j] = tmp;
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
                    Mvn tmp = jsonFile2.getArtifacts()[0].getMvn()[i];
                    jsonFile2.getArtifacts()[0].getMvn()[i] = jsonFile2.getArtifacts()[0].getMvn()[j];
                    jsonFile2.getArtifacts()[0].getMvn()[j] = tmp;
                }
            }
        }
    }

    private void sortScript(JsonFile jsonFile1, JsonFile jsonFile2) {
        for (int i = 0; i < jsonFile1.getScript().length; i++) {
            for (int j = 0; j < jsonFile2.getScript().length; j++) {
                if (jsonFile1.getScript()[i].getUrl().equals(jsonFile2.getScript()[j].getUrl())) {
                    Script tmp = jsonFile2.getScript()[i];
                    jsonFile2.getScript()[i] = jsonFile2.getScript()[j];
                    jsonFile2.getScript()[j] = tmp;
                }
            }
        }
    }

    private void sortRpm(JsonFile jsonFile1, JsonFile jsonFile2) {
        for (int i = 0; i < jsonFile1.getRpm().length; i++) {
            for (int j = 0; j < jsonFile2.getRpm().length; j++) {
                if (jsonFile1.getRpm()[i].getUrl().equals(jsonFile2.getRpm()[j].getUrl())) {
                    Rpm tmp = jsonFile2.getRpm()[i];
                    jsonFile2.getRpm()[i] = jsonFile2.getRpm()[j];
                    jsonFile2.getRpm()[j] = tmp;
                }
            }
        }
    }

    private void sortKeys(String[] keys1, String[] keys2) {
        for (int i = 0; i < keys1.length; i++) {
            for (int j = 0; j < keys2.length; j++) {
                if (keys1[i].equals(keys2[j])) {
                    String tmp = keys2[i];
                    keys2[i] = keys2[j];
                    keys2[j] = tmp;
                }
            }
        }
    }

    private void setMaxServiceNameLength(JsonFile jsonFile1, JsonFile jsonFile2, TechInformation techInformation) {
        if (jsonFile1.getParameters() != null && jsonFile1.getParameters().getServices() != null) {

            techInformation.setServiceKeys1(jsonFile1.getParameters().getServices().keySet().toArray(new String[0]));

            techInformation.setServiceNameLength(jsonFile1.getParameters().getServices().size());
        }
        if (jsonFile2.getParameters() != null &&
                jsonFile2.getParameters().getServices() != null) {

            techInformation.setServiceKeys2(jsonFile2.getParameters().getServices().keySet().toArray(new String[0]));

            if (jsonFile2.getParameters().getServices().size() > techInformation.getServiceNameLength()) {
                techInformation.setServiceNameLength(jsonFile2.getParameters().getServices().size());
            }
        }

    }

    private void setMinServiceNameLength(JsonFile jsonFile1, JsonFile jsonFile2, TechInformation techInformation) {
        if (jsonFile1.getParameters() != null && jsonFile1.getParameters().getServices() != null) {

            techInformation.setMinServiceNameLength(jsonFile1.getParameters().getServices().size());
        }
        if (jsonFile2.getParameters() != null &&
                jsonFile2.getParameters().getServices() != null) {
            if (jsonFile2.getParameters().getServices().size() < techInformation.getMinServiceNameLength()) {
                techInformation.setMinServiceNameLength(jsonFile2.getParameters().getServices().size());
            }
        }
    }

    private void setMaxCommonLength(JsonFile jsonFile1, JsonFile jsonFile2, TechInformation techInformation) {
        if (jsonFile1.getParameters() != null && jsonFile1.getParameters().getCommon() != null) {

            techInformation.setCommonKeys1(jsonFile1.getParameters().getCommon().keySet().toArray(new String[0]));

            techInformation.setCommonLength(jsonFile1.getParameters().getCommon().size());
        }
        if (jsonFile2.getParameters() != null &&
                jsonFile2.getParameters().getCommon() != null) {
            techInformation.setCommonKeys2(jsonFile2.getParameters().getCommon().keySet().toArray(new String[0]));

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

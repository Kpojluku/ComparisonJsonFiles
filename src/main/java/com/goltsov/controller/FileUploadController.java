package com.goltsov.controller;


import com.goltsov.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Controller
public class FileUploadController {

    @GetMapping("/")
    public String listUploadedFiles() {
        return "upload";
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile[] file,
                                   @ModelAttribute("jsonfile1") JsonFile jsonfile1,
                                   @ModelAttribute("jsonfile2") JsonFile jsonfile2,
                                   @ModelAttribute("Tech") TechInformation techInformation,
                                   @ModelAttribute("keyFields1") KeyFields keyFields1,
                                   @ModelAttribute("keyFields2") KeyFields keyFields2,
                                   @ModelAttribute("errors") Errors errors) {
        JsonComparator jsonComparator = new JsonComparator();
        try {
            JsonFile File1 = jsonComparator.getJsonFile(file[0]);
            JsonFile File2 = jsonComparator.getJsonFile(file[1]);
            jsonComparator.setJsonFile(File1, jsonfile1);
            jsonComparator.setJsonFile(File2, jsonfile2);

            techInformation.setFileName1(Objects.requireNonNull(file[0].getOriginalFilename()).substring(0, file[0].getOriginalFilename().lastIndexOf(".")));
            techInformation.setFileName2(Objects.requireNonNull(file[1].getOriginalFilename()).substring(0, file[1].getOriginalFilename().lastIndexOf(".")));


            return jsonComparator.compare(jsonfile1, jsonfile2, techInformation, keyFields1, keyFields2);

        } catch (com.fasterxml.jackson.databind.exc.InvalidFormatException e) {
            e.printStackTrace();
            errors.setInvalidFormatException(true);
            return "error";
        } catch (com.fasterxml.jackson.core.JsonParseException | com.fasterxml.jackson.databind.JsonMappingException e) {
            e.printStackTrace();
            errors.setJsonParseException(true);
            return "error";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}

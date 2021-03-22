package com.goltsov.controller;


import com.goltsov.model.JsonComparator;
import com.goltsov.model.JsonFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

    @GetMapping("/upload")
    public String listUploadedFiles(Model model) {
        model.addAttribute("upload");
        return "upload";
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile[] file) {
        JsonComparator jsonComparator = new JsonComparator();
        try {
            JsonFile jsonFile1 = jsonComparator.getJsonFile(file[0]);
            JsonFile jsonFile2 = jsonComparator.getJsonFile(file[1]);
            System.out.println(jsonFile1);

        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }

        return "upload";
    }
}

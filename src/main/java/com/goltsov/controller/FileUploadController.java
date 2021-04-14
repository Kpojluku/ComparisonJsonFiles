package com.goltsov.controller;


import com.goltsov.model.JsonComparator;
import com.goltsov.model.JsonFile;
import com.goltsov.model.TechInformation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

    @GetMapping("/")
    public String listUploadedFiles(Model model) {
        model.addAttribute("upload");
        return "upload";
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile[] file,
                                   @ModelAttribute("jsonfile1") JsonFile jsonfile1,
                                   @ModelAttribute("jsonfile2") JsonFile jsonfile2,
                                   @ModelAttribute("Tech") TechInformation techInformation) {
        JsonComparator jsonComparator = new JsonComparator();
        try {
            JsonFile File1 = jsonComparator.getJsonFile(file[0]);
            JsonFile File2 = jsonComparator.getJsonFile(file[1]);
            jsonComparator.setJsonFile(File1, jsonfile1);
            jsonComparator.setJsonFile(File2, jsonfile2);

            return jsonComparator.compare(jsonfile1, jsonfile2, techInformation);

        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}

package com.goltsov.controller;


import com.goltsov.model.JsonComparator;
import com.goltsov.model.JsonFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String handleFileUpload(@RequestParam("file") MultipartFile[] file,
                                   @ModelAttribute("jsonfile1") JsonFile jsonfile1,
                                   @ModelAttribute("jsonfile2") JsonFile jsonfile2) {
        JsonComparator jsonComparator = new JsonComparator();
        try {
            JsonFile File1 = jsonComparator.getJsonFile(file[0]);
            JsonFile File2 = jsonComparator.getJsonFile(file[1]);
          //  jsonfile1.setMetadata(File1.getMetadata());
         //   jsonfile2.setMetadata(File2.getMetadata());
            jsonComparator.setJsonFile(File1, jsonfile1);
            jsonComparator.setJsonFile(File2, jsonfile2);

           return jsonComparator.compare(jsonfile1, jsonfile2);

        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }
}

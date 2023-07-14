package com.assesment.fileuploadserviceapp.controllers;

import com.assesment.fileuploadserviceapp.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FolderController {

    @Autowired
    private FileService fileService;

    @PostMapping("folders/{folderId}/files")
    public ResponseEntity<String> uploadFileToFolder(@PathVariable Long folderId, @RequestParam("file") MultipartFile file, @RequestHeader("Authorization") String userMail)
            throws IOException {
        String fileName = file.getOriginalFilename();
        fileService.createFileToFolder(folderId, fileName, file.getBytes(), userMail);
        return ResponseEntity.ok("File uploaded successfully: " + fileName);
    }
}

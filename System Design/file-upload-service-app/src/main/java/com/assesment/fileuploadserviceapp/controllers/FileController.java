package com.assesment.fileuploadserviceapp.controllers;

import com.assesment.fileuploadserviceapp.entites.FileData;
import com.assesment.fileuploadserviceapp.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping("/files/{fileId}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long fileId, @RequestHeader("Authorization") String userMail) throws FileNotFoundException {
        FileData fileData = fileService.getFileData(fileId, userMail);
        byte[] fileBytes = fileData.getBinaryData();

        return ResponseEntity.ok()
                .header("Content-Disposition", "inline; filename=" + URLEncoder.encode(fileData.getItem().getName(), StandardCharsets.UTF_8))
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(fileBytes);
    }


}

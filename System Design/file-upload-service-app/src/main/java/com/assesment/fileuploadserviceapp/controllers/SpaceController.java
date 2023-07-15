package com.assesment.fileuploadserviceapp.controllers;

import com.assesment.fileuploadserviceapp.entites.File;
import com.assesment.fileuploadserviceapp.entites.Folder;
import com.assesment.fileuploadserviceapp.entites.Space;
import com.assesment.fileuploadserviceapp.service.FileService;
import com.assesment.fileuploadserviceapp.service.FolderService;
import com.assesment.fileuploadserviceapp.service.SpaceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@AllArgsConstructor
public class SpaceController {

    @Autowired
    private SpaceService spaceService;

    @Autowired
    private FolderService folderService;

    @Autowired
    private FileService fileService;

    @PostMapping("/spaces")
    public ResponseEntity<String> createSpace(@RequestBody String spaceName, @RequestHeader(HttpHeaders.AUTHORIZATION) String userMail) {
        Space space = spaceService.createSpace(spaceName, userMail);
        return ResponseEntity.ok("Space with id %s created".formatted(space.getId()));
    }

    @PostMapping("/spaces/{spaceId}/folders")
    public ResponseEntity<String> createFolder(@PathVariable Long spaceId, @RequestBody String folderName, @RequestHeader(HttpHeaders.AUTHORIZATION) String userMail) {
        Folder folder = folderService.createFolder(spaceId, folderName, userMail);
        return ResponseEntity.ok("Folder with id %s created".formatted(folder.getId()));
    }

    @PostMapping("/spaces/{spaceId}/files")
    public ResponseEntity<String> uploadFileToSpace(@PathVariable Long spaceId, @RequestParam("file") MultipartFile file, @RequestHeader(HttpHeaders.AUTHORIZATION) String userMail)
            throws IOException {
        String fileName = file.getOriginalFilename();
        File savedFile = fileService.createFileToSpace(spaceId, fileName, file.getBytes(), userMail);
        return ResponseEntity.ok("File with id: %s name: %s uploaded".formatted(savedFile.getId(), savedFile.getName()));
    }
}

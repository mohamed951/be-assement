package com.assesment.fileuploadserviceapp.service;

import com.assesment.fileuploadserviceapp.entites.*;
import com.assesment.fileuploadserviceapp.exceptions.ForbiddenException;
import com.assesment.fileuploadserviceapp.lenum.PermissionLevel;
import com.assesment.fileuploadserviceapp.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;

@Service
public class FileService {

    @Autowired
    private SpaceRepository spaceRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private FolderRepository folderRepository;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private FileDataRepository fileDataRepository;

    public File createFileToFolder(Long folderId, String fileName, byte[] bytes, String userMail) {
        Folder folder = folderRepository.findById(folderId).orElseThrow();
        PermissionGroup permissionGroup = folder.getPermissionGroup();
        boolean isAuthorized = permissionRepository.existsByPermissionGroup_IdAndUserEmailAndPermissionLevel(permissionGroup.getId(), userMail, PermissionLevel.EDIT);
        if (!isAuthorized) {
            throw new ForbiddenException("Not authorized to access this resource");
        }
        File file = new File();
        file.setName(fileName);
        file.setParent(folder);
        file.setPermissionGroup(permissionGroup);
        fileRepository.save(file);
        FileData fileData = new FileData();
        fileData.setBinaryData(bytes);
        fileData.setItem(file);
        fileDataRepository.save(fileData);
        return file;
    }

    public File createFileToSpace(Long spaceId, String fileName, byte[] bytes, String userMail) {
        Space space = spaceRepository.findById(spaceId).orElseThrow();
        PermissionGroup permissionGroup = space.getPermissionGroup();
        boolean isAuthorized = permissionRepository.existsByPermissionGroup_IdAndUserEmailAndPermissionLevel(permissionGroup.getId(), userMail, PermissionLevel.EDIT);
        if (!isAuthorized) {
            throw new ForbiddenException("Not authorized to access this resource");
        }
        File file = new File();
        file.setName(fileName);
        file.setParent(space);
        file.setPermissionGroup(permissionGroup);
        fileRepository.save(file);
        FileData fileData = new FileData();
        fileData.setBinaryData(bytes);
        fileData.setItem(file);
        fileDataRepository.save(fileData);
        return file;
    }

    public FileData getFileData(Long fileId, String userMail) throws FileNotFoundException {
        File file = fileRepository.findById(fileId).orElseThrow();
        PermissionGroup permissionGroup = file.getPermissionGroup();

        boolean isAuthorized = permissionRepository.existsByPermissionGroup_IdAndUserEmailAndPermissionLevelIn(permissionGroup.getId(), userMail,
                List.of(PermissionLevel.EDIT, PermissionLevel.VIEW));

        if (!isAuthorized) {
            throw new ForbiddenException("Not authorized to access this resource");
        }
        return fileDataRepository.findByItem(file)
                .orElseThrow(() -> new FileNotFoundException("File not found with ID: " + fileId));
    }

    public File getFile(Long fileId, String userMail) throws FileNotFoundException {
        File file = fileRepository.findById(fileId).orElseThrow();
        PermissionGroup permissionGroup = file.getPermissionGroup();

        boolean isAuthorized = permissionRepository.existsByPermissionGroup_IdAndUserEmailAndPermissionLevelIn(permissionGroup.getId(), userMail,
                List.of(PermissionLevel.EDIT, PermissionLevel.VIEW));

        if (!isAuthorized) {
            throw new ForbiddenException("Not authorized to access this resource");
        }
        return file;
    }
}

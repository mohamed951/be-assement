package com.assesment.fileuploadserviceapp.service.impl;

import com.assesment.fileuploadserviceapp.entites.*;
import com.assesment.fileuploadserviceapp.lenum.PermissionLevel;
import com.assesment.fileuploadserviceapp.repositories.FileDataRepository;
import com.assesment.fileuploadserviceapp.repositories.FileRepository;
import com.assesment.fileuploadserviceapp.repositories.FolderRepository;
import com.assesment.fileuploadserviceapp.repositories.SpaceRepository;
import com.assesment.fileuploadserviceapp.service.FileService;
import com.assesment.fileuploadserviceapp.util.FileBuilder;
import com.assesment.fileuploadserviceapp.util.FileDataBuilder;
import com.assesment.fileuploadserviceapp.validation.PermissionValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.assesment.fileuploadserviceapp.exceptions.ExceptionSupplier.itemNotFoundException;

@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService {

    private SpaceRepository spaceRepository;

    private FolderRepository folderRepository;

    private FileRepository fileRepository;

    private FileDataRepository fileDataRepository;

    private PermissionValidator permissionValidator;

    @Override
    public File createFileToFolder(Long folderId, String fileName, byte[] bytes, String userMail) {
        File file = saveFile(folderId, fileName, userMail);
        saveFileMetaData(bytes, file);
        return file;
    }

    private File saveFile(Long folderId, String fileName, String userMail) {
        Folder folder = folderRepository.findById(folderId)
                .orElseThrow(itemNotFoundException(folderId));
        PermissionGroup permissionGroup = folder.getPermissionGroup();
        permissionValidator.validateUserIsAuthorizedToAccessItem(userMail, permissionGroup, List.of(PermissionLevel.EDIT));
        File file = FileBuilder.constructFile(fileName, folder, permissionGroup);
        fileRepository.save(file);
        return file;
    }

    private void saveFileMetaData(byte[] bytes, File file) {
        FileData fileData = FileDataBuilder.constructFileData(bytes, file);
        fileDataRepository.save(fileData);
    }

    @Override
    public File createFileToSpace(Long spaceId, String fileName, byte[] bytes, String userMail) {
        Space space = spaceRepository.findById(spaceId)
                .orElseThrow(itemNotFoundException(spaceId));
        PermissionGroup permissionGroup = space.getPermissionGroup();
        permissionValidator.validateUserIsAuthorizedToAccessItem(userMail, permissionGroup, List.of(PermissionLevel.EDIT, PermissionLevel.VIEW));
        File file = FileBuilder.constructFile(fileName, space, permissionGroup);
        fileRepository.save(file);
        saveFileMetaData(bytes, file);
        return file;
    }

    @Override
    public FileData getFileData(Long fileId, String userMail) {
        File file = getFile(fileId, userMail);
        return fileDataRepository.findByItem(file)
                .orElseThrow(itemNotFoundException(fileId));
    }

    @Override
    public File getFile(Long fileId, String userMail) {
        File file = fileRepository.findById(fileId)
                .orElseThrow(itemNotFoundException(fileId));
        PermissionGroup permissionGroup = file.getPermissionGroup();
        permissionValidator.validateUserIsAuthorizedToAccessItem(userMail, permissionGroup, List.of(PermissionLevel.EDIT, PermissionLevel.VIEW));
        return file;
    }
}

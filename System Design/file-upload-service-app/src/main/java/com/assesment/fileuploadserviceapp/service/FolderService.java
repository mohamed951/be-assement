package com.assesment.fileuploadserviceapp.service;

import com.assesment.fileuploadserviceapp.entites.Folder;
import com.assesment.fileuploadserviceapp.entites.PermissionGroup;
import com.assesment.fileuploadserviceapp.entites.Space;
import com.assesment.fileuploadserviceapp.exceptions.ForbiddenException;
import com.assesment.fileuploadserviceapp.lenum.PermissionLevel;
import com.assesment.fileuploadserviceapp.repositories.FolderRepository;
import com.assesment.fileuploadserviceapp.repositories.PermissionRepository;
import com.assesment.fileuploadserviceapp.repositories.SpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FolderService {

    @Autowired
    private SpaceRepository spaceRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private FolderRepository folderRepository;

    public Folder createFolder(Long spaceId, String folderName, String userMail) {
        Space space = spaceRepository.findById(spaceId).orElseThrow();
        PermissionGroup permissionGroup = space.getPermissionGroup();
        boolean isAuthorized = permissionRepository.existsByPermissionGroup_IdAndUserEmailAndPermissionLevel(permissionGroup.getId(), userMail, PermissionLevel.EDIT);
        if (!isAuthorized) {
            throw new ForbiddenException("Not authorized to access this resource");
        }
        Folder folder = new Folder();
        folder.setName(folderName);
        folder.setParent(space);
        folder.setPermissionGroup(permissionGroup);
        return folderRepository.save(folder);
    }
}

package com.assesment.fileuploadserviceapp.service;

import com.assesment.fileuploadserviceapp.entites.Folder;
import com.assesment.fileuploadserviceapp.entites.PermissionGroup;
import com.assesment.fileuploadserviceapp.entites.Space;
import com.assesment.fileuploadserviceapp.lenum.PermissionLevel;
import com.assesment.fileuploadserviceapp.repositories.FolderRepository;
import com.assesment.fileuploadserviceapp.repositories.PermissionRepository;
import com.assesment.fileuploadserviceapp.repositories.SpaceRepository;
import com.assesment.fileuploadserviceapp.validation.PermissionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.assesment.fileuploadserviceapp.exceptions.ExceptionSupplier.itemNotFoundException;
import static com.assesment.fileuploadserviceapp.util.FolderBuilder.constructFolder;

@Service
public class FolderService {

    @Autowired
    private SpaceRepository spaceRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private FolderRepository folderRepository;

    @Autowired
    private PermissionValidator permissionValidator;

    public Folder createFolder(Long spaceId, String folderName, String userMail) {
        Space space = spaceRepository.findById(spaceId)
                .orElseThrow(itemNotFoundException(spaceId));

        PermissionGroup permissionGroup = space.getPermissionGroup();
        permissionValidator.validateUserIsAuthorizedToAccessItem(userMail, permissionGroup, List.of(PermissionLevel.EDIT));
        Folder folder = constructFolder(folderName, space, permissionGroup);
        return folderRepository.save(folder);
    }
}

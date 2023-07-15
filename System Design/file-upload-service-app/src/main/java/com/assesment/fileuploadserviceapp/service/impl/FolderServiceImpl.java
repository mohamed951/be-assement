package com.assesment.fileuploadserviceapp.service.impl;

import com.assesment.fileuploadserviceapp.entites.Folder;
import com.assesment.fileuploadserviceapp.entites.PermissionGroup;
import com.assesment.fileuploadserviceapp.entites.Space;
import com.assesment.fileuploadserviceapp.lenum.PermissionLevel;
import com.assesment.fileuploadserviceapp.repositories.FolderRepository;
import com.assesment.fileuploadserviceapp.repositories.PermissionRepository;
import com.assesment.fileuploadserviceapp.repositories.SpaceRepository;
import com.assesment.fileuploadserviceapp.service.FolderService;
import com.assesment.fileuploadserviceapp.validation.PermissionValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.assesment.fileuploadserviceapp.exceptions.ExceptionSupplier.itemNotFoundException;
import static com.assesment.fileuploadserviceapp.util.FolderBuilder.constructFolder;

@Service
@AllArgsConstructor
public class FolderServiceImpl implements FolderService {

    private SpaceRepository spaceRepository;

    private PermissionRepository permissionRepository;

    private FolderRepository folderRepository;

    private PermissionValidator permissionValidator;

    @Override
    public Folder createFolder(Long spaceId, String folderName, String userMail) {
        Space space = spaceRepository.findById(spaceId)
                .orElseThrow(itemNotFoundException(spaceId));

        PermissionGroup permissionGroup = space.getPermissionGroup();
        permissionValidator.validateUserIsAuthorizedToAccessItem(userMail, permissionGroup, List.of(PermissionLevel.EDIT));
        Folder folder = constructFolder(folderName, space, permissionGroup);
        return folderRepository.save(folder);
    }
}

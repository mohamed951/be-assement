package com.assesment.fileuploadserviceapp.service.impl;

import com.assesment.fileuploadserviceapp.entites.PermissionGroup;
import com.assesment.fileuploadserviceapp.entites.Space;
import com.assesment.fileuploadserviceapp.lenum.PermissionLevel;
import com.assesment.fileuploadserviceapp.repositories.PermissionGroupRepository;
import com.assesment.fileuploadserviceapp.repositories.SpaceRepository;
import com.assesment.fileuploadserviceapp.service.SpaceService;
import com.assesment.fileuploadserviceapp.util.SpaceBuilder;
import com.assesment.fileuploadserviceapp.validation.ItemValidator;
import com.assesment.fileuploadserviceapp.validation.PermissionValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.assesment.fileuploadserviceapp.exceptions.ExceptionSupplier.defaultGroupNotFoundException;

@Service
@AllArgsConstructor
public class SpaceServiceImpl implements SpaceService {

    private SpaceRepository spaceRepository;

    private PermissionGroupRepository permissionGroupRepository;

    private PermissionValidator permissionValidator;

    private ItemValidator itemValidator;


    @Override
    public Space createSpace(String spaceName, String userMail) {
        itemValidator.validateSpace(spaceName);
        PermissionGroup permissionGroup = getDefaultPermissionGroup();
        Space space = SpaceBuilder.constructSpace(spaceName, permissionGroup);
        permissionValidator.validateUserIsAuthorizedToAccessItem(userMail, permissionGroup, List.of(PermissionLevel.EDIT));
        return spaceRepository.save(space);
    }

    private PermissionGroup getDefaultPermissionGroup() {
        final String defaultGroupName = "ADMIN";
        return permissionGroupRepository.findByName(defaultGroupName).orElseThrow(
                defaultGroupNotFoundException(defaultGroupName)
        );
    }
}

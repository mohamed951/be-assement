package com.assesment.fileuploadserviceapp.validation;

import com.assesment.fileuploadserviceapp.entites.PermissionGroup;
import com.assesment.fileuploadserviceapp.exceptions.ForbiddenException;
import com.assesment.fileuploadserviceapp.lenum.PermissionLevel;
import com.assesment.fileuploadserviceapp.repositories.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PermissionValidator {

    @Autowired
    private PermissionRepository permissionRepository;

    public void validateUserIsAuthorizedToAccessItem(String userMail, PermissionGroup permissionGroup, List<PermissionLevel> permissionLevels) {
        boolean isAuthorized = permissionRepository.existsByPermissionGroup_IdAndUserEmailAndPermissionLevelIn(permissionGroup.getId(), userMail,
                permissionLevels);

        if (!isAuthorized) {
            throw new ForbiddenException("Not authorized to access this resource");
        }
    }
}

package com.assesment.fileuploadserviceapp.service;

import com.assesment.fileuploadserviceapp.entites.PermissionGroup;
import com.assesment.fileuploadserviceapp.entites.Space;
import com.assesment.fileuploadserviceapp.repositories.PermissionGroupRepository;
import com.assesment.fileuploadserviceapp.repositories.PermissionRepository;
import com.assesment.fileuploadserviceapp.repositories.SpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpaceService {

    @Autowired
    private SpaceRepository spaceRepository;

    @Autowired
    private PermissionGroupRepository permissionGroupRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    public Space createSpace(String spaceName, String userMail) {
        Space space = new Space();
        space.setName(spaceName);
        PermissionGroup permissionGroup = permissionGroupRepository.findByName("ADMIN").orElseThrow();
        space.setPermissionGroup(permissionGroup);
        return spaceRepository.save(space);
    }
}

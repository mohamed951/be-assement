package com.assesment.fileuploadserviceapp.util;

import com.assesment.fileuploadserviceapp.entites.PermissionGroup;
import com.assesment.fileuploadserviceapp.entites.Space;

public class SpaceBuilder {

    private SpaceBuilder() {
    }

    public static Space constructSpace(String spaceName, PermissionGroup permissionGroup) {
        Space space = new Space();
        space.setName(spaceName);
        space.setPermissionGroup(permissionGroup);
        return space;
    }
}

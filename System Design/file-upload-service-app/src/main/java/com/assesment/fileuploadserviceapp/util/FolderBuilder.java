package com.assesment.fileuploadserviceapp.util;

import com.assesment.fileuploadserviceapp.entites.Folder;
import com.assesment.fileuploadserviceapp.entites.PermissionGroup;
import com.assesment.fileuploadserviceapp.entites.Space;

public class FolderBuilder {

    private FolderBuilder() {
    }

    public static Folder constructFolder(String folderName, Space space, PermissionGroup permissionGroup) {
        Folder folder = new Folder();
        folder.setName(folderName);
        folder.setParent(space);
        folder.setPermissionGroup(permissionGroup);
        return folder;
    }
}

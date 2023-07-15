package com.assesment.fileuploadserviceapp.util;

import com.assesment.fileuploadserviceapp.entites.File;
import com.assesment.fileuploadserviceapp.entites.Folder;
import com.assesment.fileuploadserviceapp.entites.PermissionGroup;
import com.assesment.fileuploadserviceapp.entites.Space;

public class FileBuilder {

    public static File constructFile(String fileName, Space space, PermissionGroup permissionGroup) {
        File file = new File();
        file.setName(fileName);
        file.setParent(space);
        file.setPermissionGroup(permissionGroup);
        return file;
    }

    public static File constructFile(String fileName, Folder folder, PermissionGroup permissionGroup) {
        File file = new File();
        file.setName(fileName);
        file.setParent(folder);
        file.setPermissionGroup(permissionGroup);
        return file;
    }
}

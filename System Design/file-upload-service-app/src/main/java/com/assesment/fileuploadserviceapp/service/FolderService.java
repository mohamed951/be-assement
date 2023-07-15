package com.assesment.fileuploadserviceapp.service;

import com.assesment.fileuploadserviceapp.entites.Folder;

public interface FolderService {

    Folder createFolder(Long spaceId, String folderName, String userMail);
}

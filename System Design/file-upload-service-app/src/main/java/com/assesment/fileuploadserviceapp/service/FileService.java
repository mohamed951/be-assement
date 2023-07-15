package com.assesment.fileuploadserviceapp.service;

import com.assesment.fileuploadserviceapp.entites.File;
import com.assesment.fileuploadserviceapp.entites.FileData;

public interface FileService {

    File createFileToFolder(Long folderId, String fileName, byte[] bytes, String userMail);

    File createFileToSpace(Long spaceId, String fileName, byte[] bytes, String userMail);

    FileData getFileData(Long fileId, String userMail);

    File getFile(Long fileId, String userMail);
}

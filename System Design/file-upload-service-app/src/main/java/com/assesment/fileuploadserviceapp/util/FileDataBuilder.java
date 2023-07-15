package com.assesment.fileuploadserviceapp.util;

import com.assesment.fileuploadserviceapp.entites.File;
import com.assesment.fileuploadserviceapp.entites.FileData;

public class FileDataBuilder {

    private FileDataBuilder() {
    }

    public static FileData constructFileData(byte[] bytes, File file) {
        FileData fileData = new FileData();
        fileData.setBinaryData(bytes);
        fileData.setItem(file);
        return fileData;
    }
}

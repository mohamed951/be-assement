package com.assesment.fileuploadserviceapp.validation;

import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.assesment.fileuploadserviceapp.exceptions.ExceptionSupplier.folderNameNotValid;
import static com.assesment.fileuploadserviceapp.exceptions.ExceptionSupplier.spaceNameNotValid;
import static com.assesment.fileuploadserviceapp.exceptions.ExceptionThrowerUtil.doThrow;

@Component
public class ItemValidator {

    public void validateSpace(String spaceName) {
        if (Objects.isNull(spaceName) || spaceName.isEmpty()) {
            doThrow(spaceNameNotValid());
        }
    }

    public void validateFolder(String folderName) {
        if (Objects.isNull(folderName) || folderName.isEmpty()) {
            doThrow(folderNameNotValid());
        }
    }
}

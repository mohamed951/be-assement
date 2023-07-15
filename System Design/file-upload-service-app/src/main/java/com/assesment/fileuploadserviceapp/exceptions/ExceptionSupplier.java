package com.assesment.fileuploadserviceapp.exceptions;

import java.util.function.Supplier;

public class ExceptionSupplier {

    private ExceptionSupplier() {
    }

    public static Supplier<RuntimeException> itemNotFoundException(Long id) {
        return () -> new NotFoundException("Item not found with ID=" + id);
    }

    public static Supplier<RuntimeException> defaultGroupNotFoundException(String name) {
        return () -> new NotFoundException("Default group with name= %s not Found".formatted(name));
    }

    public static Supplier<RuntimeException> spaceNameNotValid() {
        return () -> new BadRequestException("Space name not valid");
    }
    public static Supplier<RuntimeException> folderNameNotValid() {
        return () -> new BadRequestException("Folder name not valid");
    }

}

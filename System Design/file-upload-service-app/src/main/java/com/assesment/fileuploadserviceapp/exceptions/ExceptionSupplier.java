package com.assesment.fileuploadserviceapp.exceptions;

import java.util.function.Supplier;

public class ExceptionSupplier {

    private ExceptionSupplier() {
    }

    public static Supplier<NotFoundException> itemNotFoundException(Long id) {
        return () -> new NotFoundException("Item not found with ID=" + id);
    }

    public static Supplier<NotFoundException> defaultGroupNotFoundException(String name) {
        return () -> new NotFoundException("Default group with name= %s not Found".formatted(name));
    }
}

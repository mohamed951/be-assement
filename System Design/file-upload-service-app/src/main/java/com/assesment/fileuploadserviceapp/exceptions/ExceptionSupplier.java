package com.assesment.fileuploadserviceapp.exceptions;

import java.util.function.Supplier;

public class ExceptionSupplier {

    public static Supplier<NotFoundException> itemNotFoundException(Long id) {
        return () -> new NotFoundException("Item not found with ID: " + id);
    }
}

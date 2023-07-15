package com.assesment.fileuploadserviceapp.exceptions;

import java.util.function.Supplier;

public class ExceptionThrowerUtil {

    private ExceptionThrowerUtil() {
    }

    public static <T> T doThrow(Supplier<? extends RuntimeException> exceptionSupplier) {
        throw exceptionSupplier.get();
    }
}

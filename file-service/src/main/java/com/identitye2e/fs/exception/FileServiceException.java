package com.identitye2e.fs.exception;

public class FileServiceException extends RuntimeException {

    public FileServiceException(String message) {
        super(message);
    }

    public FileServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}

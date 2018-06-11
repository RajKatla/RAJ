package com.identitye2e.fs.exception;


import org.apache.log4j.Logger;

public class FileServiceExceptionHandler {
    private static final Logger LOGGER = Logger.getLogger(FileServiceExceptionHandler.class);

    public static String handleError(final String errorMessage) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(errorMessage);
        }

        LOGGER.error(errorMessage);
        throw new FileServiceException(errorMessage);
    }

    public static String handleError(final String errorMessage, final Exception ex) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(errorMessage, ex);
        }

        LOGGER.error(errorMessage, ex);
        throw new FileServiceException(errorMessage, ex);
    }
}

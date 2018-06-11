package com.identitye2e.fs.service.impl;

import com.identitye2e.fs.pojo.FileMetadata;
import com.identitye2e.fs.service.DirectoryScannerService;
import com.identitye2e.fs.util.MIMEType;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.identitye2e.fs.exception.FileServiceExceptionHandler.handleError;
import static com.identitye2e.fs.util.AppUtil.checkNotNull;
import static java.nio.file.Files.list;
import static org.apache.commons.io.FilenameUtils.getExtension;

public class DirectoryScannerServiceImpl implements DirectoryScannerService {
    private static final Logger LOGGER = Logger.getLogger(DirectoryScannerServiceImpl.class);

    public Optional<List<FileMetadata>> scanDirectory(final Optional<String> directoryPath) {
        if (checkNotNull(directoryPath)) {
            if (LOGGER.isDebugEnabled()) {
                final String msg = String.format("Scanning directory: %s", directoryPath);
                LOGGER.debug(msg);
            }
            return directoryPath.map(this::getFileMetadata);
        } else
            return Optional.empty();
    }

    private List<FileMetadata> getFileMetadata(final String directory) {
        if (checkNotNull(directory)) {
            try (final Stream<Path> paths = list(new File(directory).toPath())) {
                return paths.filter(Files::isRegularFile)
                        .map(Path::toFile)
                        .map(this::getData)
                        .collect(Collectors.toList());
            } catch (IOException ex) {
                final String errorMsg = "Exception occurred while scanning directory";
                handleError(errorMsg, ex);
            }
        }
        return Collections.emptyList();
    }

    private FileMetadata getData(final File file) {
        final String name = file.getName();
        final long size = file.length();
        final String extension = getExtension(name);
        final String mimeType = getMimeFileType(extension);
        return new FileMetadata.FileMetadataBuilder()
                .withName(name)
                .withExtension(extension)
                .withMIMEType(mimeType)
                .withSize(size)
                .build();
    }

    private String getMimeFileType(final String extension) {
        if (checkNotNull(extension)) {
            if (extension.equals(MIMEType.CSV.name().toLowerCase())) {
                return MIMEType.CSV.getMimeType();
            } else if (extension.equals(MIMEType.XLS.name().toLowerCase())) {
                return MIMEType.XLS.getMimeType();
            } else if (extension.equals(MIMEType.XLSX.name().toLowerCase())) {
                return MIMEType.XLSX.getMimeType();
            }
        }
        return MIMEType.UNKNOWN.getMimeType();
    }
}

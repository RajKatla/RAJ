package com.identitye2e.fs.service.impl;

import com.identitye2e.fs.pojo.FileMetadata;
import com.identitye2e.fs.service.FileSearchService;
import com.identitye2e.fs.util.AppPropertiesLoader;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.identitye2e.fs.util.AppUtil.checkNotNull;

public class FileSearchServiceImpl implements FileSearchService {

    private static final Logger LOGGER = Logger.getLogger(FileSearchServiceImpl.class);

    @Override
    public Optional<List<File>> searchFilesWithMIMEType(
            final Optional<String> fileMIMEType,
            final Optional<List<FileMetadata>> fileMetadatas) throws IOException {
        if (checkNotNull(fileMetadatas) && checkNotNull(fileMIMEType)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Searching files for MIMEType : " + fileMIMEType);
            }
            final String directoryPath = new AppPropertiesLoader().getDirectoryPath();
            final Optional<List<FileMetadata>> filteredMetadata = findFilesWithMIMEType(fileMIMEType, fileMetadatas);
            return filteredMetadata.map(metadatas -> metadatas.stream().map(metadata -> {
                final String filePath =
                        directoryPath +
                                File.separator +
                                metadata.getName();
                return new File(filePath);
            }).collect(Collectors.toList()));
        }
        return Optional.empty();
    }

    private Optional<List<FileMetadata>> findFilesWithMIMEType(
            final Optional<String> fileMIMEType,
            final Optional<List<FileMetadata>> fileMetadatas) {
        return fileMIMEType.map(setOfmimeTypes -> {
            final Predicate<FileMetadata> mimeTypeFilter =
                    fileMetadata -> setOfmimeTypes.equals(fileMetadata.getMimeType());
            return fileMetadatas.map(metadatas ->
                    metadatas.stream().filter(mimeTypeFilter).collect(Collectors.toList()));
        }).map(Optional::get);
    }
}

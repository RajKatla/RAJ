package com.identitye2e.fs.service;

import com.identitye2e.fs.pojo.FileMetadata;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface FileSearchService {

    public Optional<List<File>> searchFilesWithMIMEType(final Optional<String> fileMIMEType,
                                                        final Optional<List<FileMetadata>> fileMetadatas) throws IOException;
}

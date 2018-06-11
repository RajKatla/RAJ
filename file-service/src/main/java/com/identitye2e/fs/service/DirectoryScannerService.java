package com.identitye2e.fs.service;

import com.identitye2e.fs.pojo.FileMetadata;

import java.util.List;
import java.util.Optional;

public interface DirectoryScannerService {
    Optional<List<FileMetadata>> scanDirectory(final Optional<String> directoryPath);
}

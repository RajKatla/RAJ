package com.identitye2e.fs.integration;

import com.identitye2e.fs.pojo.FileMetadata;
import com.identitye2e.fs.service.DirectoryScannerService;
import com.identitye2e.fs.service.FileSearchService;
import com.identitye2e.fs.service.impl.DirectoryScannerServiceImpl;
import com.identitye2e.fs.service.impl.FileSearchServiceImpl;
import com.identitye2e.fs.util.MIMEType;
import org.apache.commons.io.FilenameUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.identitye2e.fs.data.ResourcePathUtil.getPath;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class FileSearchServiceITTest {

    private final DirectoryScannerService directoryScannerService = new DirectoryScannerServiceImpl();
    private final FileSearchService fileSearchService = new FileSearchServiceImpl();

    @Test
    public void shouldReturnMatchedFilesWhenSearchWithCSVMIMEType() throws IOException {
        final String path = getPath();
        final Optional<String> directory = Optional.of(path);

        final Optional<List<FileMetadata>> fileMetadatas =
                directoryScannerService.scanDirectory(directory);

        assertTrue(fileMetadatas.isPresent());
        assertThat(fileMetadatas.get().size(), is(10));

        final Optional<List<File>> mimeTypeResult =
                fileSearchService.searchFilesWithMIMEType(Optional.of(MIMEType.CSV.getMimeType()), fileMetadatas);

        assertTrue(mimeTypeResult.isPresent());
        final List<File> resultFileMetadata = mimeTypeResult.get();
        assertThat(resultFileMetadata.size(), is(5));

        final File file = resultFileMetadata.get(0);
        final String fileExt = FilenameUtils.getExtension(file.getName());

        assertThat(fileExt, is("csv"));
    }

    @Test
    public void shouldReturnMatchedFilesWhenSearchWithExcelMIMEType() throws IOException {
        final String path = getPath();
        final Optional<String> directory = Optional.of(path);

        final Optional<List<FileMetadata>> fileMetadatas =
                directoryScannerService.scanDirectory(directory);

        assertTrue(fileMetadatas.isPresent());
        assertThat(fileMetadatas.get().size(), is(10));

        final Optional<List<File>> mimeTypeResult =
                fileSearchService.searchFilesWithMIMEType(Optional.of(MIMEType.XLSX.getMimeType()), fileMetadatas);

        assertTrue(mimeTypeResult.isPresent());
        final List<File> resultFileMetadata = mimeTypeResult.get();
        assertThat(resultFileMetadata.size(), is(5));

        final File file = resultFileMetadata.get(0);
        final String fileExt = FilenameUtils.getExtension(file.getName());

        assertThat(fileExt, is("xlsx"));
    }
}

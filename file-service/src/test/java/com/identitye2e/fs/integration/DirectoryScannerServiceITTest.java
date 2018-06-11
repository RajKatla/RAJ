package com.identitye2e.fs.integration;

import com.identitye2e.fs.pojo.FileMetadata;
import com.identitye2e.fs.service.DirectoryScannerService;
import com.identitye2e.fs.service.impl.DirectoryScannerServiceImpl;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.identitye2e.fs.data.ResourcePathUtil.getPath;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class DirectoryScannerServiceITTest {

    private final DirectoryScannerService directoryScannerService =
            new DirectoryScannerServiceImpl();

    @Test
    public void shouldScanGivenDirectoryAndReturnFileMetadata() throws IOException {
        final String directoryPath = getPath();
        final Optional<String> directory = Optional.of(directoryPath);
        final Optional<List<FileMetadata>> fileMetadata =
                directoryScannerService.scanDirectory(directory);

        assertTrue(fileMetadata.isPresent());

        final List<FileMetadata> filesMetadata = fileMetadata.get();

        assertThat(filesMetadata.size(), is(10));

        final String firstFileName = "make_clour_info_csv_1.csv";
        final String firstFileExt = "csv";
        final long firstFileSize = 146;
        final String firstFileMIMEType = "text/csv";

        assertThat(filesMetadata.get(0).getName(), is(firstFileName));
        assertThat(filesMetadata.get(0).getExtension(), is(firstFileExt));
        assertThat(filesMetadata.get(0).getSize(), is(firstFileSize));
        assertThat(filesMetadata.get(0).getMimeType(), is(firstFileMIMEType));
    }
}

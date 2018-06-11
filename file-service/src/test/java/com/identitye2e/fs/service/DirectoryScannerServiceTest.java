package com.identitye2e.fs.service;

import com.identitye2e.fs.pojo.FileMetadata;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.identitye2e.fs.data.TestData.getFileMetadata;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class DirectoryScannerServiceTest {

    @Mock
    private DirectoryScannerService directoryScannerService;

    @Before
    public void setUp() {
        directoryScannerService = mock(DirectoryScannerService.class);
    }

    @Test
    public void shouldScanGivenDirectoryAndReturnFileMetadata() {
        //given
        final FileMetadata fileMetadata = getFileMetadata();
        final Optional<List<FileMetadata>> collectionOfFilesInfo =
                Optional.of(Collections.singletonList(fileMetadata));
        when(directoryScannerService.scanDirectory(any(Optional.class)))
                .thenReturn(collectionOfFilesInfo);

        //when
        final Optional<List<FileMetadata>> filesInfo =
                directoryScannerService.scanDirectory(Optional.of("directory"));

        //then
        assertTrue(filesInfo.isPresent());

        final List<FileMetadata> filesInformation = filesInfo.get();

        assertThat(filesInformation.size(), is(1));
        final FileMetadata fileInfo = filesInformation.get(0);

        assertThat(fileInfo.getName(), is(fileMetadata.getName()));
        assertThat(fileInfo.getExtension(), is(fileMetadata.getExtension()));
        assertThat(fileInfo.getSize(), is(fileMetadata.getSize()));
        assertThat(fileInfo.getMimeType(), is(fileMetadata.getMimeType()));

        verify(directoryScannerService, times(1))
                .scanDirectory(Optional.of("directory"));
        verifyNoMoreInteractions(directoryScannerService);

    }

}

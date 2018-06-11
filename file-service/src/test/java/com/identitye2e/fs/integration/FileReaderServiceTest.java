package com.identitye2e.fs.integration;

import com.identitye2e.fs.pojo.FileMetadata;
import com.identitye2e.fs.pojo.VehicleRegistrationDetails;
import com.identitye2e.fs.service.DirectoryScannerService;
import com.identitye2e.fs.service.FileReaderService;
import com.identitye2e.fs.service.FileSearchService;
import com.identitye2e.fs.service.impl.DirectoryScannerServiceImpl;
import com.identitye2e.fs.service.impl.FileReaderServiceImpl;
import com.identitye2e.fs.service.impl.FileSearchServiceImpl;
import com.identitye2e.fs.util.MIMEType;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.identitye2e.fs.data.ResourcePathUtil.getPath;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FileReaderServiceTest {

    final FileReaderService fileReaderService = new FileReaderServiceImpl();
    private final DirectoryScannerService directoryScannerService =
            new DirectoryScannerServiceImpl();
    private final FileSearchService fileSearchService = new FileSearchServiceImpl();

    @Test
    public void testReadVehicleRegistrationDetailsFromCSV() throws IOException {
        final String path = getPath();
        final Optional<String> directory = Optional.of(path);

        final Optional<List<FileMetadata>> fileMetadatas = directoryScannerService.scanDirectory(directory);
        assertTrue(fileMetadatas.isPresent());

        final Optional<List<File>> csvSearchResult =
                fileSearchService.searchFilesWithMIMEType(Optional.of(MIMEType.CSV.getMimeType()), fileMetadatas);
        assertTrue(csvSearchResult.isPresent());

        final List<File> csvFiles = csvSearchResult.get();
        assertThat(csvFiles.size(), is(5));

        final Optional<List<VehicleRegistrationDetails>> vehicleRegistrationDetailsFromCSVFiles =
                fileReaderService.readVehicleRegistrationDetailsFromCSV(csvSearchResult);
        assertTrue(vehicleRegistrationDetailsFromCSVFiles.isPresent());

        final List<VehicleRegistrationDetails> vehicleRegistrationDetailsList =
                vehicleRegistrationDetailsFromCSVFiles.get();

        assertThat(vehicleRegistrationDetailsList.size(), is(25));

        vehicleRegistrationDetailsList.forEach(vehicleRegistrationDetails -> {
            final String registrationNumber = vehicleRegistrationDetails.getRegistrationNumber();
            final String make = vehicleRegistrationDetails.getMake();
            final String colour = vehicleRegistrationDetails.getColour();

            assertNotNull(registrationNumber);
            assertThat(registrationNumber.isEmpty(), is(false));

            assertNotNull(make);
            assertThat(make.isEmpty(), is(false));

            assertNotNull(colour);
            assertThat(colour.isEmpty(), is(false));
        });
    }

    @Test
    public void testReadVehicleRegistrationDetailsFromExcel() throws IOException {
        final String path = getPath();
        final Optional<String> directory = Optional.of(path);

        final Optional<List<FileMetadata>> fileMetadatas = directoryScannerService.scanDirectory(directory);
        assertTrue(fileMetadatas.isPresent());

        final Optional<List<File>> excelSearchResult =
                fileSearchService.searchFilesWithMIMEType(Optional.of(MIMEType.XLSX.getMimeType()), fileMetadatas);
        assertTrue(excelSearchResult.isPresent());

        final List<File> excelFiles = excelSearchResult.get();
        assertThat(excelFiles.size(), is(5));

        final Optional<List<VehicleRegistrationDetails>> vehicleRegistrationDetailsFromExcelFiles =
                fileReaderService.readVehicleRegistrationDetailsFromExcel(excelSearchResult);
        assertTrue(vehicleRegistrationDetailsFromExcelFiles.isPresent());

        final List<VehicleRegistrationDetails> vehicleRegistrationDetailsList =
                vehicleRegistrationDetailsFromExcelFiles.get();

        assertThat(vehicleRegistrationDetailsList.size(), is(25));

        vehicleRegistrationDetailsList.forEach(vehicleRegistrationDetails -> {
            final String registrationNumber = vehicleRegistrationDetails.getRegistrationNumber();
            final String make = vehicleRegistrationDetails.getMake();
            final String colour = vehicleRegistrationDetails.getColour();

            assertNotNull(registrationNumber);
            assertThat(registrationNumber.isEmpty(), is(false));

            assertNotNull(make);
            assertThat(make.isEmpty(), is(false));

            assertNotNull(colour);
            assertThat(colour.isEmpty(), is(false));
        });
    }
}

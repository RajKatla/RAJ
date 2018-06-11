package com.identitye2e.fs.service.impl;

import com.identitye2e.fs.pojo.VehicleRegistrationDetails;
import com.identitye2e.fs.service.FileReaderService;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static com.identitye2e.fs.exception.FileServiceExceptionHandler.handleError;
import static com.identitye2e.fs.util.AppUtil.checkNotNull;

public class FileReaderServiceImpl implements FileReaderService {
    private static final Logger LOGGER = Logger.getLogger(FileReaderServiceImpl.class);

    private static final String COMMA = ",";

    @Override
    public Optional<List<VehicleRegistrationDetails>> readVehicleRegistrationDetailsFromCSV(
            final Optional<List<File>> files) {
        if (checkNotNull(files)) {
            if (LOGGER.isDebugEnabled()) {
                final String msg = String.format("Reading vehicle registration details from: %s", files);
                LOGGER.debug(msg);
            }
            final List<VehicleRegistrationDetails> vehicleRegistrationDetails = new ArrayList<>();

            return files.map(csvFiles -> csvFiles.stream().map(csvFile -> {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(getFilePath(csvFile)))) {
                    String line = reader.readLine();
                    while ((line = reader.readLine()) != null) {
                        final String[] data = line.split(COMMA);
                        final String registrationNumber = data[0];
                        final String make = data[1];
                        final String colour = data[2];
                        vehicleRegistrationDetails.add(
                                new VehicleRegistrationDetails.VehicleDetailsBuilder()
                                        .withRegistrationNumber(registrationNumber)
                                        .withMake(make)
                                        .withColour(colour)
                                        .build());
                    }
                } catch (Exception ex) {
                    final String msg = String.format("Error occurred while reading csv file: %s", csvFile);
                    handleError(msg, ex);
                }
                return vehicleRegistrationDetails;
            })).flatMap(stream -> stream.reduce((data, data2) -> data));

        }
        return Optional.empty();
    }

    @Override
    public Optional<List<VehicleRegistrationDetails>> readVehicleRegistrationDetailsFromExcel(
            final Optional<List<File>> files) {
        if (checkNotNull(files)) {
            if (LOGGER.isDebugEnabled()) {
                final String msg = String.format("Reading vehicle registration details from: %s", files);
                LOGGER.debug(msg);
            }
            final List<VehicleRegistrationDetails> vehicleRegistrationDetails = new ArrayList<>();

            return files.map(excelFiles -> excelFiles.stream()
                    .map(excelFile -> {
                        try {
                            Workbook workbook = new XSSFWorkbook(getFilePath(excelFile));
                            Sheet datatypeSheet = workbook.getSheetAt(0);
                            Iterator<Row> iterator = datatypeSheet.iterator();
                            while (iterator.hasNext()) {
                                Row currentRow = iterator.next();
                                Iterator<Cell> cellIterator = currentRow.iterator();

                                if (currentRow.getRowNum() == 0) {
                                    continue;
                                }
                                while (cellIterator.hasNext()) {

                                    Cell regigstationNumberCell = cellIterator.next();
                                    Cell makeCell = cellIterator.next();
                                    Cell colourCell = cellIterator.next();

                                    String registrationNumber = null;
                                    String make = null;
                                    String colour = null;

                                    if (!regigstationNumberCell.getStringCellValue().equals("")) {
                                        registrationNumber = regigstationNumberCell.getStringCellValue();
                                    }
                                    if (!makeCell.getStringCellValue().equals("")) {
                                        make = makeCell.getStringCellValue();
                                    }
                                    if (!colourCell.getStringCellValue().equals("")) {
                                        colour = colourCell.getStringCellValue();
                                    }

                                    vehicleRegistrationDetails.add(
                                            new VehicleRegistrationDetails.VehicleDetailsBuilder()
                                                    .withRegistrationNumber(registrationNumber)
                                                    .withMake(make)
                                                    .withColour(colour)
                                                    .build());

                                }

                            }
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return vehicleRegistrationDetails;
                    })).flatMap(stream -> stream.reduce((data, data2) -> data));
        }
        return Optional.empty();
    }

    private InputStream getFilePath(final File file) {
        return FileReaderServiceImpl.class.getClassLoader().getResourceAsStream(file.toPath().toString());
    }
}

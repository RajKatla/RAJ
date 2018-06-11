package com.identitye2e.fs.service;

import com.identitye2e.fs.pojo.VehicleRegistrationDetails;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public interface FileReaderService {

    Optional<List<VehicleRegistrationDetails>> readVehicleRegistrationDetailsFromCSV(
            final Optional<List<File>> paths) throws FileNotFoundException;

    Optional<List<VehicleRegistrationDetails>> readVehicleRegistrationDetailsFromExcel(
            final Optional<List<File>> paths);
}

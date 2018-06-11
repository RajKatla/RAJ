package selenium.test;

import com.identitye2e.fs.data.ResourcePathUtil;
import com.identitye2e.fs.pojo.FileMetadata;
import com.identitye2e.fs.pojo.VehicleRegistrationDetails;
import com.identitye2e.fs.service.DirectoryScannerService;
import com.identitye2e.fs.service.FileReaderService;
import com.identitye2e.fs.service.FileSearchService;
import com.identitye2e.fs.service.impl.DirectoryScannerServiceImpl;
import com.identitye2e.fs.service.impl.FileReaderServiceImpl;
import com.identitye2e.fs.service.impl.FileSearchServiceImpl;
import com.identitye2e.fs.util.MIMEType;
import config.WebDriverConfig;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.webpages.VehicleInformationConfirmPage;
import selenium.webpages.VehicleInformationPage;
import selenium.webpages.VehicleSearchPage;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertTrue;
import static selenium.util.ScreenshotHelper.takeScreenShotsAndSave;

public class VehicleInformatConfirmPageTest {
    public static final String CHROME = "chrome";
    public static final String FIREFOX = "firefox";
    private static final Logger LOGGER = Logger.getLogger(VehicleInformatConfirmPageTest.class);
    final String link = "https://www.vehicleenquiry.service.gov.uk/";
    private final DirectoryScannerService directoryScannerService = new DirectoryScannerServiceImpl();
    private final FileSearchService fileSearchService = new FileSearchServiceImpl();

    private WebDriverWait wait;
    private WebDriver webDriver;
    private VehicleSearchPage vehicleSearchPage;
    private VehicleInformationPage vehicleInformationPage;
    private VehicleInformationConfirmPage vehicleInformationConfirmPage;
    private String directoryPath;
    private String colourText = "Colour";
    private String makeText = "Make";
    private String registrationNumberText = "Registration number";
    private String headerText = "Is this the vehicle you are looking for?";
    private String yesRadionText = "Yes";
    private String noRadionText = "No, search again";

    @Before
    public void setUp() throws IOException {
        webDriver = new WebDriverConfig(CHROME).getWebDriver();
        webDriver.manage().timeouts().setScriptTimeout(25, SECONDS);
        wait = new WebDriverWait(webDriver, 25);
        vehicleInformationPage = new VehicleInformationPage(webDriver, wait);
        vehicleSearchPage = new VehicleSearchPage(webDriver, wait);
        vehicleInformationConfirmPage = new VehicleInformationConfirmPage(webDriver, wait);
        directoryPath = ResourcePathUtil.getPath();
    }

    @After
    public void release() {
        webDriver.quit();
    }

    @Test
    public void testVehicleInformationConfirmPageWithDataInCSVFiles() throws IOException {
        final FileReaderService fileReaderService = new FileReaderServiceImpl();
        final Optional<String> mimeType = Optional.of(MIMEType.CSV.getMimeType());
        final Optional<List<FileMetadata>> fileMetadataList =
                directoryScannerService.scanDirectory(Optional.of(directoryPath));
        final Optional<List<File>> files = fileSearchService.searchFilesWithMIMEType(mimeType, fileMetadataList);
        final Optional<List<VehicleRegistrationDetails>> vehicleRegistrationDetails =
                fileReaderService.readVehicleRegistrationDetailsFromCSV(files);

        assertAndTakeScreenShots(vehicleRegistrationDetails);
    }

    @Test
    public void testVehicleInformationConfirmPageWithDataInExcelFiles() throws IOException {
        final Optional<String> mimeType = Optional.of(MIMEType.XLSX.getMimeType());
        final Optional<List<FileMetadata>> fileMetadataList =
                directoryScannerService.scanDirectory(Optional.of(directoryPath));
        final Optional<List<File>> files = fileSearchService.searchFilesWithMIMEType(mimeType, fileMetadataList);
        FileReaderService fileReaderService = new FileReaderServiceImpl();

        final Optional<List<VehicleRegistrationDetails>> vehicleRegistrationDetails =
                fileReaderService.readVehicleRegistrationDetailsFromExcel(files);

        assertAndTakeScreenShots(vehicleRegistrationDetails);

    }

    private void assertAndTakeScreenShots(
            final Optional<List<VehicleRegistrationDetails>> vehicleRegistrationDetails) {

        vehicleRegistrationDetails.map(details -> {
            IntStream.range(0, details.size())
                    .forEach(idx -> {
                        final VehicleRegistrationDetails data = details.get(idx);

                        final String registrationNumber = data.getRegistrationNumber();
                        LOGGER.info("Searching for registration number: " + registrationNumber);

                        vehicleSearchPage.visitVehicleSearchPage(link);
//                        assertTrue(vehicleSearchPage.isPageLoaded());

                        assertTrue(vehicleSearchPage.verifyTextBoxIsDisplayed());
                        vehicleSearchPage.enterRegistrationNumber(data.getRegistrationNumber());

                        assertTrue(vehicleSearchPage.verifyContinueButtonIsDisplayed());
                        vehicleSearchPage.visitVechicleDetailsPage();

                        assertTrue(headerText.equals(vehicleInformationConfirmPage.headerText()));

                        assertTrue(vehicleInformationConfirmPage.verifyRegistrationNumberPresent());
                        assertTrue(registrationNumberText.equals(vehicleInformationConfirmPage.registrationNumberLabel()));
                       assertTrue(data.getRegistrationNumber().equals(vehicleInformationConfirmPage.registrationNumber()));

                         assertTrue(makeText.equals(vehicleInformationConfirmPage.makeLabel()));
                        assertTrue(data.getMake().equals(vehicleInformationConfirmPage.make()));

                        assertTrue(colourText.equals(vehicleInformationConfirmPage.colourLabel()));
                        assertTrue(data.getColour().equals(vehicleInformationConfirmPage.colour()));

                        assertTrue(yesRadionText.equals(vehicleInformationConfirmPage.yesRadioText()));
                        assertTrue(noRadionText.equals(vehicleInformationConfirmPage.noRadioText()));

                        try {
                            takeScreenShotsAndSave(webDriver, idx);
                        } catch (IOException ex) {
                            LOGGER.error("Error while taking screenshots: " + ExceptionUtils.getStackTrace(ex));
                        }
                        webDriver.navigate().back();
                    });
            return null;
        });
    }

}

package selenium.test;

import config.WebDriverConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.webpages.VehicleInformationPage;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class VehicleInformationPageTest {

    public static final String CHROME = "chrome";
    public static final String FIREFOX = "firefox";

    private WebDriver webDriver;
    private WebDriverWait wait;
    private VehicleInformationPage vehicleInformationPage;
    private String headerText = "Get vehicle information from DVLA";
    private String firstChildText = "Check online to find out what information the " +
            "Driver and Vehicle Licensing Agency (DVLA) holds about a vehicle.";
    private String secondChildText = "You’ll need the vehicle’s registration number.";
    private String startButtonName = "Start now";


    @Before
    public void setUp() throws IOException {
        webDriver = new WebDriverConfig(CHROME).getWebDriver();
        wait = new WebDriverWait(webDriver, 20);
        vehicleInformationPage = new VehicleInformationPage(webDriver, wait);
    }

    @After
    public void tearDown() {
        webDriver.quit();
    }

    @Test
    public void testVehicleInformationPage() {
        final String link = "https://www.gov.uk/get-vehicle-information-from-dvla";
        vehicleInformationPage.visitGetVehicleInformationPage(link);

//        assertTrue(headerText.equals(vehicleInformationPage.headerText()));
        assertTrue(firstChildText.equals(vehicleInformationPage.firstChildText()));
        assertTrue(secondChildText.equals(vehicleInformationPage.secondChildText()));
        assertTrue(vehicleInformationPage.isStartButtonPresent());
        assertTrue(startButtonName.equals(vehicleInformationPage.startButtonName()));

    }
}

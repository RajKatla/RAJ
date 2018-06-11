package selenium.test;

import config.WebDriverConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.webpages.VehicleInformationPage;
import selenium.webpages.VehicleSearchPage;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class VehicleDetailSearchPageTest {
    public static final String CHROME = "chrome";
    public static final String FIREFOX = "firefox";

    private WebDriver webDriver;
    private WebDriverWait wait;
    private VehicleInformationPage vehicleInformationPage;
    private VehicleSearchPage vehicleSearchPage;

    private String headerText = "Enter the registration number of the vehicle";
    private String firstSpanText = "Registration number (number plate)";
    private String secondSpanText = "For example, CU57ABC";
    private String startButtonName = "Start now";


    @Before
    public void setUp() throws IOException {
        webDriver = new WebDriverConfig(CHROME).getWebDriver();
        wait = new WebDriverWait(webDriver, 20);
        vehicleInformationPage = new VehicleInformationPage(webDriver, wait);
        vehicleSearchPage = new VehicleSearchPage(webDriver, wait);
    }

    @After
    public void tearDown() {
        webDriver.quit();
    }

    @Test
    public void testVehicleInformationSearchPage() {
        final String link = "https://www.gov.uk/get-vehicle-information-from-dvla";
        vehicleInformationPage.visitGetVehicleInformationPage(link);
        vehicleInformationPage.visitVehicleInformationSearchPage();

        assertTrue(headerText.equals(vehicleSearchPage.headerText()));
        assertTrue(firstSpanText.equals(vehicleSearchPage.firstSpanText()));
        assertTrue(secondSpanText.equals(vehicleSearchPage.secondSpanText()));
        assertTrue(vehicleSearchPage.isTextBoxPresent());
        assertTrue(vehicleSearchPage.isContinueButtonPresent());
    }
}

package cucumber.stepdefinition;

import config.WebDriverConfig;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.webpages.VehicleInformationConfirmPage;
import selenium.webpages.VehicleSearchPage;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class VehicleRegistrationDetailsConfirmSteps {

    private WebDriver webDriver;
    private VehicleSearchPage vehicleSearchPage;
    private VehicleInformationConfirmPage vehicleInformationConfirmPage;

    @Before
    public void setUp() throws IOException {
        webDriver = new WebDriverConfig("chrome").getWebDriver();
        final WebDriverWait wait = new WebDriverWait(webDriver, 20);
        vehicleSearchPage = new VehicleSearchPage(webDriver, wait);
        vehicleInformationConfirmPage = new VehicleInformationConfirmPage(webDriver, wait);
    }

    @After
    public void release() {
        webDriver.quit();
    }

    @Given("^Web driver$")
    public void web_driver() {
    }

    @Given("^link \"([^\"]*)\"$")
    public void link(String link) {
        webDriver.get(link);
    }

    @When("^I search vehicle with registration number \"([^\"]*)\"$")
    public void i_search_vehicle_with_registration_number(String registrationNumber) {
        vehicleSearchPage.enterRegistrationNumber(registrationNumber);
    }

    @When("^click continue button$")
    public void click_continue_button() {
        vehicleSearchPage.visitVechicleDetailsPage();
    }

    @Then("^I should see vehicle details \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_should_see_vehicle_details_and(String make, String colur) {
        final String actualMake = vehicleInformationConfirmPage.make();
        final String actualColour = vehicleInformationConfirmPage.colour();
        assertTrue(actualMake.equals(make));
        assertTrue(actualColour.equals(colur));
    }

}

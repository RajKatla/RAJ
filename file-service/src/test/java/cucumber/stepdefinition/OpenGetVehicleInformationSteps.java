package cucumber.stepdefinition;

import config.WebDriverConfig;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.webpages.VehicleInformationPage;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class OpenGetVehicleInformationSteps {

    private WebDriver webDriver;
    private String link;
    private VehicleInformationPage vehicleInformationPage;

    @Before
    public void setUp() throws IOException {
        webDriver = new WebDriverConfig("chrome").getWebDriver();
        final WebDriverWait wait = new WebDriverWait(webDriver, 20);
        vehicleInformationPage = new VehicleInformationPage(webDriver, wait);
    }

    @After
    public void release() {
        webDriver.quit();
    }

    @Given("^a web driver$")
    public void a_web_driver() {
    }

    @Given("^a link \"([^\"]*)\"$")
    public void a_link(String link) {
        this.link = link;
    }

    @When("^I open above link$")
    public void i_open_above_link() {
        vehicleInformationPage.visitGetVehicleInformationPage(link);
    }

    @Then("^I should see Get vehicle information from DVLA page$")
    public void i_should_see_Get_vehicle_information_from_DVLA_page() {
        System.out.println("inside then-1");
    }

    /*@Then("^I should see header text \"([^\"]*)\"$")
    public void i_should_see_header_text(String header) {
        assertTrue(vehicleInformationPage.headerText().equals(header));
    }And I should see header text "Get vehicle information from DVLA"*/

    @Then("^I should see first text \"([^\"]*)\"$")
    public void i_should_see_first_text(String text) {
        assertTrue(vehicleInformationPage.firstChildText().equals(text));
    }

    @Then("^I should see second text \"([^\"]*)\"$")
    public void i_should_see_second_text(String text) {
        assertTrue(vehicleInformationPage.secondChildText().equals(text));
    }
    @Then("^I should see \"([^\"]*)\" button$")
    public void i_should_see_button(String buttonName) throws Throwable {
        assertTrue(vehicleInformationPage.isStartButtonPresent());
        assertTrue(vehicleInformationPage.startButtonName().equals(buttonName));
    }
}

package cucumber.stepdefinition;

import config.WebDriverConfig;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Ignore;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.webpages.VehicleInformationPage;
import selenium.webpages.VehicleSearchPage;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

@Ignore
public class VehicleRegistrationDetailsSearchSteps {

    private WebDriver webDriver;
    private String link;
    private VehicleInformationPage vehicleInformationPage;
    private VehicleSearchPage vehicleSearchPage;

    @Before
    public void setUp() throws IOException {
        webDriver = new WebDriverConfig("chrome").getWebDriver();
        final WebDriverWait wait = new WebDriverWait(webDriver, 20);
        vehicleInformationPage = new VehicleInformationPage(webDriver, wait);
        vehicleSearchPage = new VehicleSearchPage(webDriver, wait);
    }

    @After
    public void release() {
        webDriver.quit();
    }

    @Given("^web driver$")
    public void web_driver() {}

    @Given("^page link \"([^\"]*)\"$")
    public void page_link(String link) {
        webDriver.get(link);
    }

    @When("^I click Start now button$")
    public void i_click_Start_now_button() {
        vehicleInformationPage.visitVehicleInformationSearchPage();
    }

    @Then("^I should redirect to vehicle information search page$")
    public void i_should_redirect_to_vehicle_information_search_page() {}

    @Then("^I should see header \"([^\"]*)\"$")
    public void i_should_see_header(final String header) {
        assertTrue(vehicleSearchPage.headerText().equals(header));
    }

    @Then("^I should see a form label with text \"([^\"]*)\"$")
    public void i_should_see_a_form_label_with_text(String label) {
        assertTrue(vehicleSearchPage.firstSpanText().equals(label));
    }

    @Then("^I should see a text \"([^\"]*)\"$")
    public void i_should_see_a_text(String label) {
        assertTrue(vehicleSearchPage.secondSpanText().equals(label));
    }

    @Then("^I should see a text box to enter vehicle registration number$")
    public void i_should_see_a_text_box_to_enter_vehicle_registration_number() {
        assertTrue(vehicleSearchPage.isTextBoxPresent());
    }

    @Then("^I should see button \"([^\"]*)\"$")
    public void i_should_see_button(final String continueButton) {
        assertTrue(vehicleSearchPage.isContinueButtonPresent());
        assertTrue(vehicleSearchPage.continueButtonName().equals(continueButton));
    }
}

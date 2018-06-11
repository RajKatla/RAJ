package selenium.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.PageFactory.initElements;

public class VehicleSearchPage {

    private WebDriverWait wait;
    private WebDriver webDriver;

    @FindBy(xpath = "//*[@id=\"content\"]/form/div/div/h1")
    private WebElement header;

    @FindBy(xpath = "//*[@id=\"content\"]/form/div/div/div[2]/fieldset/div/label/span[1]")
    private WebElement firstSpan;

    @FindBy(xpath = "//*[@id=\"content\"]/form/div/div/div[2]/fieldset/div/label/span[2]")
    private WebElement secondSpan;

    @FindBy(xpath = "//*[@id=\"Vrm\"]")
    private WebElement textBox;

    @FindBy(xpath = "//*[@id=\"content\"]/form/div/div/div[2]/fieldset/button")
    private WebElement continueButton;

    public VehicleSearchPage(final WebDriver webDriver, final WebDriverWait wait) {
        this.webDriver = webDriver;
        this.wait = wait;
        initElements(webDriver, this);
    }

    public void visitVehicleSearchPage(final String link) {
        webDriver.get(link);
    }

    public boolean isPageLoaded(){
        return (wait).until(ExpectedConditions.visibilityOf(header)).isDisplayed();
    }
    public String headerText() {
        return (wait).until(ExpectedConditions.visibilityOf(header)).getText();
    }

    public String firstSpanText() {
        return (wait).until(ExpectedConditions.visibilityOf(firstSpan)).getText();
    }

    public String secondSpanText() {
        return (wait).until(ExpectedConditions.visibilityOf(secondSpan)).getText();
    }

    public boolean isTextBoxPresent() {
        return isElementVisible(textBox);
    }

    public boolean isContinueButtonPresent() {
        return continueButton.isDisplayed();
    }

    public String continueButtonName() {
        return continueButton.getText();
    }

    private boolean isElementVisible(WebElement element) {
        return (wait).until(ExpectedConditions.elementToBeClickable(element)).isDisplayed();
    }

    public boolean verifyTextBoxIsDisplayed() {
        return (wait).until(ExpectedConditions.elementToBeClickable(textBox)).isDisplayed();
    }

    public boolean verifyContinueButtonIsDisplayed() {
        return (wait).until(ExpectedConditions.elementToBeClickable(continueButton)).isDisplayed();
    }
    public void enterRegistrationNumber(final String vehicleRegistrationNumber) {
        (wait).until(ExpectedConditions.elementToBeClickable(textBox)).sendKeys(vehicleRegistrationNumber);
    }

    public void visitVechicleDetailsPage() {
        (wait).until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }
}

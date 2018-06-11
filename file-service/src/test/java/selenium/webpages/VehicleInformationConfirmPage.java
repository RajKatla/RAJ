package selenium.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.PageFactory.initElements;

public class VehicleInformationConfirmPage {

    private WebDriverWait wait;
    private WebDriver webDriver;

    @FindBy(xpath = "//*[@id=\"pr3\"]/div/h1")
    private WebElement header;

    @FindBy(xpath = "//*[@id=\"pr3\"]/div/ul/li[1]/span[1]")
    private WebElement registrationNumberText;

    @FindBy(xpath = "//*[@id=\"pr3\"]/div/ul/li[1]/span[2]")
    private WebElement registrationNumberValue;

    @FindBy(xpath = "//*[@id=\"pr3\"]/div/ul/li[2]/span[1]")
    private WebElement makeText;

    @FindBy(xpath = "//*[@id=\"pr3\"]/div/ul/li[2]/span[2]/strong")
    private WebElement makeValue;

    @FindBy(xpath = "//*[@id=\"pr3\"]/div/ul/li[3]/span[1]")
    private WebElement colourText;

    @FindBy(xpath = "//*[@id=\"pr3\"]/div/ul/li[3]/span[2]/strong")
    private WebElement colourValue;

    @FindBy(xpath = "//*[@id=\"Correct_True\"]")
    private WebElement yesRadio;

    @FindBy(xpath = "//*[@id=\"pr3\"]/div/div[2]/fieldset/div[1]/label")
    private WebElement yesRadioText;

    @FindBy(xpath = "//*[@id=\"Correct_False\"]")
    private WebElement noRadio;

    @FindBy(xpath = "//*[@id=\"pr3\"]/div/div[2]/fieldset/div[2]/label")
    private WebElement noRadioText;

    @FindBy(xpath = "//*[@id=\"pr3\"]/div/details/summary/span")
    private WebElement incorrectVahicleDetailsLink;

    @FindBy(xpath = "//*[@id=\"pr3\"]/div/button")
    private WebElement continueBuuton;

    public VehicleInformationConfirmPage(final WebDriver webDriver, final WebDriverWait wait) {
        this.webDriver = webDriver;
        this.wait = wait;
        initElements(webDriver, this);
    }

    public String headerText() {
        return (wait).until(ExpectedConditions.visibilityOf(header)).getText();
    }

    public String registrationNumberLabel() {
        return (wait).until(ExpectedConditions.visibilityOf(registrationNumberText)).getText();
    }

    public boolean verifyRegistrationNumberPresent(){
        return registrationNumberValue.isDisplayed();
    }
    public String registrationNumber() {
        return (wait).until(ExpectedConditions.visibilityOf(registrationNumberValue)).getText();
    }

    public String makeLabel() {
        return (wait).until(ExpectedConditions.visibilityOf(makeText)).getText();
    }

    public String make() {
        return (wait).until(ExpectedConditions.visibilityOf(makeValue)).getText();
    }

    public String colourLabel() {
        return (wait).until(ExpectedConditions.visibilityOf(colourText)).getText();
    }

    public String colour() {
        return (wait).until(ExpectedConditions.elementToBeClickable(colourValue)).getText();
    }

    public boolean isYesRadioPresent() {
        return (wait).until(ExpectedConditions.elementToBeClickable(yesRadio)).isDisplayed();
    }

    public String yesRadioText() {
        return (wait).until(ExpectedConditions.visibilityOf(yesRadioText)).getText();
    }

    public String noRadioText() {
        return (wait).until(ExpectedConditions.visibilityOf(noRadioText)).getText();
    }

    public boolean isNoRadioPresent() {
        return (wait).until(ExpectedConditions.elementToBeClickable(noRadio)).isDisplayed();
    }

    public boolean isIncorrectDetailsLinkPresent() {
        return (wait).until(ExpectedConditions.elementToBeClickable(incorrectVahicleDetailsLink)).isDisplayed();
    }
    public boolean isContinueButtonPresent() {
        return (wait).until(ExpectedConditions.elementToBeClickable(continueBuuton)).isDisplayed();
    }

}

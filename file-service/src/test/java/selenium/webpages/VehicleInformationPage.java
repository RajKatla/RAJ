package selenium.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.PageFactory.initElements;

public class VehicleInformationPage {

    private WebDriverWait wait;
    private WebDriver webDriver;

    @FindBy(className = "//*[@id=\"content\"]/header/div/h1")
    private WebElement headerText;

    @FindBy(xpath = "//*[@id=\"content\"]/div/div[1]/div/section[1]/div/p[1]")
    private WebElement firstChildText;

    @FindBy(xpath = "//*[@id=\"content\"]/div/div[1]/div/section[1]/div/p[2]")
    private WebElement secondChildText;

    @FindBy(xpath = "//*[@id=\"get-started\"]/a")
    private WebElement startButton;

    public VehicleInformationPage(final WebDriver webDriver, final WebDriverWait wait) {
        this.webDriver = webDriver;
        this.wait = wait;
        initElements(webDriver, this);
    }

    public void visitGetVehicleInformationPage(final String link) {
        webDriver.get(link);
    }

    public String headerText() {
        return (wait).until(ExpectedConditions.visibilityOf(headerText)).getText();
    }

    public String firstChildText() {
        return (wait).until(ExpectedConditions.visibilityOf(firstChildText)).getText();
    }

    public String secondChildText() {
        return (wait).until(ExpectedConditions.visibilityOf(secondChildText)).getText();
    }

    public boolean isStartButtonPresent() {
        return (wait).until(ExpectedConditions.visibilityOf(startButton)).isDisplayed();
    }

    public String startButtonName() {
        return (wait).until(ExpectedConditions.visibilityOf(startButton)).getText();
    }

    public void visitVehicleInformationSearchPage() {
        (wait).until(ExpectedConditions.elementToBeClickable(startButton)).click();
    }
}

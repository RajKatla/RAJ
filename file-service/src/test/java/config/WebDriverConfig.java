package config;

import com.identitye2e.fs.util.AppPropertiesLoader;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class WebDriverConfig {
    private static final Logger LOGGER = Logger.getLogger(WebDriverConfig.class);

    private WebDriver webDriver;

    public WebDriverConfig(final String driverType) throws IOException {
        setDriver(driverType);
    }

    private static String getDriver(final String driverPath) {
        return Objects.requireNonNull(WebDriverConfig.class.getClassLoader().getResource(driverPath)).getPath();
    }

    private void setDriver(final String browserType) throws IOException {
        final String firefoxDriverPath = new AppPropertiesLoader().getFirefoxDriverPath();
        final String chromeDriverPath = new AppPropertiesLoader().getChromeDriverPath();

        switch (browserType) {
            case "chrome":
                final String chromeDriver = getDriver(chromeDriverPath);
                webDriver = initChromeDriver(chromeDriver);
                break;
            case "firefox":
                final String firefoxDriver = getDriver(firefoxDriverPath);
                webDriver = initFirefoxDriver(firefoxDriver);
                break;
            default:
                LOGGER.info("browser : " + browserType
                        + " is invalid, Launching Firefox as browser of choice..");
                webDriver = initFirefoxDriver(firefoxDriverPath);
        }
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    private WebDriver initChromeDriver(final String chromeDriver) {
        LOGGER.info("Initializing chrome driver..");
        System.setProperty("webdriver.chrome.driver", chromeDriver);
        final ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(true);
        webDriver = new ChromeDriver(chromeOptions);
        setDriverImplicitlyWait();
        return webDriver;
    }

    private void setDriverImplicitlyWait() {
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    private WebDriver initFirefoxDriver(final String firefoxDriver) {
        LOGGER.info("Initializing firefox driver..");
        System.setProperty("webdriver.gecko.driver", firefoxDriver);
        final FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);
        webDriver = new FirefoxDriver(options);
        setDriverImplicitlyWait();
        return webDriver;
    }


}

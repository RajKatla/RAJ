package selenium.util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotHelper {
    private static final String PNG = ".png";
    private static final String SCREENSHOTS_PATH = "screenshots/screenshot";

    public static void takeScreenShotsAndSave(final WebDriver webDriver, final int row) throws IOException {
        File scrFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(SCREENSHOTS_PATH + row + PNG));
    }
}

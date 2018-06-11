package com.identitye2e.fs.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppPropertiesLoader {

    private static final String PROPERTIES_PATH = "application.properties";
    private static final String FILES_PATH = "directory.path";
    private static final String FIREFOX_DRIVER_PATH = "webdriver.firefox.path";
    private static final String CHROME_DRIVER_PATH = "webdriver.chrome.path";
    private final Properties properties = new Properties();

    public AppPropertiesLoader() throws IOException {
        loadProperties();
    }

    private void loadProperties() throws IOException {
        final InputStream appProperties = getClass().getClassLoader().getResourceAsStream(PROPERTIES_PATH);
        properties.load(appProperties);
    }

    public String getDirectoryPath() {
        return properties.getProperty(FILES_PATH);
    }

    public String getFirefoxDriverPath() {
        return properties.getProperty(FIREFOX_DRIVER_PATH);
    }

    public String getChromeDriverPath() {
        return properties.getProperty(CHROME_DRIVER_PATH);
    }
}

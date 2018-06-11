package com.identitye2e.fs.util;

import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class AppPropertiesLoaderTest {

    @Test
    public void testLoadProperties() throws IOException {
        final AppPropertiesLoader appPropertiesLoader = new AppPropertiesLoader();
        final String directoryPath = appPropertiesLoader.getDirectoryPath();

        assertNotNull(directoryPath);
        assertThat(directoryPath, is("files"));

    }

}

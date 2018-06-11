package com.identitye2e.fs.data;

import com.identitye2e.fs.util.AppPropertiesLoader;

import java.io.IOException;

public class ResourcePathUtil {

    public static String getPath() throws IOException {
        final AppPropertiesLoader appPropertiesLoader = new AppPropertiesLoader();
        final String directoryPath = appPropertiesLoader.getDirectoryPath();
        return ResourcePathUtil.class.getClassLoader().getResource(directoryPath).getPath();
   }
}

package com.identitye2e.fs.data;

import com.identitye2e.fs.pojo.FileMetadata;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;

public class TestData {

    public static FileMetadata getFileMetadata() {
        return new FileMetadata
                .FileMetadataBuilder()
                .withName(getRandomString())
                .withExtension(getRandomString())
                .withMIMEType(getRandomString())
                .withSize(getRandomLong())
                .build();
    }

    private static String getRandomString() {
        return RandomStringUtils.randomAlphabetic(10);
    }

    private static long getRandomLong() {
        return RandomUtils.nextLong();
    }
}

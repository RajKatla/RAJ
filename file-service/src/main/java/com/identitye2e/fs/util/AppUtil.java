package com.identitye2e.fs.util;

import java.util.Optional;

public class AppUtil {
    public static boolean checkNotNull(final Object object) {
        return Optional.ofNullable(object).isPresent();
    }
}

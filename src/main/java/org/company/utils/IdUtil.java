package org.company.utils;

public class IdUtil {

    public static String generateUniqueId() {
        return java.util.UUID.randomUUID().toString();
    }
}

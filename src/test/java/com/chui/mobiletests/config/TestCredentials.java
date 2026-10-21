package com.chui.mobiletests.config;

public class TestCredentials {

    public static String validEmail() {
        return requiredEnv("NICEQUEST_EMAIL");
    }

    public static String validPassword() {
        return requiredEnv("NICEQUEST_PASSWORD");
    }

    private static String requiredEnv(String name) {
        String value = System.getenv(name);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException("Missing environment variable: " + name);
        }
        return value;
    }
}
package com.chui.mobiletests.config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.MutableCapabilities;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Properties;

public class DriverFactory {

    private static Properties loadProperties(String platform) {
        String path = "config/" + platform + ".properties";
        try (InputStream in = DriverFactory.class.getClassLoader().getResourceAsStream(path)) {
            if (in == null) {
                throw new IllegalArgumentException("Configuration file not found: " + path);
            }
            Properties props = new Properties();
            props.load(in);
            return props;
        } catch (IOException e) {
            throw new IllegalStateException("Could not read " + path, e);
        }
    }

    private static MutableCapabilities buildCapabilities(Properties props) {
        MutableCapabilities capabilities = new MutableCapabilities();
        for (String key : props.stringPropertyNames()) {
            if (key.equals("server.url")) {
                continue;
            }
            String value = props.getProperty(key);
            String capabilityName = key.equals("platformName") ? key : "appium:" + key;
            capabilities.setCapability(capabilityName, parseValue(value));
        }
        return capabilities;
    }

    private static Object parseValue(String value) {
        if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) {
            return Boolean.parseBoolean(value);
        }
        return value;
    }

    public static AppiumDriver createDriver() {
        String platform = System.getProperty("platform", "android").toLowerCase();
        Properties props = loadProperties(platform);
        MutableCapabilities capabilities = buildCapabilities(props);
        URL serverUrl = toUrl(props.getProperty("server.url"));

        return switch (platform) {
            case "android" -> new AndroidDriver(serverUrl, capabilities);
            case "ios" -> new IOSDriver(serverUrl, capabilities);
            default -> throw new IllegalArgumentException(
                    "Unsupported platform: " + platform + " (use android or ios)");
        };
    }

    private static URL toUrl(String value) {
        try {
            return URI.create(value).toURL();
        } catch (MalformedURLException e) {
            throw new IllegalStateException("Invalid server.url: " + value, e);
        }
    }
}
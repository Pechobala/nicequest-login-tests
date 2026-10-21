package com.chui.mobiletests.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class DashboardPage extends BasePage {

    private static final By START_TAB_BUTTON = By.id("start-tab-button");
    private static final By STORE_TAB_BUTTON = By.id("store-tab-button");
    private static final By ACCOUNT_TAB_BUTTON = By.id("account-tab-button");

    public DashboardPage(AppiumDriver driver) {
        super(driver);
    }

    public boolean isNavigationBarDisplayed() {
        return isDisplayed(START_TAB_BUTTON)
                && isDisplayed(STORE_TAB_BUTTON)
                && isDisplayed(ACCOUNT_TAB_BUTTON);
    }
}

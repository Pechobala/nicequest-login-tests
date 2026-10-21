package com.chui.mobiletests.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    private static final By EMAIL_INPUT = By.id("sign-in-email-input");
    private static final By PASSWORD_INPUT = By.id("sign-in-password-input");
    private static final By SIGN_IN_BUTTON = By.id("sign-in-button");
    private static final By FORGOT_PASSWORD_BUTTON = By.id("sign-in-forget-password-button");
    private static final String INVALID_CREDENTIALS_MESSAGE = "Nombre de usuario y / o contraseña incorrectos";
    private static final By INVALID_CREDENTIALS_ERROR =
            By.xpath("//android.widget.TextView[@text='" + INVALID_CREDENTIALS_MESSAGE + "']");
    private static final By START_TAB_BUTTON = By.id("start-tab-button");
    private static final By STORE_TAB_BUTTON = By.id("store-tab-button");
    private static final By ACCOUNT_TAB_BUTTON = By.id("account-tab-button");

    public LoginPage(AppiumDriver driver) {
        super(driver);
    }

    public void loginWith(String email, String password) {
        enterCredentials(email, password);
        click(SIGN_IN_BUTTON);
    }

    public void enterCredentials(String email, String password) {
        type(EMAIL_INPUT, email);
        type(PASSWORD_INPUT, password);
    }

    public boolean isSignInButtonEnabled() {
        return isEnabled(SIGN_IN_BUTTON);
    }

    public boolean isInvalidCredentialsErrorDisplayed() {
        return isDisplayed(INVALID_CREDENTIALS_ERROR);
    }

    public boolean isLoginScreenDisplayed() {
        return isDisplayed(EMAIL_INPUT);
    }
}

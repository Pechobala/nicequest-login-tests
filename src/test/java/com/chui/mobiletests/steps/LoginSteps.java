package com.chui.mobiletests.steps;

import com.chui.mobiletests.config.TestCredentials;
import com.chui.mobiletests.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {


    private static final String UNREGISTERED_EMAIL = "unregistered.user@example.com";
    private static final String WRONG_PASSWORD = "wrong-password-123";

    private final TestContext context;

    public LoginSteps(TestContext context) {
        this.context = context;
    }

    private LoginPage loginPage() {
        return new LoginPage(context.getDriver());
    }


    @Given("the user is on the login screen")
    public void theUserIsOnTheLoginScreen() {
        assertTrue(loginPage().isLoginScreenDisplayed(), "Login screen was not displayed");
    }

    @When("the user logs in with valid credentials")
    public void theUserLogsInWithValidCredentials() {
        loginPage().loginWith(TestCredentials.validEmail(), TestCredentials.validPassword());
    }

    @When("the user enters email {string} and password {string}")
    public void theUserEntersEmailAndPassword(String email, String password) {
        loginPage().enterCredentials(email, password);
    }

    @When("the user logs in with an unregistered email")
    public void theUserLogsInWithAnUnregisteredEmail(){
        loginPage().loginWith(UNREGISTERED_EMAIL, WRONG_PASSWORD);
    }

    @When("the user logs in with a wrong password")
    public void theUserLogsInWithAWrongPassword(){
        loginPage().loginWith(TestCredentials.validEmail(), WRONG_PASSWORD);
    }

    @Then("the invalid credentials error is displayed")
    public void theInvalidCredentialsErrorIsDisplayed(){
        assertTrue(loginPage().isInvalidCredentialsErrorDisplayed(), "The credential error message was not displayed.");
    }

    @Then("the login button is disabled")
    public void theLoginButtonIsDisabled() {
        assertFalse(loginPage().isSignInButtonEnabled(), "The Sign In button is not disabled.");
    }
}

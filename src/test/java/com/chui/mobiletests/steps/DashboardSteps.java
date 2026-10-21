package com.chui.mobiletests.steps;

import com.chui.mobiletests.pages.DashboardPage;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DashboardSteps {
    private final TestContext context;

    public DashboardSteps(TestContext context) {
        this.context = context;
    }

    private DashboardPage dashboardPage() {
        return new DashboardPage(context.getDriver());
    }

    // We consider the dashboard is displayed when the buttons Start, Store and Account are displayed.
    @Then("the Dashboard is displayed")
    public void theDashboardIsDisplayed() {
        assertTrue(dashboardPage().isNavigationBarDisplayed(), "Dashboard screen was not displayed");
    }
}

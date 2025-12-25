package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AlertPage;

public class AlertTests extends BaseTest {

    @Test
    public void handleTextboxAlert() {

        AlertPage alertPage = new AlertPage(driver);


        driver.get("https://demo.automationtesting.in/Alerts.html");
        alertPage.clickAlertWithTextboxTab();
        alertPage.clickPromptButton();

        String fullName = "Nika Kanashvili";
        alertPage.handlePromptAlert(fullName);


        Assert.assertTrue(alertPage.getResultText().contains(fullName),
                "Expected name not found in alert result text!");
    }
}
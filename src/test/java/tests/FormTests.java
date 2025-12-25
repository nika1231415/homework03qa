package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FormPage;

public class FormTests extends BaseTest {

    @Test
    public void fillAndSubmitForm() {
        FormPage formPage = new FormPage(driver);

        driver.get("https://demoqa.com/automation-practice-form");

        formPage.fillPersonalDetails("Nika", "Kanashvili", "nika@test.com", "1234567890");
        formPage.selectDateOfBirth("May", "1998", "15");
        formPage.setSubject("Maths");
        formPage.setAddress("Tbilisi, Georgia", "NCR", "Delhi");
        formPage.submitForm();

        String result = formPage.getConfirmationModalText();
        Assert.assertTrue(result.contains("Nika Kanashvili"));
        Assert.assertTrue(result.contains("nika@test.com"));
        Assert.assertTrue(result.contains("Male"));
        Assert.assertTrue(result.contains("1234567890"));
        Assert.assertTrue(result.contains("Maths"));
        Assert.assertTrue(result.contains("Sports"));
        Assert.assertTrue(result.contains("Tbilisi, Georgia"));
        Assert.assertTrue(result.contains("NCR Delhi"));
    }
}
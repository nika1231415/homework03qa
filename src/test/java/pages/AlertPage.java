package pages;

import io.qameta.allure.Step; // <-- Allure import
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AlertPage {
    private WebDriver driver;

    // Locators
    private By textboxTab = By.xpath("//a[contains(text(),'Alert with Textbox')]");
    private By promptButton = By.xpath("//button[contains(text(),'click the button to demonstrate the prompt box')]");
    private By resultText = By.id("demo1");

    public AlertPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions with Allure Steps

    @Step("Navigating to the 'Alert with Textbox' tab")
    public void clickAlertWithTextboxTab() {
        driver.findElement(textboxTab).click();
    }

    @Step("Clicking the button to trigger the prompt box")
    public void clickPromptButton() {
        driver.findElement(promptButton).click();
    }

    @Step("Switching to alert and typing text: '{0}'")
    public void handlePromptAlert(String text) {
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(text);
        alert.accept();
    }

    @Step("Retrieving the result text displayed on the page")
    public String getResultText() {
        return driver.findElement(resultText).getText();
    }
}
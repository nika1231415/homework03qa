package pages;

import io.qameta.allure.Step; // <-- New Import
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class FormPage {
    private WebDriver driver;

    // Locators remain private
    private By firstName = By.id("firstName");
    private By lastName = By.id("lastName");
    private By userEmail = By.id("userEmail");
    private By genderMale = By.xpath("//label[text()='Male']");
    private By userNumber = By.id("userNumber");
    private By dateInput = By.id("dateOfBirthInput");
    private By monthSelect = By.className("react-datepicker__month-select");
    private By yearSelect = By.className("react-datepicker__year-select");
    private By subjectsInput = By.id("subjectsInput");
    private By hobbySports = By.xpath("//label[text()='Sports']");
    private By address = By.id("currentAddress");
    private By stateDropdown = By.id("state");
    private By cityDropdown = By.id("city");
    private By submitButton = By.id("submit");
    private By modalContent = By.className("modal-content");

    public FormPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions decorated with Allure @Step
    // {0}, {1} etc. refer to the parameters passed into the method

    @Step("Entering personal details: Name: {0} {1}, Email: {2}, Phone: {3}")
    public void fillPersonalDetails(String first, String last, String email, String phone) {
        driver.findElement(firstName).sendKeys(first);
        driver.findElement(lastName).sendKeys(last);
        driver.findElement(userEmail).sendKeys(email);
        driver.findElement(genderMale).click();
        driver.findElement(userNumber).sendKeys(phone);
    }

    @Step("Selecting date of birth: {0} {1}, {2}")
    public void selectDateOfBirth(String month, String year, String day) {
        driver.findElement(dateInput).click();
        new Select(driver.findElement(monthSelect)).selectByVisibleText(month);
        new Select(driver.findElement(yearSelect)).selectByVisibleText(year);
        driver.findElement(By.xpath("//div[contains(@class,'react-datepicker__day') and text()='" + day + "']")).click();
    }

    @Step("Setting subject to: {0}")
    public void setSubject(String subject) {
        WebElement input = driver.findElement(subjectsInput);
        input.sendKeys(subject);
        input.sendKeys(Keys.ENTER);
    }

    @Step("Setting address: {0} in {1}, {2}")
    public void setAddress(String addr, String state, String city) {
        // FIX: Use JavaScript executor for the Hobby checkbox to bypass overlays
        WebElement sportsHobby = driver.findElement(hobbySports);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", sportsHobby);

        driver.findElement(address).sendKeys(addr);

        driver.findElement(stateDropdown).click();
        driver.findElement(By.xpath("//div[contains(text(),'" + state + "')]")).click();

        driver.findElement(cityDropdown).click();
        driver.findElement(By.xpath("//div[contains(text(),'" + city + "')]")).click();
    }

    @Step("Submitting the registration form")
    public void submitForm() {
        WebElement btn = driver.findElement(submitButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
    }

    @Step("Reading the confirmation modal text")
    public String getConfirmationModalText() {
        return driver.findElement(modalContent).getText();
    }
}
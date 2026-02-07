package org.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
    WebDriver driver;

    public LandingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(id = "userEmail")
    WebElement userEmail;
    @FindBy(id = "userPassword")
    WebElement password;
    @FindBy(id = "login")
    WebElement loginButton;

    @FindBy(css="[class*='flyInOut']")
    WebElement errorText;


    public ProductCatalogue loginToApplication(String email, String loginpassword) {
        userEmail.sendKeys(email);
        password.sendKeys(loginpassword);
        loginButton.click();
        return new ProductCatalogue(driver);
    }

    public String getErrorText() {
        return errorText.getText();

    }


}
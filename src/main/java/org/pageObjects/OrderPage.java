package org.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.utils.BaseMethods;

import java.util.List;

public class OrderPage extends BaseMethods {
    WebDriver driver;

    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".hero-primary")
    WebElement confirmationMessage;
    @FindBy(css = ".m-3 :first-child")
    WebElement product;
    By orderSummaryPage = By.cssSelector(".order-summary");

    public boolean verifyOrderSubmission(String ProductName, String confirmationMesage) {
        waitForElementToAppear(orderSummaryPage);
        return confirmationMessage.getText().equalsIgnoreCase(confirmationMesage) && product.getText().equals(ProductName);
    }


}
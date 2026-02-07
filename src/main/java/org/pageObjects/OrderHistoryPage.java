package org.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.utils.BaseMethods;

import java.util.List;

public class OrderHistoryPage extends BaseMethods {
    WebDriver driver;

    public OrderHistoryPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "tr td:nth-child(3)")
    List<WebElement> productNames;

    public boolean getProduct(String ProductName) {
        return productNames.stream().anyMatch(e -> e.getText().equals(ProductName));
    }


}
package org.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pageObjects.OrderHistoryPage;

import java.time.Duration;

public class BaseMethods {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css="[routerlink*='myorders']")
    WebElement ordersLink;

    public BaseMethods(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void waitForElementToAppear(By by) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void waitForElementToBeInvisible(WebElement by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(by));
    }

    public OrderHistoryPage clickOrderHistory() {
        ordersLink.click();
        return new OrderHistoryPage(driver);
    }

}

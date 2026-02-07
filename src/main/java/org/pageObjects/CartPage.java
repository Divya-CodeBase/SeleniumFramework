package org.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.utils.BaseMethods;

import java.util.List;

public class CartPage extends BaseMethods {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".cartSection h3")
    List<WebElement> cartProducts;
    @FindBy(css = ".totalRow button")
    WebElement checkoutButton;
    By paymentPage = By.cssSelector(".item__details .item__title");

    public boolean verifyItemInCart(String ProductName) {
        return cartProducts.stream().anyMatch(e -> e.getText().equals(ProductName));
    }

    public PaymentPage proceedPayment() {
        checkoutButton.click();
        waitForElementToAppear(paymentPage);
        return new PaymentPage(driver);
    }


}
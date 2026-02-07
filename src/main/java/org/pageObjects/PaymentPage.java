package org.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.utils.BaseMethods;

public class PaymentPage extends BaseMethods {
    WebDriver driver;

    public PaymentPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(css = ".item__details .item__title")
    WebElement paymentPageProduct;
    @FindBy(xpath = "//*[contains(text(),'CVV')]/../input")
    WebElement inputCVV;

    @FindBy(name = "coupon")
    WebElement inputCoupon;

    @FindBy(css = "[type='submit']")
    WebElement submitCoupon;

    @FindBy(css = "[placeholder='Select Country']")
    WebElement inputCountry;

    @FindBy(css = ".action__submit")
    WebElement submitPayment;

    By couponSuccessText = By.cssSelector("[name='coupon']+p");
    By countryList = By.cssSelector(".ta-item");


    public boolean isProductInPaymentPageVisible(String ProductName) {
        return paymentPageProduct.getText().equals(ProductName);
    }

    public boolean enterPaymentAndCouponDetails(String cvv, String coupon) {
        inputCVV.sendKeys(cvv);
        inputCoupon.sendKeys(coupon);
        submitCoupon.click();
        waitForElementToAppear(couponSuccessText);
        return driver.findElement(couponSuccessText).getText().equals("* Coupon Applied");
    }

    public OrderPage enterCountryDetailSubmit(String country) {
        Actions action = new Actions(driver);
        action.sendKeys(inputCountry, country).build().perform();
        waitForElementToAppear(countryList);
        driver.findElement(By.xpath("//button[contains(@class,'ta-item')]//*[normalize-space()='" + country + "']")).click();
        submitPayment.click();
        return new OrderPage(driver);

    }


}
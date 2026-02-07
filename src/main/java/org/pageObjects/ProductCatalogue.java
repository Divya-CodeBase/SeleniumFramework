package org.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.utils.BaseMethods;

import java.util.List;

public class ProductCatalogue extends BaseMethods {
    WebDriver driver;

    public ProductCatalogue(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".mb-3")
    List<WebElement> productList;
    By productLists = By.cssSelector(".mb-3");
    By productAddMessage = By.cssSelector("#toast-container");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By cartPage = By.cssSelector(".cartSection");
    @FindBy(css = ".ng-animating")
    WebElement loadingScreen;
    @FindBy(css = "[routerlink*='cart']")
    WebElement cartLink;


    public List<WebElement> getProductList() {
        waitForElementToAppear(productLists);
        return productList;
    }

    public WebElement getProduct( String ProductName) {
        return getProductList().stream().filter(e -> e.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst().orElse(null);
    }

    public void addProductToCart(String ProductName) {
        getProduct(ProductName).findElement(addToCart).click();
        waitForElementToAppear(productAddMessage);
        waitForElementToBeInvisible(loadingScreen);
    }

    public CartPage navigateToCart() {
        cartLink.click();
        waitForElementToAppear(cartPage);
        return new CartPage(driver);
    }

}
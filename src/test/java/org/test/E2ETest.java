package org.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.List;

public class E2ETest {

    public static void main(String args[]) {

        String productName = "ADIDAS ORIGINAL";
        String cvv = "123";
        String coupon = "rahulshettyacademy";
        String country = "India";

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        //landing page
        driver.get("https://rahulshettyacademy.com/client/#/auth/login");
        driver.findElement(By.id("userEmail")).sendKeys("testuser001@mail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Learn@123");
        driver.findElement(By.id("login")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        WebElement productItem = products.stream().filter(e -> e.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
        productItem.findElement(By.cssSelector(".card-body button:last-of-type")).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));


        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cartSection")));

        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        Assert.assertTrue(cartProducts.stream().anyMatch(e -> e.getText().equals(productName)));

        driver.findElement(By.cssSelector(".totalRow button")).click();

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".item__details .item__title"))));

        Assert.assertTrue(driver.findElement(By.cssSelector(".item__details .item__title")).getText().equals(productName));


        driver.findElement(By.xpath("//*[contains(text(),'CVV')]/../input")).sendKeys(cvv);
        driver.findElement(By.cssSelector("[name=coupon]")).sendKeys(coupon);
        driver.findElement(By.cssSelector("[type='submit']")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[name='coupon']+p"))));
        Assert.assertTrue(driver.findElement(By.cssSelector("[name='coupon']+p")).getText().equals("* Coupon Applied"));

        Actions action = new Actions(driver);
        action.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), country).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-item")));

        driver.findElement(By.xpath("//button[contains(@class,'ta-item')]//*[normalize-space()='" + country + "']")).click();

        driver.findElement(By.cssSelector(".action__submit")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".order-summary")));
        Assert.assertTrue(driver.findElement(By.cssSelector(".hero-primary")).getText().equalsIgnoreCase("THANKYOU FOR THE ORDER."));

        Assert.assertTrue(driver.findElement(By.cssSelector(".m-3 :first-child")).getText().equals(productName));

        driver.quit();


    }

}
//{
//    "UserEmail" : "testuser001@mail.com",
//    "password" : "Learn@123",
//    "ProductName" : "ADIDAS ORIGINAL"
//}
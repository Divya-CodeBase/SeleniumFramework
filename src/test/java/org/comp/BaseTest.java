package org.comp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.pageObjects.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    String url;
    public LandingPage landingPage;
    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    protected void setDriver(WebDriver driver) {
        tlDriver.set(driver);
    }

    protected WebDriver getDriver() {
        return tlDriver.get();
    }

    public WebDriver initializeDriver() throws IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/org/resources/GlobalData.properties");
        prop.load(fis);
        String browserName= System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser");
        //String browserName = prop.getProperty("browser");
        url = prop.getProperty("url");
        if (browserName.equalsIgnoreCase("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            String headless = System.getProperty("headless") != null
                    ? System.getProperty("headless")
                    : prop.getProperty("headless");
            if (headless.equalsIgnoreCase("true")) {
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--window-size=1920,1080");
            }

            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(chromeOptions);
            landingPage = new LandingPage(driver);

        } else if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().driverVersion("144.0.3719.115").setup();
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        return driver;

    }

    public List<HashMap<String, String>> getTestData(File FilePath) throws IOException {
        ObjectMapper ob= new ObjectMapper();
        String jsonContent = FileUtils.readFileToString(FilePath, StandardCharsets.UTF_8);

        List<HashMap<String, String>> data = ob.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
        return data;

    }

    public String getScreenshot(String testName, WebDriver driver) throws Exception {
        TakesScreenshot ts= (TakesScreenshot) driver;
        File src= ts.getScreenshotAs(OutputType.FILE);
        String filepath = System.getProperty("user.dir")+"/src/reports/"+testName+".jpg";
        File saveDest= new File(filepath);
        FileUtils.copyFile(src,saveDest);

        return filepath;
    }

    @BeforeMethod
    public void launchApplication() throws IOException {
        driver = initializeDriver();
        setDriver(driver);                 // ThreadLocal set
        landingPage = new LandingPage(getDriver());
        getDriver().get(url);

    }

    @AfterMethod
    public void tearDown() {
        getDriver().quit();
    }
}

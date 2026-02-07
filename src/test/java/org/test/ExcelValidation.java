package org.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;

public class ExcelValidation {
    static public void main(String args[]) throws IOException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));

        String fruitName = "Papaya";
        int input = 900;
        String filepath="/Users/vivek_ravi/Downloads/download.xlsx";

        driver.get("https://rahulshettyacademy.com/upload-download-test/");
        driver.findElement(By.cssSelector("#downloadButton")).click();

        driver.findElement(By.id("fileinput")).sendKeys(filepath);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));

        WebElement UploadSuccess = driver.findElement(By.cssSelector(".Toastify__toast-body div:nth-child(2)"));
        wait.until(ExpectedConditions.visibilityOf(UploadSuccess));

        Assert.assertEquals(UploadSuccess.getText(), "Updated Excel Data Successfully.");

        wait.until(ExpectedConditions.invisibilityOf(UploadSuccess));
        String columnId = driver.findElement(By.xpath("//*[text()='Price']")).getAttribute("data-column-id");

        System.out.println(driver.findElement(By.xpath("//*[contains(text(),'" + fruitName + "')]/parent::div/parent::div//*[@id='cell-" + columnId + "-undefined']")).getText());
       // Assert.assertEquals(input, driver.findElement(By.xpath("//*[contains(text(),'" + fruitName + "')]/parent::div/parent::div//*[@id='cell-" + columnId + "-undefined']")).getText());


        int column=  updateColumn(filepath,"price");
        int row=  updateColumn(filepath,fruitName);

        update(row,column,input,filepath);
        driver.quit();
    }

    public static int updateColumn(String filepath,String rowName) throws IOException {
        FileInputStream fis= new FileInputStream(filepath);

        XSSFWorkbook wkbk = new XSSFWorkbook(fis);
        XSSFSheet wkst= wkbk.getSheet("Sheet1");
        Iterator<Row> rows= wkst.iterator();
        int k=0;
        while(rows.hasNext()){
            Row r= rows.next();
            Iterator<Cell> cells= r.cellIterator();
            while (cells.hasNext()){
                Cell c= cells.next();
               if(c.getCellType()==CellType.STRING && c.getStringCellValue().equalsIgnoreCase(rowName)){
                   System.out.println(k);
                   return k;
                }

            }
            k++;

        }
        return 0;
    }

    public static int updateRow(String filepath,String rowName) throws IOException {
        FileInputStream fis = new FileInputStream(filepath);

        XSSFWorkbook wkbk = new XSSFWorkbook(fis);
        XSSFSheet wkst = wkbk.getSheet("Sheet1");
        Iterator<Row> rows = wkst.iterator();
        int k = 0;
        while (rows.hasNext()) {
            Row r = rows.next();
            Iterator<Cell> cells = r.cellIterator();
            while (cells.hasNext()){
                Cell c= cells.next();
                if(c.getCellType()==CellType.STRING && c.getStringCellValue().equalsIgnoreCase(rowName)){
                    System.out.println(k);
                    return k;
                }

            }
            k++;
        }
        return 0;
    }
    public static void update(int row,int col,int num,String filepath) throws IOException {
        FileInputStream fis = new FileInputStream(filepath);
        XSSFWorkbook wkbk = new XSSFWorkbook(fis);
        XSSFSheet wkst = wkbk.getSheet("Sheet1");
        Iterator<Row> rows = wkst.iterator();
        Row r = wkst.getRow(row - 1);
        Cell c = r.getCell(col);
        c.setCellValue(num);

        FileOutputStream fos = new FileOutputStream(filepath);
        fos.close();
    }




}

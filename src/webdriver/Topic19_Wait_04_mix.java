package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.Date;

public class Topic19_Wait_04_mix {
    WebDriver driver;
    WebDriverWait explicitWait;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
    }


    @Test
    public void TC_01_Only_implicit_found() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.facebook.com");
        // khi vao tim element thi thay ngay
        driver.findElement(By.cssSelector("input#email"));
       }

    @Test
    public void TC_02_Only_implicit_not_found() {
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
        // khi vao tim element thi khong thay
        driver.findElement(By.cssSelector("input#emailsss"));
    }
    @Test
    public void TC_03_Only_explicit_found() {
        driver.get("https://www.facebook.com");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
    }

    @Test
    public void TC_04_Only_explicit_not_found() {
        driver.get("https://www.facebook.com");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#emailssss")));


    }
    @Test
    public void TC_05_Mix_implicit_explicit() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.facebook.com");
        //driver.findElement(By.cssSelector("input#email"));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        System.out.println("Start time: " + getCUrrentTime());
        try{
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#emailm")));
        }catch (Exception e){
            System.out.println("End time: " + getCUrrentTime());
            e.printStackTrace();
        }
    }
    @Test
    public void TC_06_Only_Explicit_el_not_found_Web() {
        driver.get("https://www.facebook.com");
        //driver.findElement(By.cssSelector("input#email"));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        System.out.println("Start time: " + getCUrrentTime());
        try{
            explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#emailm"))));
        }catch (Exception e){
            System.out.println("End time: " + getCUrrentTime());
            e.printStackTrace();
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
    public String getCUrrentTime(){
        Date date = new Date();
        return date.toString();
    }
}

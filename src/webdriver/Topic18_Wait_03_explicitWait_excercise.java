package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class Topic18_Wait_03_explicitWait_excercise {
    WebDriver driver;
    WebDriverWait explicitWait;
    String projectPath = System.getProperty("user.dir");
    String juneFile = "June.jpeg";
    String miaFile = "Mia.JPG";
    // String character = Platform.getCurrent().is(Platform.WINDOWS) ? "\\" : "/";      --> Way 1
    String character = File.separator;
    String juneFilePath = projectPath + character + "Upload_File" + character+ juneFile;
    String miaFilePath = projectPath + character + "Upload_File" + character + miaFile;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    @Test
    public void TC_01() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start>button")).click();
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
    }

    @Test
    public void TC_02_Ajax_Loading() {
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
        driver.findElement(By.xpath("//a[text() = '12']")).click();
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar1']>div.raDiv")));
        Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(), "Friday, January 12, 2024");

    }
    @Test
    public void TC_03_Upload_file() {
        driver.get("https://gofile.io/welcome");
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn-secondary"))).click();
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#filesUpload button.filesUploadButton")));

        driver.findElement(By.cssSelector("input#filesUploadInput")).sendKeys(juneFilePath + "\n" + miaFilePath );

        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.spinner-border")))));
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress")))));

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.mainUploadSuccessLink a.ajaxLink"))).click();

        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(""))).isDisplayed());


    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic15_Upload_File {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String juneFile = "June.jpeg";
    String miaFile = "Mia.JPG";
   // String character = Platform.getCurrent().is(Platform.WINDOWS) ? "\\" : "/";      --> Way 1
    String character = File.separator;
    String juneFilePath = projectPath + character + "Upload_File" + character+ juneFile;
    String miaFilePath = projectPath + character + "Upload_File" + character + miaFile;

    WebDriverWait explicitWait;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Upload_Single_File() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        By uploadBy = By.cssSelector("input[name='files[]']");
        driver.findElement(uploadBy).sendKeys(juneFilePath);

        sleepInSeconds(3);

        driver.findElement(uploadBy).sendKeys(miaFilePath);
        sleepInSeconds(3);

        // Verify file loaded successfully
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = '" + juneFile + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = '" + miaFile + "']")).isDisplayed());

        List<WebElement> startBtns = driver.findElements(By.cssSelector("td button.start"));

        for(WebElement btn : startBtns){
            btn.click();
            sleepInSeconds(2);
        }
        // Verify that file uploaded successfully
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class = 'name']/ a[@title = '"+juneFile+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class = 'name']/ a[@title = '"+miaFile+"']")).isDisplayed());


    }

    @Test
    public void TC_02_Upload_Multiple_Files() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        By uploadBy = By.cssSelector("input[name='files[]']");
        driver.findElement(uploadBy).sendKeys(juneFilePath + "\n" + miaFilePath );

        sleepInSeconds(3);

        // Verify file loaded successfully
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = '"+ juneFile + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class = 'name' and text() = '"+ miaFile + "']")).isDisplayed());


        List<WebElement> startBtns = driver.findElements(By.cssSelector("td button.start"));

        for(WebElement btn : startBtns){
            btn.click();
            sleepInSeconds(2);
        }

        // Verify that file uploaded successfully
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class = 'name']/ a[@title = '"+juneFile+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class = 'name']/ a[@title = '"+miaFile+"']")).isDisplayed());


    }

    @Test
    public void TC_03_Window_TechPanDa_Website() {

    }

    public void sleepInSeconds(long time) {
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

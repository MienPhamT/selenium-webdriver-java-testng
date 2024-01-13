package webdriver;

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

import java.time.Duration;

public class Topic16_Wait_demo_Status_Of_El {
    WebDriver driver;
    WebDriverWait explicitWait;
    By reConfirmEmailBox = By.cssSelector("input[name = 'reg_email_confirmation__']");



    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.get("https://facebook.com");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_El_Visible() {
        // Visible: Co tren UI va co trong DOM
        driver.findElement(By.cssSelector("a[data-testid= 'open-registration-form-button']")).click();
        sleepInSeconds(3);
        driver.findElement(By.cssSelector("input[name = 'reg_email__']")).sendKeys("mia.pham@mana.com");
        sleepInSeconds(2);
        // Sau khi input email thi the input email confirm xuat hien tren UI va Dom -> Dung wait visibility
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(reConfirmEmailBox));
        Assert.assertTrue(driver.findElement(reConfirmEmailBox).isDisplayed());
    }

    @Test
    public void TC_02_El_Invisible_In_DOM() {
        // Invisible: Khong tren UI nhung van co trong DOM
        driver.findElement(By.cssSelector("input[name = 'reg_email__']")).clear();
        sleepInSeconds(2);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(reConfirmEmailBox));
        Assert.assertFalse(driver.findElement(reConfirmEmailBox).isDisplayed());


    }
    @Test
    public void TC_02_El_Invisible_Not_In_DOM() {
        // Invisible: Khong tren UI va cung khong co trong DOM
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
        sleepInSeconds(2);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(reConfirmEmailBox));
        Assert.assertEquals(driver.findElements(reConfirmEmailBox).size(),0);
    }

    @Test
    public void TC_03_El_Presence() {
        // Presence: Chỉ cần có trong DOM, trên UI có hay không không quan trọng
        driver.findElement(By.cssSelector("a[data-testid= 'open-registration-form-button']")).click();
        sleepInSeconds(3);
        driver.findElement(By.cssSelector("input[name = 'reg_email__']")).clear();
        sleepInSeconds(2);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(reConfirmEmailBox));
        Assert.assertTrue(driver.findElement(reConfirmEmailBox).isDisplayed());
    }
    @Test
    public void TC_04_El_Staleness() {
        // Staleness: không có trong DOM
        // Mở popup lên để cho el xuất hiện trong DOM
        driver.findElement(By.cssSelector("a[data-testid= 'open-registration-form-button']")).click();
        sleepInSeconds(3);
        WebElement confirmEmail = driver.findElement(reConfirmEmailBox);

        // Close popup để el bien mất trong DOM
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
        sleepInSeconds(2);
        explicitWait.until(ExpectedConditions.stalenessOf(confirmEmail));
        Assert.assertEquals(driver.findElements(reConfirmEmailBox).size(),0);
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

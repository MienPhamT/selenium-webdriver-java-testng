package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Topic10_Popup_Iframe_window {
    WebDriver driver;
    Actions action;
    WebDriverWait explicitWait;
    JavascriptExecutor javascriptExecutor ;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        javascriptExecutor = (JavascriptExecutor) driver;
        action = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Fixed_popup_InDOM() {
        driver.get("https://ngoaingu24h.vn/");
        driver.findElement(By.cssSelector("button.login_")).click();
        sleepInSeconds(2);

        By loginPopup = By.cssSelector("div[id ='modal-login-v1'][style] > div");
        driver.findElement(By.cssSelector("div[id ='modal-login-v1'][style] input#account-input")).sendKeys("Miamia");
        driver.findElement(By.cssSelector("div[id ='modal-login-v1'][style] input#password-input")).sendKeys("Miamia123");
        driver.findElement(By.cssSelector("div[id ='modal-login-v1'][style] button.btn-login-v1")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("div[id ='modal-login-v1'][style] div.error-login-panel")).getText()
        , "Mật khẩu sai!");

        driver.findElement(By.cssSelector("div[id ='modal-login-v1'][style] button.close")).click();
        Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());

    }
    @Test
    public void TC_02_Fixed_Popup_Not_InDOM() {
        driver.get("https://tiki.vn/");
        driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();
        sleepInSeconds(2);

        Assert.assertTrue(driver.findElement(By.cssSelector("div.ReactModal__Content")).isDisplayed());

        driver.findElement(By.cssSelector("p.login-with-email")).click();

        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        // Verify error msg

        //Đóng popup: Thẻ HTML không còn trong DOM nữa nên khong dùng được isDisplayed, dùng findElements instead
        driver.findElement(By.cssSelector("img.close-img")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertEquals(driver.findElements(By.cssSelector("div.ReactModal__Content")).size(),0);

        sleepInSeconds(2);

    }
   @Test
    public void TC_03_Promt_alert(){
        driver.get("https://www.facebook.com/");
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(2);

        Assert.assertTrue(driver.findElement(By.xpath("//div[text()= 'Sign Up']/parent::div/parent::div")).isDisplayed());

        driver.findElement(By.xpath("//div[text()= 'Sign Up']/parent::div/parent::div/img")).click();
        sleepInSeconds(2);

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
       Assert.assertEquals(driver.findElements(By.xpath("//div[text()= 'Sign Up']/parent::div/parent::div")).size(),0);

    }
    @Test
    public void TC_04_Authentication_Alert_ByPassInUrl(){
    }
    @Test
    public void TC_05_Double_Click(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
          }
    @Test
    public void TC_06_DragDropHTML4(){

    }
    @Test
    public void TC_07_DragDropHTML5(){

    }
    @Test
    public void sleepInSeconds(long time){
        try {
            Thread.sleep(time*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

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
    public void TC_03_FixedPopup_NotInDom2(){
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
    public void TC_04_PopupRandom_NotInDom(){
        driver.get("https://www.javacodegeeks.com/");

        By newLetterPopup= By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none'])");
        // Neu popup hien thi -> Close popup
        // Neu popup k hien thi -> Qua step tiep theo: Search du lieu
        if(driver.findElements(newLetterPopup).size() > 0 && driver.findElements(newLetterPopup).get(0).isDisplayed()){
            // >0 nghia la da duoc render nhung chua biet co hien thi hay khong
            driver.findElement(By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none']) div.lepopup-element-html-content > a")).click();
            sleepInSeconds(3);
            System.out.println("Popup hien thi");
        }
        else {
            System.out.println("Popup khong hien thi");
        }
        // Search du lieu
        driver.findElement(By.cssSelector("input#search-input")).sendKeys("Agile Testing Explained");
        driver.findElement(By.cssSelector("button#search-submit")).click();
        sleepInSeconds(3);
        String firstItemText = driver.findElement(By.xpath("//ul[@id='posts-container']/li[1]//h2/a")).getText();
        // Toi uu hon thi can viet ham kiem tra xem co contains hay k, chu k phair check equal voi fix text
        Assert.assertEquals(firstItemText, "Agile Testing Explained");

    }
    @Test
    public void TC_05_RanDomPopup_In_Dom(){
        driver.get("https://vnk.edu.vn/");
        sleepInSeconds(30);
        By marketingPopup = By.cssSelector("div.tve-leads-conversion-object");
        if (driver.findElement(marketingPopup).isDisplayed()){
            driver.findElement(By.cssSelector("div.tve_ea_thrive_leads_form_close")).click();
            sleepInSeconds(3);
        }
        else System.out.println("Popup khong hien thi");

    }
    @Test
    public void TC_06_RanDomPopup_Not_In_DOM_2(){
        driver.get("https://dehieu.vn/");
        By marketingPopup = By.cssSelector("div.popup-content");
        if(driver.findElements(marketingPopup).size()>0 && driver.findElements(marketingPopup).get(0).isDisplayed()){
            System.out.println("Popup hien thi");
            sleepInSeconds(5);
            int heightBrowser = driver.manage().window().getSize().getHeight();
            if (heightBrowser<1920){
                ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("button#close-popup")));
            }
            else {
                driver.findElement(By.cssSelector("button#close-popup")).click();
                System.out.println("man hinh to");
            }
            sleepInSeconds(3);
        }
        else System.out.println("Popup khong hien thi");
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

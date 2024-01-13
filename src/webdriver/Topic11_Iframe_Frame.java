package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Topic11_Iframe_Frame {
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
    public void TC_01_iframe1() {
        // Trang A: domain: formsite.com
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");
//        // Include iframe: Trang B:
//        // navigate from A to B
//        driver.switchTo().frame("frame-one85593366");
//        driver.findElement(By.cssSelector("")).click();
//        // B include iframe C --> Navigate from B to C
//        driver.switchTo().frame("frame-23232");
//        // Navigate from C back to B
//        driver.switchTo().parentFrame();
//        // User is on B
//        // back from B to A
//        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector("div#imageTemplateContainer>img")).click();
        sleepInSeconds(3);
        WebElement formIFrame = driver.findElement(By.cssSelector("div#formTemplateContainer>iframe"));
        Assert.assertTrue(formIFrame.isDisplayed());

        // Switch to iframe
        //driver.switchTo().frame(0); --> switch by index -> risk
        //driver.switchTo().frame("frame-one85593366");  --> switch by name or id --> Risk
        driver.switchTo().frame(formIFrame); // switch by element : stable
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-2"))).selectByVisibleText("Sophomore");
        sleepInSeconds(3);

        driver.switchTo().defaultContent();
        driver.findElement((By.cssSelector("nav.header--desktop-floater a.menu-item-login"))).click();

    }
    @Test
    public void TC_02_Frame2() {
        driver.get("https://tiki.vn/");

    }
   @Test
    public void TC_03_FixedPopup_NotInDom2(){
        driver.get("https://skills.kynaenglish.vn/");
        

    }
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

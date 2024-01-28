package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;
import java.util.function.Function;

public class Topic20_Wait_05_Fluent_Wait {
    WebDriver driver;
    WebDriverWait explicitWait;
    FluentWait<WebDriver> fluentDriver;
    FluentWait<WebElement> fluentElement;
    FluentWait<String> fluentString;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
//       // Time polling default = 500millis
//        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        // Custom Time Polling
//        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(200));
        fluentDriver = new FluentWait<WebDriver>(driver);

    }


    @Test
    public void TC_01_setting_demo_fluent() {
//        fluentDriver = new FluentWait<WebDriver>(driver);
//        fluentElement = new FluentWait<WebElement>(driver.findElement(By.cssSelector("")));
//        fluentString = new FluentWait<String>("");
//
//        fluentDriver.withTimeout(Duration.ofSeconds(10))
//                .pollingEvery(Duration.ofMillis(400))
//                .ignoring(NoSuchElementException.class)
//                .ignoring(TimeoutException.class)
//                .until(new Function<WebDriver, String>() {
//                    @Override
//                    public String apply(WebDriver webDriver){
//                        return "String";
//                    }
//                });
       }

    @Test
    public void TC_02() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start>button")).click();
        fluentDriver.withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(400))
                .ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class)
                .until(new Function<WebDriver, Boolean>() {
                    @Override
                    public Boolean apply(WebDriver webDriver){
                        return driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed();
                    }
                });
    }
    @Test (groups = "TC")
    public void TC_03() {
        driver.get("https://automationfc.github.io/fluent-wait/");
        WebElement countdownTime = driver.findElement(By.cssSelector("div#javascript_countdown_time"));
        fluentElement = new FluentWait<WebElement>(countdownTime);
        fluentElement.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
        fluentElement.until(new Function<WebElement, Boolean>() {
            @Override
            public Boolean apply (WebElement webel){
                String text = webel.getText();
                System.out.println(text);
                return text.endsWith("00");
            }
        });


    }

    public WebElement waitAndFindEl(By locator){
        FluentWait fluentDriverr = new FluentWait<WebDriver>(driver);
        fluentDriverr.withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(400))
                .ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class);
           return fluentDriver.until(new Function<WebDriver, WebElement>() {
                    @Override
                    public WebElement apply(WebDriver webDriver){
                        return webDriver.findElement(locator);
                    }
                });
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

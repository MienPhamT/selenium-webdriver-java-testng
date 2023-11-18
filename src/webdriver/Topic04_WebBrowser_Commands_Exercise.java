package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic04_WebBrowser_Commands_Exercise {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
    String accessPageUrl;
    @BeforeClass
    public void beforeClass() {
//        if (osName.contains("Windows")) {
//            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//        } else {
//            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
//        }
        //driver = new ChromeDriver();
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_Page_Url(){
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSecond(3);
        driver.getCurrentUrl();
        Assert.assertEquals( driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");

        //driver.findElement(By.ByCssSelector)
    }

    @Test
    public void TC_02_Page_Title() {
        Assert.assertTrue(driver.findElement(By.cssSelector("img.fb_logo")).isDisplayed());
    }

    @Test
    public void TC_03_Page_Navigation() {
        Assert.assertTrue(driver.findElement(By.xpath("//form[@data-testid='royal_login_form']")).isDisplayed());
    }
    public void TC_04_Page_Source(){

    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
    public void sleepInSecond(long timeInSecond){
        try {
            Thread.sleep(timeInSecond*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

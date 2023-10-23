package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic07_Handle_Button_Excercise {
    WebDriver driver;
    // Wait Tường minh: tráng thái cụ thể cho element: Visible, Invisible, presence, clickable, number...
    WebDriverWait explicitWait;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }

        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        // driver = new ChromeDriver();
        // Wait Ngầm định : Không rõ ràng cho 1 tráng thái cũ thể nào của element hết
        // Cho việc timf element - findElement
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
       // driver.get("https://www.facebook.com/");
    }

    @Test

    public void TC_01_egov_register_button() {
        driver.get("https://egov.danang.gov.vn/reg");
        WebElement chinhSachCHX = driver.findElement(By.cssSelector("input#chinhSach"));
        WebElement registerBtn = driver.findElement(By.cssSelector("input#button2"));
        String registerBtnBackgroundRGB = registerBtn.getCssValue("background-color");
        Color registerBtnBackgroundColor = Color.fromString(registerBtnBackgroundRGB);
        String registerBtnBackgroundHexa = registerBtnBackgroundColor.asHex();
        if(!chinhSachCHX.isSelected()){
           Assert.assertEquals(registerBtnBackgroundHexa.toUpperCase(), "#A0A0A0");
           chinhSachCHX.click();
        }
        Assert.assertEquals(Color.fromString(registerBtn.getCssValue("background-color")).asHex().toLowerCase(), "#ef5a00");
    }
    @Test
    public void TC_02_Login_Fahasa_button() {
        driver.get("https://www.fahasa.com/customer/account/create");
        sleepInSeconds(2);
        driver.findElement(By.cssSelector("li.popup-login-tab-login ")).click();
        sleepInSeconds(2);
        WebElement sdt =  driver.findElement(By.cssSelector("input#login_username")); sdt.clear();
        WebElement password =  driver.findElement(By.cssSelector("input#login_password")); password.clear();
        WebElement btn_Login = driver.findElement(By.cssSelector("button.fhs-btn-login"));

        Assert.assertFalse(btn_Login.isEnabled());
        Assert.assertEquals(Color.fromString(btn_Login.getCssValue("background-color")).asHex().toLowerCase(), "#000000");

        sdt.sendKeys("miooa@gmail.com");
        password.sendKeys("666hhh@");
        Assert.assertTrue(btn_Login.isEnabled());
        Assert.assertEquals(Color.fromString(btn_Login.getCssValue("background-color")).asHex().toUpperCase(), "#C92127");



    }

    public void sleepInSeconds(long time){
        try {
            Thread.sleep(time*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void selectItemInDropdown(String parentCSS, String childItem, String itemExpected){
        driver.findElement(By.xpath(parentCSS)).click();

        // Vua wait + vua tim element
        List<WebElement> listNumberButton = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childItem)));

        for (WebElement item : listNumberButton){
            if (item.getText().equals(itemExpected)){
                item.click();
                break;
            }
        }
    }
    public void selectItemEditableDropdown(String parentCSS, String childItem, String itemExpected){
        driver.findElement(By.xpath(parentCSS)).clear();
        driver.findElement(By.xpath(parentCSS)).sendKeys(itemExpected);

        // Vua wait + vua tim element
        List<WebElement> listNumberButton = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childItem)));
        for (WebElement item : listNumberButton){
            if (item.getText().equals(itemExpected)){
                item.click();
                break;
            }
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

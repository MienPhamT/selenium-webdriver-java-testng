package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_Textbox_Dropdown_Exercise {
    WebDriver driver;
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
        // driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
       // driver.get("https://www.facebook.com/");
    }

    @Test
    public void Login_01() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//span[@class= 'label' and text()= 'Account']")).click();
        driver.findElement(By.xpath("//div[@id= 'header-account']//a[@title = 'My Account']")).click();
        driver.findElement(By.xpath("//span[text()= 'Create an Account']")).click();

        // Fill all fields with valid value
        WebElement we_first_name = driver.findElement(By.id("firstname"));
        we_first_name.sendKeys("Mia");

        WebElement we_middle_name = driver.findElement(By.id("middlename"));
        we_middle_name.sendKeys("Mia");

        WebElement we_last_name = driver.findElement(By.id("lastname"));
        we_last_name.sendKeys("Pham");

        WebElement we_email = driver.findElement(By.id("email_address"));
        we_email.sendKeys("mia11@mana.com");

        driver.findElement(By.id("password")).sendKeys("mia123@");
        driver.findElement(By.id("confirmation")).sendKeys("mia123@");
        driver.findElement(By.id("is_subscribed")).click();
        driver.findElement(By.xpath("//button[@title = 'Register']")).click();

        //My Dashboard screen, verify
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),"Thank you for registering with Main Website Store.");

        //Assert.assertEquals(driver.findElement(By.xpath("//div[@class ='col-1']//div[@class = 'box-content']/p")).getText(), "Mia Mia Pham" + "mia7@mana.com");
        String acc_info = driver.findElement(By.xpath("//div[@class ='col-1']//div[@class = 'box-content']/p")).getText();
        System.out.println(acc_info);
        String[] acc_info_list = acc_info.split("\n");
        String name = acc_info_list[0];
        String email = acc_info_list[1];
        System.out.println(name);
        Assert.assertEquals(name, "Mia Mia Pham");
        Assert.assertEquals(email, "mia11@mana.com");

        //Logout
        driver.findElement(By.xpath("//span[@class ='label' and text()='Account']")).click();
        driver.findElement(By.xpath("//div[@id='header-account']//li[@class=' last']")).click();

        driver.findElement(By.xpath("//span[@class ='label' and text()='Account']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='header-account']//li[@class=' last']/a[@title ='Log In']")).isDisplayed(), "true");
    }

    @Test
    public void Register_02_Invalid_Email_Address() {
        }

    @Test
    public void Register_03_Invalid_Confirm_Email() {
        }

    @Test
    public void Register_04_Invalid_Password() {
    }
    @Test
    public void Register_05_Invalid_Confirm_Password() {
    }
    @Test
    public void Register_06_Invalid_Phone_Number() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

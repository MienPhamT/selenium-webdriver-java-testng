package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic22_TestNG_03_TestNG_Loop {
    WebDriver driver;
    WebDriverWait explicitWait;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @Test(invocationCount = 5, timeOut = 10000)
    public void TC_01_Register_Loop ()  {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//span[@class= 'label' and text()= 'Account']")).click();
        driver.findElement(By.xpath("//div[@id= 'header-account']//a[@title = 'My Account']")).click();
        driver.findElement(By.xpath("//span[text()= 'Create an Account']")).click();

        // Fill all fields with valid value
        String first_name = "Mia", middle_name = "Mia", last_name = "Pham", email = getEmailRandom(),  password = "123456";
        String full_name = first_name + " " + middle_name +" " + last_name;
        WebElement we_first_name = driver.findElement(By.id("firstname"));
        we_first_name.sendKeys(first_name);

        WebElement we_middle_name = driver.findElement(By.id("middlename"));
        we_middle_name.sendKeys(middle_name);

        WebElement we_last_name = driver.findElement(By.id("lastname"));
        we_last_name.sendKeys(last_name);

        WebElement we_email = driver.findElement(By.id("email_address"));
        we_email.sendKeys(email);

        WebElement we_password = driver.findElement(By.id("password"));
        we_password.sendKeys(password);

        WebElement we_confirm_password = driver.findElement(By.id("confirmation"));
        we_confirm_password.sendKeys(password);
        sleepInSeconds(2);

        driver.findElement(By.id("is_subscribed")).click();
        driver.findElement(By.xpath("//button[@title = 'Register']")).click();

        //My Dashboard screen, verify
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),"Thank you for registering with Main Website Store.");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class = 'welcome-msg']//strong")).getText(), "Hello, "+ full_name +"!" );

        String acc_info = driver.findElement(By.xpath("//div[@class ='col-1']//div[@class = 'box-content']/p")).getText();
        System.out.println(acc_info);
        String[] acc_info_list = acc_info.split("\n");
        String name = acc_info_list[0];
        String email1 = acc_info_list[1];
        System.out.println(name);
        Assert.assertEquals(name, full_name);
        Assert.assertEquals(email1, email);

        //Logout
        driver.findElement(By.xpath("//span[@class ='label' and text()='Account']")).click();
        driver.findElement(By.xpath("//div[@id='header-account']//li[@class=' last']")).click();
        sleepInSeconds(2);

    }
    public String getEmailRandom(){
        Random rand = new Random();
        return "mia" + rand.nextInt(99999) + "@gmail.com";
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

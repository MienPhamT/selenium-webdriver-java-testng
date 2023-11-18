package javaTester;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_05_Assert {
    WebDriver driver;
    @Test
    public void verifyTestNG(){
        // Trong java cos nhieu thu vien de verify du lieu
        // Testing framework (Unit/ Intergration/ UI Automation test)
        // JUnit 4/ TestNG/ Junit 5/ Hamcrest/ AssertJ/...
        driver = new FirefoxDriver();
        driver.get("https://www.facebook.com/");

        // MOng muon dieu kien tra ve dung
        Assert.assertTrue(driver.getPageSource().contains("Facebook helps you connect and share with the people in your life."));
        // MOng muon dieu kien tra ve sai
        Assert.assertFalse(driver.getPageSource().contains("Create a new accoount"));

        // Cac ham tra ve du lieu dang boolean
        driver.findElement(By.id("")).isDisplayed();
        driver.findElement(By.id("")).isEnabled();
        driver.findElement(By.id("")).isSelected();
        new Select(driver.findElement(By.id(""))).isMultiple();

       // Assert.assertEquals("");

        // Ham thuong dung cho UnitTest
        Object name = null;
        Assert.assertNull(name);
        Assert.assertNotNull(name);


    }
}

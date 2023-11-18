package javaTester;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_09_Get_Driver_Info {
    WebDriver driver;
    @Test
    public void testDriverInfo(){
        driver = new FirefoxDriver();
        System.out.println(driver.toString());
        //OS nào, browser nào, Browser class, ID của driver...
        //result: FirefoxDriver: firefox on mac (fbd2b4e1-083d-4a48-b864-d2a0cf21f7b5)
        if (driver.toString().contains("firefox")){
            // do sth...
        }
        driver.quit();
    }
}

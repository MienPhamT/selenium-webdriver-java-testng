package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;
import java.util.List;

public class Topic17_Wait_02_findElement_implicitWait {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://facebook.com");
    }

    @Test
    public void TC_01_find_el() {
       // Case 1: El duoc tim thay chi co 1 : Khong can cho het timeout, tim thay se tra ve Web el va run step tiep theo
        System.out.println("Start step: " + getDateTimeNow());
        driver.findElement(By.cssSelector("input#email"));
        System.out.println("Start step: " + getDateTimeNow());

        /*  Case 2: El duoc tim thay nhung nhieu hon 1
            -> Se khong cho het timeout, tim thay se return first element, khong tim het
         */
        System.out.println("Start step: " + getDateTimeNow());
        driver.findElement(By.cssSelector("input[type='text'],[type='password']")).sendKeys("mia@mail.com");
        System.out.println("end step: " + getDateTimeNow());

        /* Case 3: El khong duoc tim thay trong khoang thoi gian duoc set
        --> Cho het timeout duoc set (10s), cứ mỗi nửa giây sẽ tìm lại 1 lần
        --> cuối cùng trả ra exception: NoSuchElemnent, đánh failed testcase
         */
        driver.findElement(By.cssSelector("input#mia"));

    }

    @Test
    public void TC_01_find_elements() {
        List<WebElement> listEl;
        /* Case 1: El duoc tim thay chi co 1 :
        --> Khong can cho het timeout, tim thay se tra ve list Web el chua 1 el
        */

        listEl = driver.findElements(By.cssSelector("input#email"));
        System.out.println(listEl.size());
        /*  Case 2: El duoc tim thay nhung nhieu hon 1
            -> Se khong cho het timeout, tim thay se return list El
         */
        listEl = driver.findElements(By.cssSelector("input[type='text'],[type='password']"));
        System.out.println(listEl.size());
        /* Case 3: El khong duoc tim thay trong khoang thoi gian duoc set
        --> Cho het timeout duoc set (10s), cứ mỗi nửa giây sẽ tìm lại 1 lần
        --> cuối cùng trả ra list el voi size() = 0
         */
         listEl = driver.findElements(By.cssSelector("input#mia"));
        System.out.println(listEl.size());

    }

    public void sleepInSeconds(long time) {
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public String getDateTimeNow(){
        Date date = new Date();
        return date.toString();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

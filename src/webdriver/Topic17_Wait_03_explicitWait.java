package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;
import java.util.List;

public class Topic17_Wait_03_explicitWait {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    @Test
    public void TC_01_find_el() {
        // Chờ cho 1 alert presence trong HTML/ DOM trước khi thao tác lên
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());

        // Chờ cho el không còn trong DOM
        explicitWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector(""))));
        // Chờ cho El xuất hiện trong DOM
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("")));
        // Chờ cho 1 list El có trong DOM
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("")));

        // Chờ cho 1-n El được hiển thị trên UI
        explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.cssSelector(""))));
        explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(""))));

        // Chờ cho 1 El có thể click
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("")));
        // Chờ cho page hiện tại có title như mong đợi
        explicitWait.until(ExpectedConditions.titleIs(""));
        // Kết hợp các điều kiện
        explicitWait.until(ExpectedConditions.and(
                ExpectedConditions.alertIsPresent(),
                ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("")))));
        // Chờ cho El có attribute chứa giá trị như mong đợi (giá trị tương đối)
        explicitWait.until(ExpectedConditions.attributeContains(By.cssSelector(""),"placeholder", "input here"));

        // Chờ cho El có attribute chứa giá trị như mong đợi (giá trị tương đối)
        explicitWait.until(ExpectedConditions.attributeToBe(By.cssSelector(""),"placeholder", "input here"));
        explicitWait.until(ExpectedConditions.attributeToBeNotEmpty(driver.findElement(By.cssSelector("")),"placeholder"));

        explicitWait.until(ExpectedConditions.domAttributeToBe(driver.findElement(By.cssSelector("")),"placeholder", "title"));
        explicitWait.until(ExpectedConditions.domPropertyToBe(driver.findElement(By.cssSelector("")),"placeholder", "title"));

        // Chờ cho 1 element được Select thành công
        explicitWait.until(ExpectedConditions.elementToBeSelected(By.cssSelector("")));

        // Chờ cho 1 el selected rồi
        explicitWait.until(ExpectedConditions.elementSelectionStateToBe(By.cssSelector(""), true));

        // Chờ cho 1 el de-selected rồi
        explicitWait.until(ExpectedConditions.elementSelectionStateToBe(By.cssSelector(""), false));

        // Chờ cho 1 frame được available và switch qua: id/ name/ index/ WebElement/ By
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(""));

        // Chờ cho 1 el biến mất khỏi UI
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("")));
        // Chờ cho đoạn code JS cần trả về giá trị
        explicitWait.until(ExpectedConditions.jsReturnsValue(""));
        // Chờ cho đoạn code JS được thuwjc thi không ném ra exception nào cả ( nếu k có ném ra -> trả ra true, nếu throw ra lỗi -> Trả về false)
        explicitWait.until(ExpectedConditions.javaScriptThrowsNoExceptions(""));
        // Chờ số lượng el bằng 1 con số nhất định
        explicitWait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(""),6 ));

        explicitWait.until(ExpectedConditions.numberOfWindowsToBe(3));

    }

    @Test
    public void TC_01_find_elements() {
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

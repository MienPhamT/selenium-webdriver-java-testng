package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic12_Window_Tab {
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
    public void TC_01_window_selelium_webdriver() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        // Get id of current tab
        String parentID = driver.getWindowHandle();
        driver.findElement(By.xpath("//a[text() = 'GOOGLE']")).click();
        sleepInSeconds(3);
        // Sau khi nhan vao gg -> Mo tab moi cho google.com -> co nhieu hon 1 tab -> getwindow handle se tra ra list ID cua cac Tab
        // Switch to Google tab
        switchTaborWindowByID(parentID);
        driver.findElement(By.cssSelector("textarea[name='q']")).sendKeys("mia");
        // Switch back to parent tab
        String idGoogle = driver.getWindowHandle();
        switchTaborWindowByID(idGoogle);
        // Dang co 2 tab:parent + google, click them tab Facebook -> co 3 tabs
        driver.findElement(By.xpath("//a[text() = 'FACEBOOK']")).click();
        sleepInSeconds(6);
        switchToWindoworTabbyTitle("Facebook – log in or sign up");
        System.out.println(driver.getTitle());
        closeAllTaborWindowExceptForParent(parentID);
    }
    public void switchTaborWindowByID(String parentID){
        Set<String> allIDs = driver.getWindowHandles();
        for (String id : allIDs){
            if(!id.equals(parentID)) {
                driver.switchTo().window(id);
                break;
            }
        }
    }
    public void switchToWindoworTabbyTitle(String expectedTitle){
        Set<String> allIDs = driver.getWindowHandles();
        for (String id : allIDs){
            driver.switchTo().window(id);
            String currentTitle = driver.getTitle();
            if(expectedTitle.equals(currentTitle)){
                break;
            }
        }
    }

    @Test
    public void TC_02_Window_KynaWebsite() {
        driver.get("https://skills.kynaenglish.vn/");
        driver.findElement(By.cssSelector("div.hotline img[alt='facebook']")).click();
        sleepInSeconds(5);

        switchToWindoworTabbyTitle("Kyna.vn | Ho Chi Minh City | Facebook");
        System.out.println(driver.getTitle());
        switchToWindoworTabbyTitle("Kyna.vn - Học online cùng chuyên gia");
        System.out.println(driver.getTitle());
    }
    public void closeAllTaborWindowExceptForParent(String parentID){
        Set<String> allIDs = driver.getWindowHandles();
        for (String id : allIDs) {
            if (!parentID.equals(id)) {
                driver.switchTo().window(id);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }
   @Test
    public void TC_03_Window_TechPanDa_Website(){
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//a[text()= 'Mobile']"));
        sleepInSeconds(3);

        driver.findElement(By.xpath("//a[title()= 'IPhone'/parent::h2/following-sibling"));
       sleepInSeconds(3);


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

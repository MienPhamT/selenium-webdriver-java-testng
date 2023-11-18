package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v115.network.Network;
import org.openqa.selenium.devtools.v115.network.model.Headers;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class Topic09_User_interactions {
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
    public void TC_01_Accept_Alert() {

    }
    @Test
    public void TC_02_Confirm_alert() {
    }
   @Test
    public void TC_03_Promt_alert(){
    }
    @Test
    public void TC_04_Authentication_Alert_ByPassInUrl(){
    }
    @Test
    public void TC_05_Double_Click(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement doubleClickButton = driver.findElement(By.xpath("//button[text() = 'Double click me']"));
        // Cần scroll tới element sau đó mới double click - chỉ cần lamf bước này cho Firefox
       if (driver.toString().contains("firefox")){
           // scrollIntoView(true):  kéo mép trên của element lên phía trên cùng của view port
           // scrollIntoView(false):  kéo mép dưới của element xuống phía dưới cùng của view port
           javascriptExecutor.executeScript("arguments[0].scrollIntoView(false);", doubleClickButton);
           sleepInSeconds(3);
       }
        action.doubleClick(doubleClickButton).perform();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(),"Hello Automation Guys!" );
    }
    @Test
    public void TC_06_DragDropHTML4(){
        driver.get("https://automationfc.github.io/kendo-drag-drop/");
        WebElement smallCircle = driver.findElement(By.cssSelector("div#draggable"));
        WebElement bigCircle = driver.findElement(By.cssSelector("div#droptarget"));

        action.dragAndDrop(smallCircle,bigCircle).perform();
        sleepInSeconds(3);
        Assert.assertEquals(bigCircle.getText(), "You did great! ");

    }
 //   @Test
//    public void TC_07_DragDropHTML5(){
//        driver.get("https://automationfc.github.io/drag-drop-html5/");
//        String columnA = "div#column-a";
//        String columnB = "div#column-b";
//
//
//        String projectPath = System.getProperty("user.dir");
//        String dragAndDropFilePath = projectPath + "/DragandDropHTML5/draganddrop_helper.js";
//
//        String jsContentFile = getContentFile(dragAndDropFilePath);
//        jsContentFile = jsContentFile + "$('" + columnA + "').simulateDragDrop({ dropTarget: '" + columnB + "'})'";
//        javascriptExecutor.executeScript(jsContentFile);
//    }
//    public String getContentFile(String filePath){
//        public String getContentFile(String filePath) throws IOException {
//            Charset cs = Charset.forName("UTF-8");
//            FileInputStream stream = new FileInputStream(filePath);
//            try {
//                Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
//                StringBuilder builder = new StringBuilder();
//                char[] buffer = new char[8192];
//                int read;
//                while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
//                    builder.append(buffer, 0, read);
//                }
//                return builder.toString();
//            } finally {
//                stream.close();
//            }
//        }
//    }
    @Test
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

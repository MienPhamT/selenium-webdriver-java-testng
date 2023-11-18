package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v115.network.Network;
import org.openqa.selenium.devtools.v115.network.model.Headers;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Topic08_Handle_Alert {
    WebDriver driver;
    // Wait Tường minh: tráng thái cụ thể cho element: Visible, Invisible, presence, clickable, number...
    WebDriverWait explicitWait;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
    By resultText = By.id("result");
    @BeforeClass
    public void beforeClass() {
//        if (osName.contains("Windows")) {
//            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//        } else {
//            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
//        }
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Accept_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()= 'Click for JS Alert']")).click();
        // Chờ cho alert present (Có trong HTML, k cần trên UI), nếu present thì sẽ tự switch vào. nếu hết thời gian chò mà k xuất hiện thì sẽ fail.
        // thời gian chờ đã được set trong hàm before class
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        // Có thể k dùng wait mà dùng thư viện alert để switch qua, Thu vien alert khong support authentication alert
        // Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "I am a JS Alert");
        alert.accept(); // accept xong se close alert
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(resultText).getText(), "You clicked an alert successfully");

    }
    @Test
    public void TC_02_Confirm_alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()= 'Click for JS Confirm']")).click();
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "I am a JS Confirm");
        alert.dismiss();
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(resultText).getText(), "You clicked: Cancel");
    }
   @Test
    public void TC_03_Promt_alert(){
       driver.get("https://automationfc.github.io/basic-form/index.html");

       driver.findElement(By.xpath("//button[text()= 'Click for JS Promt']")).click();
       Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
       Assert.assertEquals(alert.getText(), "I am a JS promt");
       String text = "Mia mia";
       alert.sendKeys(text);
       alert.accept();
       sleepInSeconds(2);
       Assert.assertEquals(driver.findElement(resultText).getText(), "You entered: " + text);
   }
    @Test
    public void TC_04_Authentication_Alert_ByPassInUrl(){
        // Cach 1: Truyen thang user name, password trong domain
        String username = "admin";
        String password = "admin";
//        driver.get("http://" + username +":" + password +"@the-internet.herokuapp.com/basic_auth");
//        Assert.assertTrue(driver.findElement(By.xpath("")).isDisplayed());
        // Cach 2: Chi chay tren Windows, k dung tren mac, linux :> (dung thu vien ben ngoai: AutoIT): for each browser, we must use saparate script, refer to video of topic 29

        driver.get("http://the-internet.herokuapp.com/");
        String authenLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");

        String[] authenArray = authenLink.split("//");
        authenLink = authenArray[0]+ "//" + username + ":"+ password +"@"+ authenArray[1];
        System.out.println(authenLink);
        driver.get(authenLink);
        sleepInSeconds(2);
        Assert.assertTrue(driver.findElement(By.xpath("")).isDisplayed());
    }
    @Test
    public void TC_05_Authentication_Alert_using_seleniumVer4(){
        driver = new ChromeDriver();
        // Get Devtools object
        DevTools devTools = ((HasDevTools)driver).getDevTools();
        devTools.createSession();

        // Enable the Network Domain of devTools
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        // Encode username, password
        Map<String,Object> headers = new HashMap<String, Object>();
        //String basicAuthen = "Basic" + new String(new Base64().encode(String.format("%s:%s", "admin", "admin").getBytes()));
        //headers.put("Authorization", basicAuthen);

        //Set To Header
        devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));
        driver.get("http://the-internet.herokuapp.com/");
    }
    @Test
    public void TC_05_Select_All_Checkbox(){
        driver.get("https://automationfc.github.io/multiple-fields/");

    }

    @Test
    public void TC_06_Custom_Radio(){
    }
    @Test
    public void TC_07_Custom_Radio() {
        driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
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

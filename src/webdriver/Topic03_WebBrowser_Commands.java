package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
public class Topic03_WebBrowser_Commands {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
    String accessPageUrl;
    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/");
    }

    @Test
    public void TC_01_Url() {
        // Lấy ra url/ page hiện tại đang đứng
        String loginPageUrl = driver.getCurrentUrl();

        // Lấy ra page source  HTML/ CSS/ JS của page hiện
        driver.getPageSource();
        Assert.assertTrue(driver.getPageSource().contains("Facebook helps you connect and share with the people in your life."));

        // Lay ra Title cua page hien tai
        driver.getTitle();

        // Lays ra ID của cửa sổ / tab hiện taị
        // Handle Window/ Tab
        driver.getWindowHandle();
        driver.getWindowHandles();

        // Cookies - Framework
        driver.manage().getCookies();

        // Get ra nhung log o dev Tool - FrameWoork
        driver.manage().logs().get(LogType.DRIVER);

        // Apply cho viec tim element cho (findElement/ findElements)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Cho cho page duoc load xong
        driver.manage().timeouts().pageLoadTimeout((Duration.ofSeconds(30)));

        // set truoc khi dung thu vien JavascriptExecutor
        // Inject 1 doan code JS vao trong Browser/ Element
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(10));


        //Neu chi dung 1 lan thi k khai bao bien
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/");

        // Dung nhieu lan
        Assert.assertEquals(loginPageUrl, "https://www.facebook.com/");

        // Trong 1 hàm nếu như có 2 biến cùng tên ( 1 Global, 1 local) -> Sẽ ưu tiên lấy biến local dùng
        // TRong hàm nếu muốn sử dụng biến local thì phải khởi tạo giá trị
        String accessPageUrl;
        //driver.get(accessPageUrl); // Báo lỗi ngay khi viết code

        // Nếu trong hàm mà muốn sử dụng biến global -> Dùng từ khoá this
        driver.get(this.accessPageUrl); // Sẽ không báo lỗi ngay mà run code mới báo lỗi



    }

    @Test
    public void TC_02_Logo() {
        Assert.assertTrue(driver.findElement(By.cssSelector("img.fb_logo")).isDisplayed());
    }

    @Test
    public void TC_03_Form() {
        Assert.assertTrue(driver.findElement(By.xpath("//form[@data-testid='royal_login_form']")).isDisplayed());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

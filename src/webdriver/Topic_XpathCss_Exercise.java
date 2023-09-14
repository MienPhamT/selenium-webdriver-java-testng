import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_XpathCss_Exercise {
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
    public void Register_01_Empty_Data() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.id("txtFirstname")).clear();
        driver.findElement(By.id("txtEmail")).clear();
        driver.findElement(By.id("txtCEmail")).clear();
        driver.findElement(By.id("txtPassword")).clear();
        driver.findElement(By.id("txtCPassword")).clear();
        driver.findElement(By.id("txtPhone")).clear();
        driver.findElement(By.xpath("//button[text() = 'ĐĂNG KÝ' and @type = 'submit']")).click();

        //verify
        Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");
    }

    @Test
    public void Register_02_Invalid_Email_Address() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.id("txtFirstname")).sendKeys("Mia");
        driver.findElement(By.id("txtEmail")).sendKeys("mama");
        driver.findElement(By.id("txtCEmail")).sendKeys("mama");
        driver.findElement(By.id("txtPassword")).sendKeys("mia123@");
        driver.findElement(By.id("txtCPassword")).sendKeys("mia123@");
        driver.findElement(By.id("txtPhone")).sendKeys("0987666567");
        driver.findElement(By.xpath("//button[text() = 'ĐĂNG KÝ' and @type = 'submit']")).click();

        //verify
        List<WebElement> label_error = driver.findElements(By.xpath("//label[@id= 'txtFirstname-error' and @style='display: none;']"));
        Assert.assertEquals(label_error.size(), 0);
        //Assert.assertTrue(driver.findElement(By.xpath("//label[@id= 'txtFirstname-error' and @style= 'display: none;']")).isDisplayed(),"false");
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
    }

    @Test
    public void Register_03_Invalid_Confirm_Email() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.id("txtFirstname")).sendKeys("Mia");
        driver.findElement(By.id("txtEmail")).sendKeys("mama@mana.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("mama");
        driver.findElement(By.id("txtPassword")).sendKeys("mia123@");
        driver.findElement(By.id("txtCPassword")).sendKeys("mia123@");
        driver.findElement(By.id("txtPhone")).sendKeys("0987666567");
        driver.findElement(By.xpath("//button[text() = 'ĐĂNG KÝ' and @type = 'submit']")).click();

        //verify
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")), "Email nhập lại không đúng");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
    }

    @Test
    public void Register_04_Invalid_Password() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.id("txtFirstname")).sendKeys("Mia");
        driver.findElement(By.id("txtEmail")).sendKeys("mama@mana.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("mama@mana.com");
        driver.findElement(By.id("txtPassword")).sendKeys("mia");
        driver.findElement(By.id("txtCPassword")).sendKeys("mia");
        driver.findElement(By.id("txtPhone")).sendKeys("0987666567");
        driver.findElement(By.xpath("//button[text() = 'ĐĂNG KÝ' and @type = 'submit']")).click();

        //verify
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
    }
    @Test
    public void Register_05_Invalid_Confirm_Password() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.id("txtFirstname")).sendKeys("Mia");
        driver.findElement(By.id("txtEmail")).sendKeys("mama@mana.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("mama@mana.com");
        driver.findElement(By.id("txtPassword")).sendKeys("mia123@");
        driver.findElement(By.id("txtCPassword")).sendKeys("mia");
        driver.findElement(By.id("txtPhone")).sendKeys("0987666567");
        driver.findElement(By.xpath("//button[text() = 'ĐĂNG KÝ' and @type = 'submit']")).click();

        //verify
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");
    }
    @Test
    public void Register_06_Invalid_Phone_Number() {
        // Nhập full, phone ít hơn 10 ký tự
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.id("txtFirstname")).sendKeys("Mia");
        driver.findElement(By.id("txtEmail")).sendKeys("mama@mana.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("mama@mana.com");
        driver.findElement(By.id("txtPassword")).sendKeys("mia123@");
        driver.findElement(By.id("txtCPassword")).sendKeys("mia");
        driver.findElement(By.id("txtPhone")).sendKeys("098766");
        driver.findElement(By.xpath("//button[text() = 'ĐĂNG KÝ' and @type = 'submit']")).click();
        //verify
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");

        // Phone lớn hơn 11 số
        driver.findElement(By.id("txtPhone")).clear();
        driver.findElement(By.id("txtPhone")).sendKeys("098766988733");
        driver.findElement(By.xpath("//button[text() = 'ĐĂNG KÝ' and @type = 'submit']")).click();
        //verify
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");

        // Phone không bắt đầu bằng đầu số các nhà mạng
        driver.findElement(By.id("txtPhone")).clear();
        driver.findElement(By.id("txtPhone")).sendKeys("0287669887");
        driver.findElement(By.xpath("//button[text() = 'ĐĂNG KÝ' and @type = 'submit']")).click();
        //verify
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

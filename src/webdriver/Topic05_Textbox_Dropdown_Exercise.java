package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic05_Textbox_Dropdown_Exercise {
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
    public void Login_01() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//span[@class= 'label' and text()= 'Account']")).click();
        driver.findElement(By.xpath("//div[@id= 'header-account']//a[@title = 'My Account']")).click();
        driver.findElement(By.xpath("//span[text()= 'Create an Account']")).click();

        // Fill all fields with valid value
        String first_name = "Mia", middle_name = "Mia", last_name = "Pham", email = getEmailRandom(), password = "123456";
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

        //Assert.assertEquals(driver.findElement(By.xpath("//div[@class ='col-1']//div[@class = 'box-content']/p")).getText(), "Mia Mia Pham" + "mia7@mana.com");
        String acc_info = driver.findElement(By.xpath("//div[@class ='col-1']//div[@class = 'box-content']/p")).getText();
        System.out.println(acc_info);
        String[] acc_info_list = acc_info.split("\n");
        String name = acc_info_list[0];
        String email1 = acc_info_list[1];
        System.out.println(name);
        Assert.assertEquals(name, full_name);
        Assert.assertEquals(email1, email);

        /* Can verify by following way:
        Assert.assertTrue(acc_info.contains(full_name));
        Assert.assertTrue(acc_info.contains(email));
        */
        //Logout
        driver.findElement(By.xpath("//span[@class ='label' and text()='Account']")).click();
        driver.findElement(By.xpath("//div[@id='header-account']//li[@class=' last']")).click();
        sleepInSeconds(2);

        // Login with above registered acc
        driver.findElement(By.xpath("//div[@class = 'footer']//a[@title = 'My Account'] ")).click();
        driver.findElement(By.id("email_address")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        sleepInSeconds(2);
            // verify
        String acc_info_login = driver.findElement(By.xpath("//div[@class ='col-1']//div[@class = 'box-content']/p")).getText();
        Assert.assertTrue(acc_info.contains(full_name));
        Assert.assertTrue(acc_info.contains(email));
    }

    @Test
    public void Login_02_Success() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class = 'footer']//a[@title = 'My Account']")).click();
        /* there is 3 ways to solve this case:
         */
        // Register an account:
        driver.findElement(By.xpath("//span[text()= 'Create an Account']")).click();
        sleepInSeconds(2);

        String first_name = "Mia", middle_name = "Mia", last_name = "Pham", email = getEmailRandom(), password = "123456";
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
    }
    public String getEmailRandom(){
        Random rand = new Random();
        return "mia" + rand.nextInt(99999) + "@gmail.com";
    }
    public String randomUsername(){
        Random rand = new Random();
        return "mia" + rand.nextInt(9999);
    }
    public void sleepInSeconds(long time){
        try {
            Thread.sleep(time*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void Login_03_OrangeHRM() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        // login
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[@type ='submit']")).click();
        sleepInSeconds(3);
        driver.findElement(By.xpath("//ul[@class = 'oxd-main-menu']//span[text()='PIM']")).click();
        sleepInSeconds(3);
        driver.findElement(By.xpath("//li[@class = 'oxd-topbar-body-nav-tab']/a[text() = 'Add Employee']")).click();
        sleepInSeconds(3);
        String employeeId = driver.findElement(By.xpath("//label[text() = 'Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value");

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();",
                driver.findElement(By.xpath("//input[@type ='checkbox']")));

        sleepInSeconds(2);
        // Input info on screen 'Add employee - Create login details'
        String firstName = "Mia", lastName = "Pham";
        driver.findElement(By.name("firstName")).sendKeys(firstName);
        driver.findElement(By.name("lastName")).sendKeys(lastName);

        String username = randomUsername(), password = "Deptrai@123";
        driver.findElement(By.xpath("//label[text()= 'Username']/parent::div/following-sibling::div/input")).sendKeys(username);
        driver.findElement(By.xpath("//label[text()= 'Password']/parent::div/following-sibling::div/input")).sendKeys(password);
        driver.findElement(By.xpath("//label[text()= 'Confirm Password']/parent::div/following-sibling::div/input")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type = 'submit']")).click();
        sleepInSeconds(3);
        // validate info of user
        Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text() = 'Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeId);

        // Click tab Immigaration
        driver.findElement(By.xpath("//div[@class ='orangehrm-tabs-wrapper']/a[text() ='Immigration']")).click();
        sleepInSeconds(2);

        driver.findElement(By.xpath("//h6[text() ='Assigned Immigration Records']/following-sibling::button")).click();
        sleepInSeconds(2);
        String number = "154667676", comment = "Note something";
        driver.findElement(By.xpath("//label[text()= 'Number']/parent::div/following-sibling::div/input")).sendKeys(number);
        driver.findElement(By.xpath("//textarea")).sendKeys(comment);
        driver.findElement(By.xpath("//button[@type = 'submit']")).click();
        sleepInSeconds(2);
        driver.findElement(By.xpath("//i[@class='oxd-icon bi-pencil-fill']/parent::button")).click();
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), number);
        Assert.assertEquals( driver.findElement(By.xpath("//textarea")).getAttribute("value"), comment);

        driver.findElement(By.xpath("//li[@class='oxd-userdropdown']")).click();
        driver.findElement(By.xpath("//ul[@class='oxd-dropdown-menu']/li/a[text()='Logout']")).click();
        sleepInSeconds(3);

        // login again
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.xpath("//button[@type ='submit']")).click();
        sleepInSeconds(2);

        driver.findElement(By.xpath("//ul[@class = 'oxd-main-menu']//span[text()='My Info']")).click();
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text() = 'Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeId);

        driver.findElement(By.xpath("//div[@class ='orangehrm-tabs-wrapper']/a[text() ='Immigration']")).click();
        sleepInSeconds(2);
        driver.findElement(By.xpath("//i[@class='oxd-icon bi-pencil-fill']/parent::button")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.xpath("//label[text()= 'Number']/parent::div/following-sibling::div/input")).getAttribute("value"), number);
        Assert.assertEquals( driver.findElement(By.xpath("//textarea")).getAttribute("value"), comment);


    }

    @Test
    public void Register_04_Invalid_Password() {
    }
    @Test
    public void Register_05_Invalid_Confirm_Password() {
    }
    @Test
    public void Register_06_Invalid_Phone_Number() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic07_Handle_Button_Excercise {
    WebDriver driver;
    // Wait Tường minh: tráng thái cụ thể cho element: Visible, Invisible, presence, clickable, number...
    WebDriverWait explicitWait;
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
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        // driver = new ChromeDriver();
        // Wait Ngầm định : Không rõ ràng cho 1 tráng thái cũ thể nào của element hết
        // Cho việc timf element - findElement
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
       // driver.get("https://www.facebook.com/");
    }

    @Test

    public void TC_01_egov_register_button() {
        driver.get("https://egov.danang.gov.vn/reg");
        WebElement chinhSachCHX = driver.findElement(By.cssSelector("input#chinhSach"));
        WebElement registerBtn = driver.findElement(By.cssSelector("input#button2"));
        String registerBtnBackgroundRGB = registerBtn.getCssValue("background-color");
        Color registerBtnBackgroundColor = Color.fromString(registerBtnBackgroundRGB);
        String registerBtnBackgroundHexa = registerBtnBackgroundColor.asHex();
        if(!chinhSachCHX.isSelected()){
           Assert.assertEquals(registerBtnBackgroundHexa.toUpperCase(), "#A0A0A0");
           chinhSachCHX.click();
        }
        Assert.assertEquals(Color.fromString(registerBtn.getCssValue("background-color")).asHex().toLowerCase(), "#ef5a00");
    }
    @Test
    public void TC_02_Login_Fahasa_button() {
        driver.get("https://www.fahasa.com/customer/account/create");
        sleepInSeconds(2);
        driver.findElement(By.cssSelector("li.popup-login-tab-login ")).click();
        sleepInSeconds(2);
        WebElement sdt =  driver.findElement(By.cssSelector("input#login_username")); sdt.clear();
        WebElement password =  driver.findElement(By.cssSelector("input#login_password")); password.clear();
        WebElement btn_Login = driver.findElement(By.cssSelector("button.fhs-btn-login"));

        Assert.assertFalse(btn_Login.isEnabled());
        Assert.assertEquals(Color.fromString(btn_Login.getCssValue("background-color")).asHex().toLowerCase(), "#000000");

        sdt.sendKeys("miooa@gmail.com");
        password.sendKeys("666hhh@");
        Assert.assertTrue(btn_Login.isEnabled());
        Assert.assertEquals(Color.fromString(btn_Login.getCssValue("background-color")).asHex().toUpperCase(), "#C92127");
    }

   @Test
    public void TC_03_Telerik_Checkbox(){
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        By luggageCoverChx = By.xpath("//label[text() = 'Luggage compartment cover']/preceding-sibling::input");
        if(!driver.findElement(luggageCoverChx).isSelected()){
            driver.findElement(luggageCoverChx).click();
        }
        sleepInSeconds(2);
        // verify checkbox da chon
        Assert.assertTrue(driver.findElement(luggageCoverChx).isSelected());
    }
    @Test
    public void TC_04_Default_RadioBtn(){
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        By petrolRadioBtn = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");

        if (!driver.findElement(petrolRadioBtn).isSelected()){
            driver.findElement(petrolRadioBtn).click();
        }
        Assert.assertTrue(driver.findElement(petrolRadioBtn).isSelected());
    }
    @Test
    public void TC_05_Select_All_Checkbox(){
        driver.get("https://automationfc.github.io/multiple-fields/");

        List<WebElement> listCHX = driver.findElements(By.xpath("//li[@data-type = 'control_checkbox']//input"));
        for (WebElement checkbox: listCHX){
            if (!checkbox.isSelected()){
                checkbox.click();
            }
        }
        for (WebElement checkbox: listCHX){
            Assert.assertTrue(checkbox.isSelected());
        }
        // delete cookies and refresh page:
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();

        // sau khi refresh lai thi phai gan lai bien da tim truoc do :>
        listCHX = driver.findElements(By.xpath("//li[@data-type = 'control_checkbox']//input"));

        WebElement heartAttackCHX = null;
        for (WebElement checkbox: listCHX){
            if (checkbox.getAttribute("value").equals("Heart Attack") && !checkbox.isSelected()){
                checkbox.click();
                heartAttackCHX = checkbox;
            }
        }
        Assert.assertTrue(heartAttackCHX.isSelected());
    }

    @Test
    public void TC_06_Custom_Radio(){
        driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
        // Case1: Dung the input de click -> the input bi che boi 1 th div khac -> khong click duoc -> Failed
        //driver.findElement(By.xpath("//div[text()='Register for your relative']/preceding-sibling::div/input")).click();

        // Case2: Dung the div ben ngoai de click + verify : Click duoc nhung verify bi failed, vi the div k co trang thai la isSelected()

        // Solution 1: Dung the div de click va the input de verify
        // Cach nay 1 element ma can den 2 locator -> kho maintain more difficullity
//        driver.findElement(By.xpath("//div[text()='Register yourself']/preceding-sibling::div/div[@class = 'mat-radio-outer-circle']")).click();
//        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Register for your relative']/preceding-sibling::div/input")).isSelected());

        //Solution 2: Dung input de click (dung JS click) + verify
            //((JavascriptExecutor)driver).executeScript("");
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();",
                driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")));
        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")).isSelected());

    }
    @Test
    public void TC_07_Custom_Radio_Google_form() {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");



    }


    public void sleepInSeconds(long time){
        try {
            Thread.sleep(time*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void selectItemInDropdown(String parentCSS, String childItem, String itemExpected){
        driver.findElement(By.xpath(parentCSS)).click();

        // Vua wait + vua tim element
        List<WebElement> listNumberButton = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childItem)));

        for (WebElement item : listNumberButton){
            if (item.getText().equals(itemExpected)){
                item.click();
                break;
            }
        }
    }
    public void selectItemEditableDropdown(String parentCSS, String childItem, String itemExpected){
        driver.findElement(By.xpath(parentCSS)).clear();
        driver.findElement(By.xpath(parentCSS)).sendKeys(itemExpected);

        // Vua wait + vua tim element
        List<WebElement> listNumberButton = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childItem)));
        for (WebElement item : listNumberButton){
            if (item.getText().equals(itemExpected)){
                item.click();
                break;
            }
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

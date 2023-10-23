package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic06_Custom_Dropdown {
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
    /*
    1 - Click vào 1 thẻ để cho nó xổ hết các item bên trong dropdown ra
    2.1 - Nó sẽ xổ ra chứa hết tất cả các item
    2.2 - Nó sẽ xổ ra nhưng chỉ chứa 1 phần và đang load thêm
    Ngàn/ Triệu record
    3.1 - Nếu như item cần chọn nó hiển thị thì click vào
    3.2 - Nếu như item cần chọn nằm bên dưới thì 1 số trường hợp cần scroll xuống để hiển thị lên rồi mới chọn (Angular/ React/ VueJS/..)
    4 - Trước khi click cần kiểm tra nếu như text của item bằng vs item cần chọn thì click vào
     */
    /*
    1. Visible/ Display/ Visibility: Nhin thay duoc + thao tac duoc: Co tren UI + CO trong HTML
    2. Presence: Co/khong co tren UI + bat buoc co trong HTML
     */
    public void TC_01_jQuery() {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
        selectItemInDropdown("//span[@id='speed-button']", "ul#speed-menu div", "Faster");
        sleepInSeconds(2);
        selectItemInDropdown("//span[@id='files-button']", "ul#files-menu div", "ui.jQuery.js");
        sleepInSeconds(2);
        selectItemInDropdown("//span[@id='number-button']", "ul#number-menu div", "15");
        sleepInSeconds(3);
        selectItemInDropdown("//span[@id='salutation-button']", "ul#salutation-menu div", "Dr.");

        //Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>ul#speed-menu div")).getText(), "Faster");
    }

    @Test
    public void TC_02_ReAct() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

        selectItemInDropdown("i.dropdown.icon", "div.item>span.text", "Christian");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Christian");
    }
    @Test
    public void TC_03_Vue_JS() {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Second Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");

    }
    @Test
    public void TC_04_Editable() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        selectItemEditableDropdown("input.search", "div.tem span", "Albania");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Albania");

    }
    @Test
    public void TC_05_Nopcommerce() {
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
        selectItemInDropdown("//select[@name = 'DateOfBirthDay']", "select[name='DateOfBirthDay'] option[value='18']", "18");
        Assert.assertTrue(driver.findElement(By.cssSelector("select[name='DateOfBirthDay'] option[value='18']")).isSelected());
    }
    public String getEmailRandom(){
        Random rand = new Random();
        return "mia" + rand.nextInt(1,19) + "@gmail.com";
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
    @Test
    public void Register_06_Invalid_Phone_Number() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

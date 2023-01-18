import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class FindElementOnTheWebSiteTest {

    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(FindElementOnTheWebSiteTest.class);
    private ConfigProperty cfg = ConfigFactory.create(ConfigProperty.class);

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Driver up");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    //Go to the tele2 website page https://msk.tele2.ru/shop/number
    //Enter "97" in the "number search" field and start searching
    //Wait for numbers to appear
    public void openPageTele2() {
        driver.get(cfg.tele2Url() + "shop/number");
        logger.info("Tele2 web page opened with phone number selection");
        inputNumber();
        waitPage();
        chooseFirstNumber();
    }

    public void inputNumber() {
        WebElement element = driver.findElement(By.id("searchNumber"));
        Assert.assertTrue(element.isDisplayed());
        logger.info("Found element with id=searchNumber");
        element.sendKeys("97" + Keys.ENTER);  //ввод числа 97 в поисковую строку и нажатие Enter
        logger.info("Value entered in search string 97");
    }

    public void waitPage() {
        WebDriverWait wait = new WebDriverWait(driver, 5);//задано явное ожидание (5 сек)
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("preloader-icon")));
        logger.info("Waiting for element ended");
    }

    public void chooseFirstNumber(){
        WebElement element = driver.findElement(By.xpath("//*[@class='phone-number']"));
        element.click();
        logger.info("First item selected");
        ExpectedConditions.invisibilityOfElementLocated(By.className("info-modal tariff-selector-modal"));
        logger.info("Prompt window popped up");
    }

    @After
    public void setDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Driver completed");
        }
    }
}

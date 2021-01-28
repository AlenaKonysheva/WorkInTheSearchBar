import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;

public class SampleTest2 {
    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(SampleTest2.class);

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");    //Открытие безголового хрома
        driver = new ChromeDriver(options);
        logger.info("Драйвер поднят");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); //Определение неявного тайм аута на весь проект
    }
    @Test   //открытие страницы
    public void openPage() {
        driver.get("https://otus.ru/");
        logger.info("Открыта страница otus");

    }
    @After
    public void setDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Драйвер завершил работу");
        }
    }




}

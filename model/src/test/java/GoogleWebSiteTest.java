import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class GoogleWebSiteTest {

    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(GoogleWebSiteTest.class);
    private ConfigProperty cfg = ConfigFactory.create(ConfigProperty.class);

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Driver up");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    public void openPageGoogle() {
        driver.get(cfg.googleUrl());
        logger.info("Google page opened");
        driver.manage().window().maximize();
        chekTitle();
    }

    public void chekTitle() {
        System.out.println("Expected page title is: Google");
        System.out.println("Actual title is: " + driver.getTitle());
        Assert.assertEquals("Google", driver.getTitle());
        logger.info("title check complete");
    }

    @After
    public void setDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Driver completed");
        }
    }
}

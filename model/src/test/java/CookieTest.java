import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.*;
import org.junit.*;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class CookieTest {
    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(CookieTest.class);
    private ConfigProperty cfg = ConfigFactory.create(ConfigProperty.class);

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Driver up");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    public void addCookieTest() {
        driver.get(cfg.otusUrl());
        logger.info("Otus webpage was open");

        addingCookiesViaManage();
        logger.info("Cookies Via Manage added");

        addingCookiesViaVariables();
        logger.info("Cookies Via Variables added");

        printCookiesValues();
    }

    public void addingCookiesViaManage() {
        driver.manage().addCookie(new Cookie("Otus1", "Value1"));
        logger.info("Cookie Otus1 was aded");
        logger.info("Display cookie " + driver.manage().getCookieNamed("Otus1"));

        driver.manage().addCookie(new Cookie("Otus2", "Value2"));
        logger.info("Cookie Otus2 was aded");
        logger.info("Display cookie " + driver.manage().getCookieNamed("Otus2"));

    }

    public void addingCookiesViaVariables() {
        //way 1
        Cookie cookie = new Cookie("Otus3", "Value3");
        driver.manage().addCookie(cookie);
        logger.info("Cookie Otus3 was aded");
        logger.info("Display cookie " + driver.manage().getCookieNamed("Otus3"));
        //way 2
        driver.manage().addCookie(new Cookie("Otus4", "Value4"));
        logger.info("Cookie Otus4 was aded");
        logger.info("Display cookie " + driver.manage().getCookieNamed("Otus4"));
    }

    public void printCookiesValues() {
        logger.info("Number of all cookies " + driver.manage().getCookies().size());
        logger.info("Displaying the number of cookies " + driver.manage().getCookies());
    }


    @Test
    public void deleteCookieTest() {
        driver.get(cfg.otusUrl());
        logger.info("Otus webpage was open");

        addingCookiesViaManage();
        printCookiesValues();
        deleteCookie();
    }

    public void deleteCookie() {
        driver.manage().deleteCookieNamed("Otus1");
        logger.info("Deleted cookie named Otus1");
        printCookiesValues();

        driver.manage().deleteCookieNamed("Otus2");
        logger.info("Deleted cookie named Otus2");
        printCookiesValues();

        driver.manage().deleteAllCookies();
        logger.info("Deleted all cookies");


        logger.info("Array size with hands after removing all cookies " + driver.manage().getCookies().size());
    }

    @After
    public void setDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Driver completed");
        }
    }

}

import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WindowBrowserTest {
    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(WindowBrowserTest.class);
    private ConfigProperty cfg = ConfigFactory.create(ConfigProperty.class);

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Driver up");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    public void openPageInMaxWindow() {
        driver.manage().window().maximize();
        driver.get(cfg.googleUrl());
        logger.info(driver.manage().window().getSize());
    }

    @Test
    public void openPageInTheGivenWindowSize() {
        driver.manage().window().setSize(new Dimension(800, 600));
        driver.get(cfg.googleUrl());
        logger.info(driver.manage().window().getSize());
    }

    @Test
    public void moveWindows() throws InterruptedException {
        driver.manage().window().setSize(new Dimension(800, 600));
        driver.get(cfg.otusUrl());
        logger.info("Current window coordinates " + driver.manage().window().getPosition());

        Point point = driver.manage().window().getPosition();
        point.x = point.x + 200;
        driver.manage().window().setPosition(point);
        point.y = point.y + 200;
        driver.manage().window().setPosition(point);
        point.x = point.x - 50;
        driver.manage().window().setPosition(point);
        point.y = point.y - 150;
        driver.manage().window().setPosition(point);

        logger.info("Window coordinates after change " + driver.manage().window().getPosition());
    }

    @After
    public void setDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Driver completed");
        }
    }

}

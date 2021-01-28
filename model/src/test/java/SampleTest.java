import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class SampleTest {
    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(SampleTest.class);
    private ConfigProperty cfg = ConfigFactory.create(ConfigProperty.class);

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Драйвер поднят");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); //Определение неявного тайм аута на весь проект
    }

    @Test   //открытие страницы
    public void openPage() {
        driver.get(cfg.otusUrl());
        logger.info("Открыта страница otus");
    }
    @Test   //проверка title
    public void checkTitle() {
        driver.navigate().to("https://otus.ru/");
        String actualTitle = driver.getTitle();
        String expectedTitle = "Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям";
        Assert.assertEquals(actualTitle, expectedTitle);
        logger.info("Проверка title завершена");
    }

    @Test    //Добавление/Удаление Cokkie
    public void Cookie() {
        driver.get(cfg.otusUrl());  //открываю url
        driver.manage().addCookie(new Cookie("Otus1", "Value1"));
        driver.manage().addCookie(new Cookie("Otus2", "Value2"));
        //добавление через переменную
        Cookie cookie = new Cookie("Otus3", "Value3");
        driver.manage().addCookie(cookie);
        driver.manage().addCookie(new Cookie("Otus4", "Value4"));

        logger.info(driver.manage().getCookies().size());//Вывод количества cookie
        logger.info(driver.manage().getCookies());     //Вывести на экран все cookie
        logger.info(driver.manage().getCookieNamed("Otus1")); //Вывести на экран Cookie1
        driver.manage().deleteCookieNamed("Otus2");   //Удалить Cookie2 по имени куки
        driver.manage().deleteCookie(cookie);  //Удалить Cookie3 по переменной
        driver.manage().deleteAllCookies();    //Удалить все куки
        logger.info(driver.manage().getCookies().size());//Проверевка что все  куки удалены,выводит размер массива с куками
    }

    @Test  //открыть браузер в полном окне/заданном размере и вывести размер
    public void fullSizeWindows() {
        driver.manage().window().maximize();
        driver.get(cfg.otusUrl());
        driver.manage().window().setSize(new Dimension(800, 600));
        logger.info(driver.manage().window().getSize());
    }

    @Test  //открыть браузер/передвинуть его по квадрату(по двум координатам)
    public void moveWindows() throws InterruptedException {
        driver.manage().window().setSize(new Dimension(800, 600));
        driver.get(cfg.otusUrl());
        logger.info(driver.manage().window().getPosition());
        Point point = driver.manage().window().getPosition();
        point.x = point.x + 100;
        driver.manage().window().setPosition(point);
        point.y = point.y + 100;
        driver.manage().window().setPosition(point);
        point.x = point.x - 100;
        driver.manage().window().setPosition(point);
        point.y = point.y - 100;
        driver.manage().window().setPosition(point);
        logger.info(driver.manage().window().getPosition());
    }

    @After
    public void setDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Драйвер завершил работу");
        }
    }

}

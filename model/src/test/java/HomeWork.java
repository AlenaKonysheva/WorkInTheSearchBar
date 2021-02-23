import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class HomeWork {

    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(HomeWork.class);

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Драйвер поднят");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    //Откройте сайт https://yandex.ru Разверните окно браузера на полный экран(не киоск) Проверьте title страницы
    public void openPageYandex() {
        driver.get("https://yandex.ru/");
        logger.info("Открыта страница yandex");
        driver.manage().window().maximize();
        chekTitle();
    }

    public void chekTitle() {

        driver.navigate().to("https://yandex.ru/");
        String actualTitle = driver.getTitle();
        String expectedTitle = "Яндекс";
        Assert.assertEquals(actualTitle, expectedTitle);
        System.out.println("Page title is: " + driver.getTitle());
        logger.info("Проверка title завершена");
    }

    @Test
    //Перейти на сайт теле2 страница https://msk.tele2.ru/shop/number
    //Ввести в поле "поиск номера" 97 и начать поиск
    //Дождаться появления номеров
    public void openPageTele2() {
        driver.get("https://msk.tele2.ru/shop/number");
        logger.info("Открыта страница tele2 с выбором номера телефона");
        inputNumber();
        waitPage();
    }

    public void inputNumber() {
        WebElement element = driver.findElement(By.id("searchNumber"));
        Assert.assertTrue(element.isDisplayed());
        logger.info("Найден элемент с id=searchNumber");
        element.sendKeys("97"+ Keys.ENTER);  //ввод числа 97 в поисковую строку и нажатие Enter
        logger.info("В поисковую строку введено значение 97");
    }
    public void waitPage() {
        WebDriverWait wait = new WebDriverWait(driver, 5);//задано явное ожидание (5 сек)
       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("preloader-icon")));
        logger.info("Ожидание элемента завершилось");

    }

    @After
    public void setDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Драйвер завершил работу");
        }
    }
}

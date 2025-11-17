package ru.yandex.scooter;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    
    @Before
    public void setUp() {
        // Выбираем браузер
        String browser = System.getProperty("browser", "chrome");
        
        if ("firefox".equals(browser)) {
            driver = new FirefoxDriver();
        } else {
            driver = new ChromeDriver();
        }
        
        // Настройки драйвера
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        // Открываем сайт
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }
    
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

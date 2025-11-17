package ru.yandex.scooter.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    private final WebDriver driver;

    // Локаторы
    private final By orderButtonTop = By.className("Button_Button__ra12g");
    private final By orderButtonBottom = By.xpath(".//button[contains(@class, 'Button_Middle') and text()='Заказать']");
    private final By scooterLogo = By.className("Header_LogoScooter__3lsAR");
    private final By yandexLogo = By.className("Header_LogoYandex__3TSOI");
    private final By orderNumberInput = By.xpath(".//input[@placeholder='Введите номер заказа']");
    private final By goButton = By.xpath(".//button[text()='Go!']");
    private final By orderNotFoundImage = By.xpath(".//img[contains(@alt, 'NotFound') or contains(@alt, 'найдено')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOrderButtonTop() {
        driver.findElement(orderButtonTop).click();
    }

    public void clickOrderButtonBottom() {
        WebElement element = driver.findElement(orderButtonBottom);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void clickFaqQuestion(int questionIndex) {
        By questionLocator = By.id("accordion__heading-" + questionIndex);
        WebElement question = driver.findElement(questionLocator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", question);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(question)).click();
    }

    public String getFaqAnswer(int questionIndex) {
        By answerLocator = By.id("accordion__panel-" + questionIndex);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(answerLocator));
        return driver.findElement(answerLocator).getText();
    }

    public void clickScooterLogo() {
        driver.findElement(scooterLogo).click();
    }

    public void clickYandexLogo() {
        driver.findElement(yandexLogo).click();
    }

    public void checkOrderStatus(String orderNumber) {
        try {
            // Ждем пока элемент станет кликабельным
            WebElement input = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(orderNumberInput));

            // Очищаем поле и вводим номер
            input.clear();
            input.sendKeys(orderNumber);

            // Кликаем кнопку Go
            WebElement goBtn = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(goButton));
            goBtn.click();

        } catch (Exception e) {
            System.out.println("Ошибка при проверке статуса заказа: " + e.getMessage());
            throw e;
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public boolean isOrderNotFoundDisplayed() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(orderNotFoundImage));
            return driver.findElement(orderNotFoundImage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isOrderNotFoundMessageDisplayed() {
        try {
            // Ждем загрузки страницы статуса
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.urlContains("track"));

            // Проверяем различные элементы которые могут указывать на ошибку
            By[] errorIndicators = {
                    By.xpath("//*[contains(text(), 'Не найдено')]"),
                    By.xpath("//*[contains(text(), 'NotFound')]"),
                    By.xpath("//*[contains(@class, 'NotFound')]"),
                    orderNotFoundImage
            };

            for (By indicator : errorIndicators) {
                try {
                    if (driver.findElement(indicator).isDisplayed()) {
                        return true;
                    }
                } catch (Exception e) {
                    // Продолжаем проверять другие локаторы
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    // Метод для скролла к элементу
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }
}

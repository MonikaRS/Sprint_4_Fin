package ru.yandex.scooter.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OrderPage {
    private final WebDriver driver;

    // Локаторы для формы заказа
    private final By nameInput = By.xpath(".//input[@placeholder='* Имя']");
    private final By surnameInput = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By addressInput = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroInput = By.xpath(".//input[@placeholder='* Станция метро']");
    private final By metroStationOption = By.className("select-search__row");
    private final By phoneInput = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath(".//button[text()='Далее']");

    // Локаторы для второй части формы
    private final By dateInput = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriod = By.className("Dropdown-placeholder");
    private final By rentalPeriodOption = By.xpath(".//div[@class='Dropdown-option']");
    private final By blackPearlCheckbox = By.id("black");
    private final By greyHopelessnessCheckbox = By.id("grey");
    private final By commentInput = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath(".//button[contains(@class, 'Button_Middle') and text()='Заказать']");

    // Локаторы для модального окна
    private final By confirmButton = By.xpath(".//button[text()='Да']");
    private final By successModal = By.className("Order_ModalHeader__3FDaJ");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // Заполнение первой части формы
    public void fillFirstStep(String name, String surname, String address, String phone) {
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(surnameInput).sendKeys(surname);
        driver.findElement(addressInput).sendKeys(address);

        // Выбор станции метро
        driver.findElement(metroInput).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(metroStationOption));
        driver.findElement(metroStationOption).click();

        driver.findElement(phoneInput).sendKeys(phone);
        driver.findElement(nextButton).click();
    }

    // Заполнение второй части формы
    public void fillSecondStep(String date, String rentalPeriodText, String color, String comment) {
        // Заполняем дату
        driver.findElement(dateInput).sendKeys(date);

        // Выбираем срок аренды
        driver.findElement(rentalPeriod).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(rentalPeriodOption));

        // Ищем нужный срок аренды
        for (WebElement option : driver.findElements(rentalPeriodOption)) {
            if (option.getText().equals(rentalPeriodText)) {
                option.click();
                break;
            }
        }

        // Выбираем цвет
        if ("black".equals(color)) {
            driver.findElement(blackPearlCheckbox).click();
        } else if ("grey".equals(color)) {
            driver.findElement(greyHopelessnessCheckbox).click();
        }

        // Заполняем комментарий (если есть)
        if (comment != null && !comment.isEmpty()) {
            driver.findElement(commentInput).sendKeys(comment);
        }

        // Нажимаем кнопку заказа
        driver.findElement(orderButton).click();
    }

    // Подтверждение заказа в модальном окне
    public void confirmOrder() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(confirmButton)).click();
    }

    // Проверка успешного создания заказа
    public boolean isOrderSuccess() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(successModal));
            return driver.findElement(successModal).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
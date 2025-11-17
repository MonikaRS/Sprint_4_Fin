package ru.yandex.scooter.tests;

import org.junit.Test;
import ru.yandex.scooter.BaseTest;

import static org.junit.Assert.assertTrue;

public class AdditionalTest extends BaseTest {

    @Test
    public void testHomePageLoads() {
        // Простой тест - проверяем что главная страница загружается
        driver.get("https://qa-scooter.praktikum-services.ru/");

        String currentUrl = driver.getCurrentUrl();

        System.out.println("Current URL: " + currentUrl);
        System.out.println("Page Title: " + driver.getTitle());

        // Проверяем только URL - это надежнее
        assertTrue("Должны быть на главной странице самоката",
                currentUrl.contains("qa-scooter"));

        // Дополнительная проверка что страница загрузилась
        assertTrue("Страница должна иметь заголовок",
                !driver.getTitle().isEmpty());
    }
}
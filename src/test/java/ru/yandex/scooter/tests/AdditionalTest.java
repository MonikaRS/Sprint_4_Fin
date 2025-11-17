package ru.yandex.scooter.tests;

import org.junit.Test;
import ru.yandex.scooter.BaseTest;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class AdditionalTest extends BaseTest {

    @Test
    public void testHomePageLoads() {
        // Открываем главную страницу
        driver.get("https://qa-scooter.praktikum-services.ru/");

        String currentUrl = driver.getCurrentUrl();
        String pageTitle = driver.getTitle();

        // Проверяем что мы на правильной странице
        assertTrue("URL должен содержать 'qa-scooter'",
                currentUrl.contains("qa-scooter"));

        // Проверяем что заголовок страницы не пустой
        assertFalse("Заголовок страницы не должен быть пустым",
                pageTitle.isEmpty());
    }
}
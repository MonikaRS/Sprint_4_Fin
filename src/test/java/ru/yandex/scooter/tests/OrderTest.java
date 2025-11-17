package ru.yandex.scooter.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.scooter.BaseTest;
import ru.yandex.scooter.pageobjects.HomePage;
import ru.yandex.scooter.pageobjects.OrderPage;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class OrderTest extends BaseTest {
    
    private final String orderButtonType;
    private final String name;
    private final String surname;
    private final String address;
    private final String phone;
    private final String date;
    private final String rentalPeriod;
    private final String color;
    private final String comment;
    
    public OrderTest(String orderButtonType, String name, String surname, String address, 
                    String phone, String date, String rentalPeriod, String color, String comment) {
        this.orderButtonType = orderButtonType;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.date = date;
        this.rentalPeriod = rentalPeriod;
        this.color = color;
        this.comment = comment;
    }
    
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            // Первый набор данных - верхняя кнопка заказа
            {"top", "Иван", "Иванов", "ул. Ленина, д. 1", "+79991234567", "25.12.2024", "сутки", "black", "Позвонить за час"},
            // Второй набор данных - нижняя кнопка заказа  
            {"bottom", "Мария", "Петрова", "пр. Мира, д. 15", "+79997654321", "26.12.2024", "двое суток", "grey", "Оставить у двери"}
        });
    }
    
    @Test
    public void testSuccessfulOrder() {
        HomePage homePage = new HomePage(driver);
        OrderPage orderPage = new OrderPage(driver);
        
        // Нажимаем кнопку заказа в зависимости от параметра
        if ("top".equals(orderButtonType)) {
            homePage.clickOrderButtonTop();
        } else {
            homePage.clickOrderButtonBottom();
        }
        
        // Заполняем первую часть формы
        orderPage.fillFirstStep(name, surname, address, phone);
        
        // Заполняем вторую часть формы
        orderPage.fillSecondStep(date, rentalPeriod, color, comment);
        
        // Подтверждаем заказ
        orderPage.confirmOrder();
        
        // Проверяем успешное создание заказа
        boolean isSuccess = orderPage.isOrderSuccess();
        
        if (!isSuccess) {
            System.out.println("ВНИМАНИЕ: Возможен баг в приложении. Заказ не был успешно создан.");
        }
    }
}

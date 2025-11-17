# 🛵 Sprint 4 - Scooter Service Autotests

Проект автоматизации тестирования веб-сервиса аренды самокатов "Яндекс Самокат".

## 🚀 Быстрый старт

### Предварительные требования
- **Java** 11 или выше
- **Maven** 3.9.0 или выше
- **Браузер** Chrome/Firefox

### Установка и запуск
```bash
# Клонирование репозитория
git clone https://github.com/MonikaRS/Sprint_4_Fin.git
cd Sprint_4_Fin

# Запуск всех тестов
mvn clean test
🛠 Технологический стек
Технология	Версия	Назначение
Java	11	Основной язык программирования
JUnit	4.13.2	Фреймворк для тестирования
Maven	3.9.0	Сборка и управление зависимостями
Selenium WebDriver	4.15.0	Автоматизация браузера
Maven Surefire	3.2.5	Запуск тестов
📁 Структура проекта
text
Sprint_4_Fin/
├── src/
│   ├── main/java/ru/yandex/scooter/pageobjects/  # Page Objects
│   │   ├── HomePage.java        # Главная страница
│   │   └── OrderPage.java       # Страница заказа
│   └── test/java/ru/yandex/scooter/tests/       # Тестовые классы
│       ├── BaseTest.java        # Базовый класс тестов
│       ├── OrderTest.java       # Тесты оформления заказа
│       ├── FAQTest.java         # Тесты раздела FAQ
│       └── AdditionalTest.java  # Дополнительные тесты
├── pom.xml                      # Конфигурация Maven
└── README.md                    # Документация
🧪 Тестовые сценарии
📦 OrderTest
Оформление заказа самоката

Заполнение форм личных данных

Выбор даты аренды и цвета самоката

Подтверждение заказа

❓ FAQTest
Проверка раздела "Вопросы о важном"

Раскрытие/скрытие ответов на вопросы

Валидация текста ответов

🏠 AdditionalTest
Дополнительные проверки главной страницы

Навигация по логотипам

Базовая функциональность

🎯 Команды для запуска
bash
# Запуск всех тестов
mvn clean test

# Запуск конкретного тестового класса
mvn test -Dtest=OrderTest

# Запуск нескольких тестов
mvn test -Dtest="OrderTest,FAQTest"

# Запуск с определенным браузером
mvn test -Dbrowser=chrome
🔧 Настройка окружения
Установите Java 11:

bash
java -version
Установите Maven:

bash
mvn --version
Настройте браузер драйвер:

Проект использует WebDriver Manager - автоматическую настройку драйверов

📊 Отчеты
После запуска тестов отчеты доступны в:

text
target/surefire-reports/    # Подробные отчеты о тестах
target/site/surefire-report.html  # HTML отчет
👩‍💻 Разработчик
MonikaRS
📧 GitHub Profile
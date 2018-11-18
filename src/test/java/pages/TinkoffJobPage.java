package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;

public class TinkoffJobPage extends Page {
    public TinkoffJobPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public TinkoffJobPage open() {
        driver.get("https://moscow-job.tinkoff.ru/");
        logger.info("Открытие страницы Tinkoff Job");
        waitForTitle("Работа в Тинькофф Банке в Москве");
        logger.info("Проверка заголовка страницы Tinkoff Job");
        return this;
    }

    public TinkoffJobPage setFioField(String fio) {
        putText(By.cssSelector("[name='fio']"), fio);
        logger.info(String.format("Установка имени \"%s\"", fio));
        return this;
    }

    public TinkoffJobPage assertFioTitle(String text) {
        assertEquals(text, driver.findElement(By.cssSelector(".Row__row_AjrJL:nth-child(1) .Error__errorMessage_q8BBY")).getText());
        logger.info(String.format("Проверка заголовка имени \"%s\"", text));
        return this;
    }

    public TinkoffJobPage setEmailField(String email) {
        putText(By.cssSelector("[name='email']"), email);
        logger.info(String.format("Установка почты \"%s\"", email));
        return this;
    }

    public TinkoffJobPage assertEmailTitle(String text) {
        assertEquals(text, driver.findElement(By.cssSelector(".Row__row_AjrJL:nth-child(2) :nth-child(1) .Error__errorMessage_q8BBY")).getText());
        logger.info(String.format("Проверка заголовка почты \"%s\"", text));
        return this;
    }

    public TinkoffJobPage setCityField(String city) {
        putText(By.cssSelector("[name='city']"), city);
        logger.info(String.format("Установка города \"%s\"", city));
        return this;
    }

    public TinkoffJobPage assertCityTitle(String text) {
        assertEquals(text, driver.findElement(By.cssSelector(".Row__row_AjrJL:nth-child(3) .Error__errorMessage_q8BBY")).getText());
        logger.info(String.format("Проверка заголовка города \"%s\"", text));
        return this;
    }

    public TinkoffJobPage setPhoneField(String phone) {
        putText(By.cssSelector("[name='phone']"), phone);
        logger.info(String.format("Установка телефона \"%s\"", phone));
        return this;
    }

    public TinkoffJobPage assertPhoneTitle(String text) {
        assertEquals(text, driver.findElement(By.cssSelector(".Row__row_AjrJL:nth-child(2) :nth-child(2) .Error__errorMessage_q8BBY")).getText());
        logger.info(String.format("Проверка заголовка телефона \"%s\"", text));
        return this;
    }
}

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
        waitForTitle("Работа в Тинькофф Банке в Москве");
        return this;
    }

    public TinkoffJobPage setFioField(String fio) {
        putText(By.cssSelector("[name='fio']"), fio);
        return this;
    }

    public TinkoffJobPage assertFioTitle(String text) {
        assertEquals(text, driver.findElement(By.cssSelector(".Row__row_AjrJL:nth-child(1) .Error__errorMessage_q8BBY")).getText());
        return this;
    }

    public TinkoffJobPage setEmailField(String email) {
        putText(By.cssSelector("[name='email']"), email);
        return this;
    }

    public TinkoffJobPage assertEmailTitle(String text) {
        assertEquals(text, driver.findElement(By.cssSelector(".Row__row_AjrJL:nth-child(2) :nth-child(1) .Error__errorMessage_q8BBY")).getText());
        return this;
    }

    public TinkoffJobPage setCityField(String city) {
        putText(By.cssSelector("[name='city']"), city);
        return this;
    }

    public TinkoffJobPage assertCityTitle(String text) {
        assertEquals(text, driver.findElement(By.cssSelector(".Row__row_AjrJL:nth-child(3) .Error__errorMessage_q8BBY")).getText());
        return this;
    }

    public TinkoffJobPage setPhoneField(String phone) {
        putText(By.cssSelector("[name='phone']"), phone);
        return this;
    }

    public TinkoffJobPage assertPhoneTitle(String text) {
        assertEquals(text, driver.findElement(By.cssSelector(".Row__row_AjrJL:nth-child(2) :nth-child(2) .Error__errorMessage_q8BBY")).getText());
        return this;
    }
}

import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;

public class JobTest extends BaseRunner {
    @Test
    public void testFirst() {
        driver.get(baseUrl);
        driver.findElement(By.name("fio")).click();
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("phone")).click();
        driver.findElement(By.name("city")).click();
        driver.findElement(By.cssSelector("div.SelectItem__data_3u2ir.SelectItem__placeholder_3_ypX.SelectItem__isRequired_2J1mB.SelectItem__data_noWrap_FIC1K")).click();
        assertEquals("Поле обязательное", driver.findElement(By.cssSelector("div.Error__errorMessage_q8BBY")).getText());
        assertEquals("Поле обязательное", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Электронная почта'])[1]/following::div[3]")).getText());
        assertEquals("Необходимо указать номер телефона", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Номер телефона'])[1]/following::div[3]")).getText());
        assertEquals("Поле обязательное", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Город'])[1]/following::div[3]")).getText());
        //assertEquals("Поле обязательное", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Продавец-консультант'])[2]/following::div[1]")).getText());
    }

    @Test
    public void testSecond() {
        driver.get(baseUrl);
        driver.findElement(By.name("fio")).clear();
        driver.findElement(By.name("fio")).sendKeys("Иванович");
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys("mail");
        driver.findElement(By.name("phone")).clear();
        driver.findElement(By.name("phone")).sendKeys("+7 (100) 123-45-67");
        driver.findElement(By.name("city")).clear();
        driver.findElement(By.name("city")).sendKeys("Arzamas 16");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Пройдите обучение'])[1]/following::div[7]")).click();
        assertEquals("Недостаточно информации. Введите фамилию, имя и отчество через пробел (Например: Иванов Иван Алексеевич)", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Фамилия, имя и отчество'])[1]/following::div[3]")).getText());
        assertEquals("Введите корректный адрес эл. почты", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Электронная почта'])[1]/following::div[3]")).getText());
        assertEquals("Код города/оператора должен начинаться с цифры 3, 4, 5, 6, 8, 9", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Номер телефона'])[1]/following::div[3]")).getText());
        assertEquals("Допустимо использовать только буквы русского, латинского алфавита и дефис", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Город'])[1]/following::div[3]")).getText());
    }


}

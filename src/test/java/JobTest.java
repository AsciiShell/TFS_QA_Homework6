import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;

public class JobTest extends BaseRunner {
    @Test
    public void testFirst() {
        driver.get(baseUrl);
        driver.findElement(By.xpath("//*[@name='fio']")).click();
        driver.findElement(By.xpath("//*[@name='email']")).click();
        driver.findElement(By.xpath("//*[@name='phone']")).click();
        driver.findElement(By.xpath("//*[@name='city']")).click();
        driver.findElement(By.xpath("//div[@role='listbox' and @class='SelectWrap__root_35mlc']")).click();
        driver.findElement(By.xpath("//div[@class='Header__header_3Teza']/*[text()='Заполните анкету']")).click();
        assertEquals("Поле обязательное", driver.findElement(By.xpath("//*[@name='fio']/ancestor::div[@class='Row__row_AjrJL']//div[@class='Error__errorMessage_q8BBY']")).getText());
        assertEquals("Поле обязательное", driver.findElement(By.xpath("//*[@name='email']/ancestor::div[@class='FormField__field_1iwkM FormField__fieldWithFootnotes_G2-u7 FormField__field_builderStyle_2DWOd']//div[@class='Error__errorMessage_q8BBY']")).getText());
        assertEquals("Необходимо указать номер телефона", driver.findElement(By.xpath("//*[@name='phone']/ancestor::div[@class='FormField__field_1iwkM FormField__fieldWithFootnotes_G2-u7 FormField__field_builderStyle_2DWOd']//div[@class='Error__errorMessage_q8BBY']")).getText());
        assertEquals("Поле обязательное", driver.findElement(By.xpath("//*[@name='city']/ancestor::div[@class='Row__row_AjrJL']//div[@class='Error__errorMessage_q8BBY']")).getText());
        assertEquals("Поле обязательное", driver.findElement(By.xpath("//div[@role='listbox' and @class='SelectWrap__root_35mlc']/ancestor::div[@class='Row__row_AjrJL']//div[@class='Error__errorMessage_q8BBY']")).getText());
    }

    @Test
    public void testSecond() {
        driver.get(baseUrl);
        driver.findElement(By.cssSelector("[name='fio']")).clear();
        driver.findElement(By.cssSelector("[name='fio']")).sendKeys("Иванович");
        driver.findElement(By.cssSelector("[name='email']")).clear();
        driver.findElement(By.cssSelector("[name='email']")).sendKeys("mail");
        driver.findElement(By.cssSelector("[name='phone']")).clear();
        driver.findElement(By.cssSelector("[name='phone']")).sendKeys("+7 (100) 123-45-67");
        driver.findElement(By.cssSelector("[name='city']")).clear();
        driver.findElement(By.cssSelector("[name='city']")).sendKeys("Arzamas 16");
        driver.findElement(By.cssSelector(".Header__header_3Teza")).click();
        assertEquals("Недостаточно информации. Введите фамилию, имя и отчество через пробел (Например: Иванов Иван Алексеевич)", driver.findElement(By.cssSelector(".Row__row_AjrJL:nth-child(1) .Error__errorMessage_q8BBY")).getText());
        assertEquals("Введите корректный адрес эл. почты", driver.findElement(By.cssSelector(".Row__row_AjrJL:nth-child(2) :nth-child(1) .Error__errorMessage_q8BBY")).getText());
        assertEquals("Код города/оператора должен начинаться с цифры 3, 4, 5, 6, 8, 9", driver.findElement(By.cssSelector(".Row__row_AjrJL:nth-child(2) :nth-child(2) .Error__errorMessage_q8BBY")).getText());
        assertEquals("Допустимо использовать только буквы русского, латинского алфавита и дефис", driver.findElement(By.cssSelector(".Row__row_AjrJL:nth-child(3) .Error__errorMessage_q8BBY")).getText());
    }


}

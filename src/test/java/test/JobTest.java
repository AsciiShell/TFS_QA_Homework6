package test;

import org.junit.Test;


public class JobTest extends BaseRunner {
    @Test
    public void testMain() {
        app.tinkoffJob.open()
                .setFioField("Иванович")
                .setEmailField("mail")
                .setPhoneField("+7 (100) 123-45-67")
                .setCityField("Arzamas 16")
                .assertFioTitle("Недостаточно информации. Введите фамилию, имя и отчество через пробел (Например: Иванов Иван Алексеевич)")
                .assertEmailTitle("Введите корректный адрес эл. почты")
                .assertPhoneTitle("Код города/оператора должен начинаться с цифры 3, 4, 5, 6, 8, 9")
                .assertCityTitle("Допустимо использовать только буквы русского, латинского алфавита и дефис");
    }
}

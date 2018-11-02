import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MobileTest extends BaseRunner {


    public class CheckBox {
        private String name;

        CheckBox(String text) {
            name = text;
        }

        private String getRootXpath() {
            return "//span[contains(text(),'" + name + "')]/ancestor::div[@class='ui-checkbox-directive']";
        }

        private WebElement getCheckBox() {
            return driver.findElement(By.xpath(getRootXpath() + "//label[contains(@class, 'ui-checkbox')]"));
        }

        boolean isChecked() {
            return getCheckBox().getAttribute("class").contains("ui-checkbox_checked");
        }

        void setActive(boolean status) {
            if (isChecked() != status) {
                getCheckBox().findElement(By.xpath("./div[@data-qa-file='UICheckbox']")).click();
            }
        }

        String getText() {
            return driver.findElement(By.xpath(getRootXpath() + "//*[contains(@class,'ui-checkbox__text-wrapper')]")).getText();
        }

    }

    public MobileTest() {
        baseUrl = "https://www.tinkoff.ru/mobile-operator/tariffs/";
    }

    @Test
    public void testFirst() {
        driver.get(baseUrl);
        CheckBox ch = new CheckBox("Мессенджеры");
        ch.setActive(false);
        System.out.println(ch.getText() + ch.isChecked());
    }
}

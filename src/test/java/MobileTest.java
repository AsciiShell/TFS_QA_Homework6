import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class MobileTest extends BaseRunner {

    public class SelectInput {
        private WebElement element;

        SelectInput(String name) {
            new WebDriverWait(driver, 5).until(d -> driver.findElements(By.xpath("//span[contains(text(),'" + name + "')]/ancestor::div[@class='ui-dropdown-field']")).size() != 0);
            element = driver.findElement(By.xpath("//span[contains(text(),'" + name + "')]/ancestor::div[@class='ui-dropdown-field']"));
        }

        private List<WebElement> getAllItems() {
            return element.findElements(By.xpath(".//div[@class='ui-dropdown-field-list__item-view ui-select__option_with-subtext_right-side']"));
        }

        private boolean isMaximized() {
            return getAllItems().get(0).isDisplayed();
        }

        private void maximize() {
            if (!isMaximized()) {
                element.click();
                new WebDriverWait(driver, 5).until(d -> isMaximized());
            }
        }

        private void minimize() {
            if (isMaximized()) {
                element.click();
                new WebDriverWait(driver, 5).until(d -> !isMaximized());
            }
        }

        List<String> getAllItemsText() {
            List<String> result = new ArrayList<>();
            List<WebElement> els = getAllItems();
            maximize();
            for (WebElement el : els) {
                result.add(el.getText());
            }
            minimize();
            return result;
        }

        String getCurrentItemText() {
            return element.findElement(By.xpath("//div[@class='ui-select__title-flex']")).getText();
        }

        void setItem(int index) {
            maximize();
            getAllItems().get(index).click();
        }

        void setItem(String text) {
            maximize();
            new WebDriverWait(driver, 5).until(d ->
                    {
                        List<WebElement> els = getAllItems();
                        for (WebElement el : els) {
                            if (el.getText().contains(text)) {
                                el.click();
                                return true;
                            }
                        }
                        return false;
                    }
            );
        }
    }

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

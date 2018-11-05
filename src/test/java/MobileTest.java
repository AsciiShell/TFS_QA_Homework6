import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MobileTest extends BaseRunner {

    private class SelectInput {
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

    private class CheckBox {
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

    private class TextInput {
        private WebElement element;

        TextInput(String name) {
            element = driver.findElement(By.xpath("//*[@class='ui-input__label_placeholder-text' and contains(text(), '" + name + "')]/ancestor::div[@class='ui-input__column']//input[not(@disabled)]"));
        }

        String getText() {
            return element.getAttribute("value");
        }

        void setText(String text) {
            new Actions(driver).moveToElement(element)
                    .click()
                    .sendKeys(Keys.chord(Keys.CONTROL, "a"))
                    .sendKeys(Keys.DELETE)
                    .sendKeys(Keys.END)
                    .sendKeys(text)
                    .perform();
        }
    }

    private class Button {
        private WebElement element;

        Button(String name) {
            element = driver.findElement(By.xpath("//button[contains(string(),'" + name + "')]"));
        }

        public boolean isEnable() {
            return element.isEnabled();
        }

        public void click() {
            element.click();
        }
    }

    private int getTotalPrice() {
        return Integer.parseInt(driver.findElement(By.xpath("//div[@class='ui-form__field ui-form__field_title']/h3")).getText().split(":")[1].split(" ")[1]);
    }

    public MobileTest() {
        baseUrl = "https://www.tinkoff.ru/mobile-operator/tariffs/";
    }


    @Test
    public void testGoogle() {
        driver.get("https://www.google.ru/");
        new Actions(driver).moveToElement(driver.findElement(By.xpath("//input[@name='q']")))
                .click()
                .sendKeys(Keys.chord(Keys.CONTROL, "a"))
                .sendKeys(Keys.DELETE)
                .sendKeys("мобайл тинькофф")
                .perform();
        new WebDriverWait(driver, 5).until(d ->
                d.findElements(By.xpath("//li[string()='мобайл тинькофф тарифы']")).size() != 0
        );

        driver.findElement(By.xpath("//li[string()='мобайл тинькофф тарифы']")).click();
        new WebDriverWait(driver, 5).until(d ->
                d.findElements(By.xpath("//a[@href='" + baseUrl + "']")).size() != 0
        );
        driver.findElement(By.xpath("//a[@href='" + baseUrl + "']")).click();

        new WebDriverWait(driver, 10).until(d -> {
            boolean check = false;
            for (String title : driver.getWindowHandles()) {
                driver.switchTo().window(title);
                check = d.getTitle().equals("Тарифы Тинькофф Мобайл");
                if (check) {
                    break;
                }
            }
            return check;
        });

        assertEquals(driver.getTitle(), "Тарифы Тинькофф Мобайл");
        driver.switchTo().window(String.valueOf(driver.getWindowHandles().toArray()[0]));
        driver.close();

        driver.switchTo().window(String.valueOf(driver.getWindowHandles().toArray()[0]));
        assertEquals(driver.getCurrentUrl(), baseUrl);
    }

    @Test
    public void testRegion() {
        driver.get(baseUrl);
        new WebDriverWait(driver, 30).until(d ->
                d.findElements(By.xpath("//span[@class='MvnoRegionConfirmation__option_3mrvz MvnoRegionConfirmation__optionRejection_2yo5M']")).size() != 0
        );
        driver.findElement(By.xpath("//span[@class='MvnoRegionConfirmation__option_3mrvz MvnoRegionConfirmation__optionRejection_2yo5M']")).click();
        driver.findElement(By.xpath("//div[@class='MobileOperatorRegionsPopup__region_2eF67' and text()='Москва']")).click();
        assertEquals(driver.findElement(By.xpath("//div[@class='MvnoRegionConfirmation__title_3WFCP']")).getText(), "Москва и Московская область");
        driver.navigate().refresh();
        assertEquals(driver.findElement(By.xpath("//div[@class='MvnoRegionConfirmation__title_3WFCP']")).getText(), "Москва и Московская область");
        int defaultMoscowPrice = getTotalPrice();
        new SelectInput("Интернет").setItem("Безлимитны");
        new SelectInput("Звонки").setItem("Безлимитны");
        new CheckBox("Режим модема").setActive(true);
        new CheckBox("SMS").setActive(true);
        int maxMoscowPrice = getTotalPrice();

        driver.findElement(By.xpath("//div[@class='MvnoRegionConfirmation__title_3WFCP']")).click();
        driver.findElement(By.xpath("//div[@class='MobileOperatorRegionsPopup__region_2eF67' and text()='Краснодар']")).click();
        new WebDriverWait(driver, 10).until(d -> getTotalPrice() != maxMoscowPrice);
        int defaultKrasnodarPrice = getTotalPrice();
        new SelectInput("Интернет").setItem("Безлимитны");
        new SelectInput("Звонки").setItem("Безлимитны");
        new CheckBox("Режим модема").setActive(true);
        new CheckBox("SMS").setActive(true);
        int maxKrasnodarPrice = getTotalPrice();

        assertNotEquals(defaultMoscowPrice, defaultKrasnodarPrice);
        assertEquals(maxMoscowPrice, maxKrasnodarPrice);
    }

    @Test
    public void testFree() {
        driver.get(baseUrl);
        new SelectInput("Интернет").setItem("0 ГБ");
        new SelectInput("Звонки").setItem("0 минут");
        new CheckBox("Мессенджеры").setActive(false);
        new CheckBox("Социальные сети").setActive(false);
        new CheckBox("Музыка").setActive(false);
        new CheckBox("Видео").setActive(false);
        new CheckBox("SMS").setActive(false);
        assertEquals(getTotalPrice(), 0);
        assertFalse(new Button("SIM").isEnable());
    }

    @Test
    public void testDownload() {
        driver.get("https://www.tinkoff.ru/mobile-operator/documents/");

        WebElement el = driver.findElement(By.xpath("//a[@class='Link__link_3mUSi Link__link_color_blue_1bJUP Link__link_type_simple_3yVSl Link__link_nodecorated_3p7l4']"));
        el.click();

        String[] path = el.getAttribute("href").split("/");
        File f = new File(System.getProperty("download") + File.separator + path[path.length - 1]);
        new WebDriverWait(driver, 10).until(d -> f.exists());
        assertTrue(f.exists());
        assertTrue(f.length() != 0);
    }
}

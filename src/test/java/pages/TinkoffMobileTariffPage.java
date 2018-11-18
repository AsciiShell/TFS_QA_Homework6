package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TinkoffMobileTariffPage extends Page {
    public String baseUrl = "https://www.tinkoff.ru/mobile-operator/tariffs/";

    public TinkoffMobileTariffPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

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
            putText(element, text);
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

    private By getFirstRegionSelector() {
        return By.xpath("//span[@class='MvnoRegionConfirmation__option_3mrvz MvnoRegionConfirmation__optionRejection_2yo5M']");
    }

    private By getRegionSelector() {
        return By.xpath("//div[@class='MvnoRegionConfirmation__title_3WFCP']");
    }

    public TinkoffMobileTariffPage open() {
        driver.get(baseUrl);
        waitForTitle("Тарифы Тинькофф Мобайл");
        return this;
    }

    public TinkoffMobileTariffPage assertUrl() {
        assertEquals(driver.getCurrentUrl(), baseUrl);
        return this;
    }

    public String getRegion() {
        wait.until(d -> d.findElements(By.xpath("//span[@class='MvnoRegionConfirmation__regionName_2hRIp']")).size() != 0 ||
                d.findElements(getRegionSelector()).size() != 0);
        if (driver.findElements(By.xpath("//span[@class='MvnoRegionConfirmation__regionName_2hRIp']")).size() != 0) {
            String region = driver.findElement(By.xpath("//span[@class='MvnoRegionConfirmation__regionName_2hRIp']")).getText();
            return region.substring(0, region.length() - 1);
        } else {
            return driver.findElement(getRegionSelector()).getText();
        }
    }

    public TinkoffMobileTariffPage setRegion(String region) {
        wait.until(d ->
                d.findElements(getFirstRegionSelector()).size() != 0 ||
                        d.findElements(getRegionSelector()).size() != 0
        );
        if (driver.findElements(getFirstRegionSelector()).size() != 0) {
            driver.findElement(getFirstRegionSelector()).click();
        } else {
            driver.findElement(getRegionSelector()).click();
        }
        driver.findElement(By.xpath("//div[@class='MobileOperatorRegionsPopup__region_2eF67']/div[contains(text(), '" + region + "')]")).click();
        wait.until(d -> getRegion().contains(region));
        return this;
    }

    public TinkoffMobileTariffPage assertRegion(String region) {
        assertTrue(getRegion().contains(region));
        return this;
    }

    public TinkoffMobileTariffPage setInternet(String value) {
        new SelectInput("Интернет").setItem(value);
        return this;
    }

    public TinkoffMobileTariffPage setMinutes(String value) {
        new SelectInput("Звонки").setItem(value);
        return this;
    }

    public TinkoffMobileTariffPage setUnlimitedMessages(boolean value) {
        new CheckBox("Мессенджеры").setActive(value);
        return this;
    }

    public TinkoffMobileTariffPage setUnlimitedSocialNetworks(boolean value) {
        new CheckBox("Социальные сети").setActive(value);
        return this;
    }

    public TinkoffMobileTariffPage setUnlimitedMusic(boolean value) {
        new CheckBox("Музыка").setActive(value);
        return this;
    }

    public TinkoffMobileTariffPage setUnlimitedVideo(boolean value) {
        new CheckBox("Видео").setActive(value);
        return this;
    }

    public TinkoffMobileTariffPage setUnlimitedSMS(boolean value) {
        new CheckBox("SMS").setActive(value);
        return this;
    }

    public TinkoffMobileTariffPage setModem(boolean value) {
        new CheckBox("Режим модема").setActive(value);
        return this;
    }

    public int getTotalPrice() {
        return Integer.parseInt(driver.findElement(By.xpath("//div[@class='ui-form__field ui-form__field_title']/h3")).getText().split(":")[1].split(" ")[1]);
    }

    public TinkoffMobileTariffPage assertPriceEqual(int price) {
        assertEquals(getTotalPrice(), price);
        return this;
    }

    public TinkoffMobileTariffPage assertPriceNotEqual(int price) {
        assertNotEquals(getTotalPrice(), price);
        return this;
    }
}

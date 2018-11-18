package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Page {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public Page(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public void waitForTitle(String substring) {
        wait.until(d -> d.getTitle().contains(substring));
    }

    public void switchToWindow(String windowName) {
        wait.until(d -> {
            boolean check = false;
            for (String title : d.getWindowHandles()) {
                d.switchTo().window(title);
                check = d.getTitle().equals(windowName);
                if (check)
                    break;
            }
            return check;
        });
    }

    public void getPage(String url) {
        driver.navigate().to(url);
    }

    public List<WebElement> getElementsByText(String searchText) {
        return driver.findElements(By.xpath(String.format("//*[contains(text(),'%s')]", searchText)));
    }

    public void putText(By selector, String text) {
        putText(driver.findElement(selector), text);
    }

    public void putText(WebElement element, String text) {
        new Actions(driver).moveToElement(element)
                .click()
                .sendKeys(Keys.chord(Keys.CONTROL, "a"))
                .sendKeys(Keys.DELETE)
                .sendKeys(Keys.END)
                .sendKeys(text)
                .sendKeys(Keys.ENTER)
                .perform();
    }

    public void refresh() {
        driver.navigate().refresh();
    }

    public void closeCurrentTab() {
        driver.close();
    }

    public void switchToMainTab() {
        driver.switchTo().window(driver.getWindowHandles().iterator().next());
    }
}

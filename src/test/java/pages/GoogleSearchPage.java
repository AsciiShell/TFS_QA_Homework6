package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class GoogleSearchPage extends Page {

    public GoogleSearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get("https://www.google.ru/");
        waitForTitle("Google");
    }

    public void openSearchResultPage(String shortRequest, String fullRequest) {
        new Actions(driver).moveToElement(driver.findElement(By.xpath("//input[@name='q']")))
                .click()
                .sendKeys(Keys.chord(Keys.CONTROL, "a"))
                .sendKeys(Keys.DELETE)
                .sendKeys(shortRequest)
                .perform();
        wait.until(d -> d.findElements(By.xpath(String.format("//li[@class='sbct' and contains(string(),'%s')]", fullRequest))).size() != 0);
        driver.findElement(By.xpath(String.format("//li[@class='sbct' and contains(string(),'%s')]", fullRequest))).click();
    }

    public void openSearchResultPage(String request) {
        openSearchResultPage(request, request);
    }

}

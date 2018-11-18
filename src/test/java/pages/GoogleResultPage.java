package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class GoogleResultPage extends Page {

    public GoogleResultPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public GoogleResultPage clickSearchResultsByLinkContains(String link) {
        wait.until(d -> d.findElements(By.xpath("//a[@href='" + link + "']")).size() != 0);
        driver.findElement(By.xpath("//a[@href='" + link + "']")).click();
        logger.info(String.format("Переход по ссылке в поисковой выдаче Google \"%s\"", link));
        return this;
    }
}

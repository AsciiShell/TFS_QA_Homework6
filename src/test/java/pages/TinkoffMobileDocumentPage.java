package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.io.File;

import static junit.framework.TestCase.assertTrue;

public class TinkoffMobileDocumentPage extends Page {
    public String baseUrl = "https://www.tinkoff.ru/mobile-operator/documents/";

    public TinkoffMobileDocumentPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public TinkoffMobileDocumentPage open() {
        driver.get(baseUrl);
        logger.info("Открытие страницы документов Tinkoff Mobile");
        return this;
    }

    public TinkoffMobileDocumentPage downloadDocument(String name) {

        WebElement el = driver.findElement(By.xpath(String.format("//a[@class='Link__link_3mUSi Link__link_color_blue_1bJUP Link__link_type_simple_3yVSl Link__link_nodecorated_3p7l4' and contains(text(), '%s')]", name)));
        el.click();
        logger.info(String.format("Загрузка документа \"%s\"", name));
        String[] path = el.getAttribute("href").split("/");
        File f = new File(System.getProperty("download") + File.separator + path[path.length - 1]);
        wait.until(d -> f.exists());
        assertTrue(f.exists());
        assertTrue(f.length() != 0);
        logger.info(String.format("Файл \"%s\" скачался в \"%s\" и занимает \"%d\" байт", name, f.toPath(), f.length()));
        return this;
    }
}

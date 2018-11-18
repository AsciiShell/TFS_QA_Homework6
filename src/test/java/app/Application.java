package app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.*;
import test.BrowsersFactory;

import java.util.concurrent.TimeUnit;

public class Application {
    public Logger logger = LoggerFactory.getLogger(Application.class);
    private WebDriverWait wait;
    private WebDriver driver;
    public GoogleSearchPage google;
    public GoogleResultPage googleResults;
    public TinkoffJobPage tinkoffJob;
    public TinkoffMobileTariffPage tinkoffMobile;
    public TinkoffMobileDocumentPage tinkoffMobileDocument;

    public final String browserName = System.getProperty("browser") == null ? "chrome" : System.getProperty("browser");


    public Application() {
        driver = new EventFiringWebDriver(getDriver());
        wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //pages
        google = new GoogleSearchPage(driver);
        googleResults = new GoogleResultPage(driver);
        tinkoffJob = new TinkoffJobPage(driver);
        tinkoffMobile = new TinkoffMobileTariffPage(driver);
        tinkoffMobileDocument = new TinkoffMobileDocumentPage(driver);
    }

    public void quit() {
        driver.quit();
        driver = null;
    }

    private WebDriver getDriver() {
        return BrowsersFactory.buildDriver(browserName);
    }

}

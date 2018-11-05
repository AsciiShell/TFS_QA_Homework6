import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class BaseRunner {
    WebDriver driver;
    String baseUrl;

    @Before
    public void setUp() {
        driver = getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    private WebDriver getDriver() {
        return BrowsersFactory.chrome.create();
    }

}

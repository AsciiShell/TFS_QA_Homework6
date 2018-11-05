import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;


public enum BrowsersFactory {
    chrome {
        public WebDriver create() {
            updateProperty("chrome");

            HashMap<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("plugins.plugins_disabled", new String[]{"Chrome PDF Viewer"});
            //chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("plugins.always_open_pdf_externally", true);
            try {
                System.setProperty("download", Files.createTempDirectory(null).toAbsolutePath().toString());
            } catch (IOException e) {
                System.setProperty("download", System.getProperty("TEMP"));
            }
            chromePrefs.put("download.default_directory", System.getProperty("download"));

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            options.setExperimentalOption("prefs", chromePrefs);
            DesiredCapabilities cap = DesiredCapabilities.chrome();
            cap.setCapability(ChromeOptions.CAPABILITY, options);
            return new ChromeDriver(cap);
        }
    },
    firefox {
        public WebDriver create() {
            updateProperty("firefox");
            //Disable login to console and redirect log to an external file
            System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");

            FirefoxOptions options = new FirefoxOptions();
            options.addPreference("dom.webnotifications.enabled", false);
            return new FirefoxDriver(options);
        }
    },
    opera {
        public WebDriver create() {
            updateProperty("opera");
            OperaOptions options = new OperaOptions();
            options.setBinary(System.getProperty("operaPath"));
            options.addArguments("--disable-notifications");
            return new OperaDriver(options);
        }
    };

    public WebDriver create() {
        return null;
    }

    void updateProperty(String browserName) {
        System.out.println(String.format("\nstarting %s-browser......", browserName));
        if (System.getProperty("browser") == null) System.setProperty("browser", browserName);
    }
}
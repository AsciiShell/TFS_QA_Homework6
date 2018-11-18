package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.HashMap;

public class BrowsersFactory {
    public static WebDriver buildDriver(String browserName) {
        switch (browserName) {

            case "chrome_remote": {
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
                options.addArguments("--headless");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.setExperimentalOption("prefs", chromePrefs);
                DesiredCapabilities cap = DesiredCapabilities.chrome();
                cap.setCapability(ChromeOptions.CAPABILITY, options);

                try {
                    return new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), options);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
            case "firefox": {
                //Disable login to console and redirect log to an external file
                System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");

                FirefoxOptions options = new FirefoxOptions();
                options.addPreference("dom.webnotifications.enabled", false);
                return new FirefoxDriver(options);
            }

            case "opera": {
                OperaOptions options = new OperaOptions();
                options.setBinary(System.getProperty("operaPath"));
                options.addArguments("--disable-notifications");
                return new OperaDriver(options);
            }
            default: {
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
        }
    }
}
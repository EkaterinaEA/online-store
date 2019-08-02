package info.sjd.service;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverService {
    private static final String SEP = System.getProperty("file.separator");
    private static final String CHROMEDRIVER_PATH = System.getProperty("user.dir") + SEP + "lib" + SEP + "webdriver" + SEP + "chromedriver.exe";

    public static WebDriver getWebDriver() {

        System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_PATH);

//		ChromeOptions options = new ChromeOptions();
//		options.setHeadless(true);
//		WebDriver driver = new ChromeDriver(options);

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(50, TimeUnit.SECONDS);

        return driver;
    }
}

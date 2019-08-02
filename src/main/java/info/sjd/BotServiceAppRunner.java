package info.sjd;

import org.openqa.selenium.WebDriver;
import info.sjd.service.BotService;
import info.sjd.service.WebDriverService;

import java.util.logging.Logger;

public class BotServiceAppRunner {

    public static Logger log = Logger.getLogger(BotServiceAppRunner.class.getName());

    public static void main(String[] args) throws InterruptedException  {

        WebDriver driver = WebDriverService.getWebDriver();

        BotService bot = new BotService();
        bot.generateUsername();
        bot.botProm();
        Thread.sleep(6000);
        bot.killDriver();

        log.info("Name: " + bot.getUsername() + "; Password: " + bot.getPassword() + "; email: "
                + bot.getUsername() + "@maildrop.cc");

    }

}
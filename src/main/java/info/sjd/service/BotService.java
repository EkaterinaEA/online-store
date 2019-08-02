package info.sjd.service;

import java.util.Random;

import info.sjd.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BotService {

    private static String password = "AlexanderPushkin" + new Random().nextInt(1000+1) + "$" ;
    private static String username;

    private WebDriver driver = new ChromeDriver();

    public void killDriver() {
        driver.quit();
    }

    public String generateUsername() throws InterruptedException {
        Random rand = new Random();

        int  randomUsernameDiv = rand.nextInt(59) + 1;
        driver.get("http://namegenerators.org/random-username-generator/");
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[3]/form/input")).click();
        username = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[5]/div[" + randomUsernameDiv + "]")).getText();
        return username;
    }

    private void generateEmail(String username) {
        WebDriver driver2 = new ChromeDriver();
        driver2.get("https://maildrop.cc/");
        WebElement input = driver2.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[1]/nav//div[2]/form/div[1]/input"));
        input.sendKeys(username);
        driver2.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[1]/nav//div[2]/form/button")).click();
        driver2.quit();
    }

    private boolean validateUsername() {
        try {
            driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/div/div[2]/div/form/dl[1]/dd[2]")).getText();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void botProm() throws InterruptedException {

        driver.get("https://prom.ua/join-customer");
        driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div[2]/div[1]/form/div[2]/input")).sendKeys(username);
        Thread.sleep(600);
        System.out.println(validateUsername());
        while (validateUsername() == true) {
            username = username + "1";
            driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div[2]/div[1]/form/div[2]/input")).clear();
            driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div[2]/div[1]/form/div[2]/input")).sendKeys(username);
        }
        generateEmail(username);
        driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div[2]/div[1]/form/div[3]/input")).sendKeys(username + "@maildrop.cc");
        driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div[2]/div[1]/form/div[4]/input")).sendKeys(password);
        driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div[2]/div[1]/form/div[7]/button")).click();
        Thread.sleep(6000);
    }

    public void botPromWithUsersData(WebDriver driver, String name, String email, String userPassword) throws InterruptedException {

        driver.get("https://prom.ua/join-customer");
        driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div[2]/div[1]/form/div[2]/input")).sendKeys(name);
        Thread.sleep(100);
        System.out.println(validateUsername());
        while (validateUsername() == true) {
            name = name + "1";
            driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div[2]/div[1]/form/div[2]/input")).clear();
            driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div[2]/div[1]/form/div[2]/input")).sendKeys(name);
        }

        driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div[2]/div[1]/form/div[3]/input")).sendKeys(email);
        driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div[2]/div[1]/form/div[4]/input")).sendKeys(userPassword);
        driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div[2]/div[1]/form/div[7]/button")).click();
        Thread.sleep(6000);
    }

    public void botPromWithUserObject(WebDriver driver, User user) throws InterruptedException {
        String name = user.getName();
        driver.get("https://prom.ua/join-customer");
        driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div[2]/div[1]/form/div[2]/input")).sendKeys(name);
        Thread.sleep(100);
        System.out.println(validateUsername());
        while (validateUsername() == true) {
            name = name + "1";
            driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div[2]/div[1]/form/div[2]/input")).clear();
            driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div[2]/div[1]/form/div[2]/input")).sendKeys(name);
        }

        driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div[2]/div[1]/form/div[3]/input")).sendKeys(user.getEmail());
        driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div[2]/div[1]/form/div[4]/input")).sendKeys(user.getPassword());
        driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div[2]/div[1]/form/div[7]/button")).click();
        Thread.sleep(6000);
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

}

package Solutions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class Calender {
    static WebDriver driver;

    public static void calender(String expmonth, String exyear, String exday) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions Options = new ChromeOptions();
        Options.addArguments("-Start--Maximized");
        driver = new ChromeDriver(Options);
        Thread.sleep(3000);

        driver.navigate().to("https://demoqa.com/automation-practice-form");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scrollBy(0,150)");
        driver.findElement(By.id("dateOfBirthInput")).click();
        String monthyear = driver.findElement(By.xpath("//div[@class='react-datepicker__header']/*[1]")).getText();
        WebElement date = driver.findElement(By.xpath("(//div[@class='react-datepicker__month']/*/*)"));

        while (!(splitword(monthyear)[0].equals(expmonth) && splitword(monthyear)[1].equals(exyear) )) {
            driver.findElement(By.xpath("//button[@aria-label='Next Month']")).click();
            monthyear = driver.findElement(By.xpath("//div[@class='react-datepicker__header']/*[1]")).getText();
            System.out.println(monthyear);
        }
        if(date.equals(exday)){
            date.click();
        }
        Thread.sleep(5000);
        driver.close();
        driver.quit();
    }


    public static String[] splitword(String monthyear) {
        return monthyear.split(" ");
    }

    @Test
    public void runner() throws InterruptedException {
        calender("July", "2025", "24");
    }


}

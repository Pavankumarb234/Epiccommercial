package Solutions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.io.File;

public class Fileupload {
    public static WebDriver driver;
@Test
    public void upload() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions Options=new ChromeOptions();
        Options.addArguments("--start-maximized");
        Options.addArguments("--disable-notifications");
        driver= new ChromeDriver(Options);
        driver.get("https://demoqa.com/upload-download");

       WebElement Destination = driver.findElement(By.xpath("//div[@class='form-file']/*[2]"));
       Destination.sendKeys("C:\\Users\\PavanKumarBi_2vwp2tq\\Pictures\\Screenshots\\#1.png");

       Thread.sleep(3000);
       driver.close();
       driver.quit();
    }
}

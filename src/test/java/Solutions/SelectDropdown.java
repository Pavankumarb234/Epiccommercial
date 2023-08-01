package Solutions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.List;

public class SelectDropdown extends TestBase{
    @Test
    public void Dropdown() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions Options = new ChromeOptions();
        Options.addArguments("-Start--Maximized");
        Options.addArguments("--disable-notifications");
        driver =new ChromeDriver();
        driver.get("https://chercher.tech/practice/practice-dropdowns-selenium-webdriver");

        Select select = new Select(driver.findElement(By.xpath("//select[@id='first']")));
        select.selectByVisibleText("Iphone");
        Thread.sleep(2000);

        List<WebElement> Options1=select.getOptions();
        for(WebElement select1: Options1){
            System.out.println(select1.getText()+"_____"+ select1.getTagName());
        }
        driver.close();
        driver.quit();
    }
    public void SelectDynamicDropdown(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");

    }
}

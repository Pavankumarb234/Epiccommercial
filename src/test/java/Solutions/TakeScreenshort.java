package Solutions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class TakeScreenshort {
    public static WebDriver driver;
@Test
    public void TSS() throws IOException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions Options =new ChromeOptions();
        Options.addArguments("--start-maximized");
        Options.addArguments("--disable-notifications");
        driver=new ChromeDriver(Options);
        driver.get("https://demoqa.com/upload-download");

        File Src =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File Des =new File("C:\\Users\\PavanKumarBi_2vwp2tq\\Pictures\\Screenshots\\pic.png");
        FileUtils.copyFile(Src,Des);

    }
}

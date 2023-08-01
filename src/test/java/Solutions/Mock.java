package Solutions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Mock {
    public static WebDriver driver;
    public static String Student = "Krishna";
    public static int rollno = 21;

    @Test
    public static String getPageTitle(WebDriver driver) {
        System.out.println(driver.getTitle());
        return null;

    }

    public Mock() {
        System.out.println(Student);
//        System.out.println(rollno);
    }
@Test
    public static void login() {
//        driver = new ChromeDriver();
//        WebDriverManager.chromedriver().setup();
        System.out.println(rollno);
    }
}

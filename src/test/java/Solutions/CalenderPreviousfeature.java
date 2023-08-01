package Solutions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;


public class CalenderPreviousfeature{

    public static String[] splitmonthyear(String monthYearValue) {
        return monthYearValue.split(" ");
    }

    public static void selectDate(String expDate, String expMonth, String expYear) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.hyrtutorials.com/p/calendar-practice.html");
//        real code
        driver.findElement(By.id("second_date_picker")).click();
        System.out.println("clciked on second calender");
        String monthYearValue = driver.findElement(By.className("ui-datepicker-title")).getText();

        if (Integer.parseInt(splitmonthyear(monthYearValue)[1]) > Integer.parseInt(expYear)) {

            while (!(splitmonthyear(monthYearValue)[0].equalsIgnoreCase(expMonth)
                    && (splitmonthyear(monthYearValue)[1].equalsIgnoreCase(expYear)))) {
                driver.findElement(By.xpath("//span[text()='Prev']")).click();
                monthYearValue = driver.findElement(By.className("ui-datepicker-title")).getText();
            }
            driver.findElement(By.xpath("//a[text()='" + expDate + "']")).click();
        } else {
            while( (!(splitmonthyear(monthYearValue)[0].equalsIgnoreCase(expMonth)
                    && (splitmonthyear(monthYearValue)[1].equalsIgnoreCase(expYear))))) {
                driver.findElement(By.xpath("//span[text()='Next']")).click();
                monthYearValue = driver.findElement(By.className("ui-datepicker-title")).getText();
            }
            driver.findElement(By.xpath("//a[text()='" + expDate + "']")).click();
        }
        System.out.println(monthYearValue);
        driver.close();
        driver.quit();
    }

    @Test
    public void execution() {
        selectDate("28", "July", "2026");
    }

}



package Solutions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Smith {
    public static String[] splitmonthyear(String monthYearValue) {
        return monthYearValue.split(" ");
    }
@Test
    public static void selectDate() throws InterruptedException {
//            String expDate, String expMonth, String expYear) throws InterruptedException {
    WebDriverManager.chromedriver().setup();
    WebDriver driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.navigate().to("https://www.mrandmrssmith.com/");
    Thread.sleep(3000);
    driver.findElement(By.xpath("//a[@id='nav-main-find']")).click();
    Thread.sleep(3000);
    driver.findElement(By.xpath("//input[@id='react-s_query']")).sendKeys("paris");
    Thread.sleep(2000);
    driver.findElement(By.xpath("//input[@id='react-s_query']")).sendKeys(Keys.ENTER);

//        real code
//        driver.findElement(By.id("//div[@id='checkin-checkout-follow']/*[1]")).click();
    System.out.println("clciked on second calender");
    String adultsvalue = driver.findElement(By.xpath("//input[@name='adults']")).getAttribute("value");
    System.out.println(adultsvalue);

    if (Integer.parseInt((adultsvalue)) > Integer.parseInt("0")) {

        while (!((adultsvalue).equalsIgnoreCase("4"))) {
            driver.findElement(By.xpath("(//button[@name='increment'])[1]")).click();
            adultsvalue = driver.findElement(By.xpath("//input[@name='adults']")).getAttribute("value");
        }
//        } else {
//            while( (!(splitmonthyear(monthYearValue)[0].equalsIgnoreCase(expMonth)
//                    && (splitmonthyear(monthYearValue)[1].equalsIgnoreCase(expYear))))) {
//                driver.findElement(By.xpath("//span[text()='Next']")).click();
//                monthYearValue = driver.findElement(By.className("ui-datepicker-title")).getText();
//            }
//            driver.findElement(By.xpath("//a[text()='" + expDate + "']")).click();
//        }
//        System.out.println(monthYearValue);
        driver.close();
        driver.quit();
    }

//    @Test
//    public void execution() {
////        selectDate("29", "June", "2015");
//        System.out.println("asdf");
//    }
}}


package Solutions;

        import io.github.bonigarcia.wdm.WebDriverManager;
        import org.openqa.selenium.By;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.chrome.ChromeDriver;
        import org.testng.annotations.Test;

public class Testing {

    public static void selectDate(String expDate, String expMonth, String expYear) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.hyrtutorials.com/p/calendar-practice.html");
        driver.findElement(By.id("second_date_picker")).click();
        System.out.println("clciked on second calender");
        String monthYearValue = driver.findElement(By.className("ui-datepicker-title")).getText();
        String monthValue= monthYearValue.split(" ")[0].trim();
        String  yearValue= monthYearValue.split(" ")[1].trim();

        if(Integer.parseInt(yearValue) > Integer.parseInt(expYear)) {

            if (!(monthValue.equals(expMonth) && yearValue.equals(expYear))) {
                while (!(yearValue.equalsIgnoreCase(expYear))) {
                    driver.findElement(By.xpath("//span[text()='Prev']")).click();
                    monthYearValue = driver.findElement(By.className("ui-datepicker-title")).getText();
                    yearValue = monthYearValue.split(" ")[1].trim();
                }
                while (!(monthValue.equalsIgnoreCase(expMonth))) {
                    driver.findElement(By.xpath("//span[text()='Prev']")).click();
                    monthYearValue = driver.findElement(By.className("ui-datepicker-title")).getText();
                    monthValue = monthYearValue.split(" ")[0].trim();
                }
                driver.findElement(By.xpath("//a[text()='" + expDate + "']")).click();
            }
        }

        else {
            if (!(monthValue.equals(expMonth) && yearValue.equals(expYear))) {
                while (!(yearValue.equalsIgnoreCase(expYear))) {
                    driver.findElement(By.xpath("//span[text()='Next']")).click();
                    monthYearValue = driver.findElement(By.className("ui-datepicker-title")).getText();
                    yearValue = monthYearValue.split(" ")[1].trim();
                }
                while (!(monthValue.equalsIgnoreCase(expMonth))) {
                    driver.findElement(By.xpath("//span[text()='Next']")).click();
                    monthYearValue = driver.findElement(By.className("ui-datepicker-title")).getText();
                    monthValue = monthYearValue.split(" ")[0].trim();
                }
                driver.findElement(By.xpath("//a[text()='" + expDate + "']")).click();
            }
        }

    }
    @Test
    public void execution() throws InterruptedException {
        selectDate("10","June","2025");

    }

}



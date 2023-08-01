package Solutions;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;

public class  PhotoInExtent extends TestBase {
    public class ReportGen {

        static ExtentTest test;
        static ExtentReports report;
        static ExtentSparkReporter reporter;
        WebDriver driver;
        @BeforeClass
        public void initiateReport(){
            report = new ExtentReports();
            reporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/Reports/ExtentReport.html");
            test = report.createTest("mnv");
            report.attachReporter(reporter);
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.get("https://www.facebook.com/login/");
        }

        public String screen(WebDriver driver){
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File(System.getProperty("user.dir")+"/Reports/Pic.JPG");
            try {
                FileUtils.copyFile(src,dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("name of screenShot is "+dest.getName());
            System.out.println("absolute path is "+dest.getAbsolutePath());
            System.out.println("file location is "+dest);
            return dest.getName();
        }
        @Test
        public void testCase01()  {
            String title = driver.getTitle();
            if(title.equalsIgnoreCase("Log in to Facebook")) {
                test.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(screen(driver)).build());
                Assert.assertEquals(title, "Log in to Facebook");
            }
            else{
                test.info("Failed test case");
                test.log(Status.FAIL,"wrong url");
                Assert.assertEquals(title,"manoj");
            }
        }
        @Test
        public void testCase02() {
            test.log(Status.FAIL, "hello kumar");
            Assert.assertEquals(3,2);
        }
        @AfterMethod
        public void tearDown(ITestResult result){
            report.flush();
        }
    }
}

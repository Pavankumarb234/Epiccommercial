package Solutions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;


@CucumberOptions(features = "src/test/resources/features/login.feature",
        glue = {"Solutions"},
        tags = "@Smoke",
        monochrome = true,

        plugin = {"pretty",
                "html:target/html-reports.html", "json:target/jsonReports.json",

        })

public class TestRunner extends AbstractTestNGCucumberTests {

//    static {
//        System.setProperty("log4j.configurationFile", "log4j2.xml");
//    }

    @BeforeSuite
    public void SetupExtentReports() throws IOException {

        TestBase.reports = new ExtentReports();
        TestBase.reportName = System.getProperty("user.dir") + "/Reports/SolutionsExtentReport.html";
        TestBase.htmlreport = new ExtentHtmlReporter(new File(TestBase.reportName));
        TestBase.htmlreport.loadXMLConfig(String.valueOf(new File(System.getProperty("user.dir") + "/src/test/resources/RegressionTestCases/extent-config.xml")));
        TestBase.reports.setSystemInfo("BrowserName",System.getProperty("BrowserName"));
        TestBase.reports.setSystemInfo("Author", "Solutions");
        TestBase.reports.setSystemInfo("Executed by", System.getProperty("user.name"));
        TestBase.reports.setSystemInfo("Operating system", System.getProperty("os.name"));
        TestBase.reports.attachReporter(TestBase.htmlreport);


    }


}

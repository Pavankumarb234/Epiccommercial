package Solutions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class Hooks extends TestBase {
    @Before
    public void InitiateExtentReport(Scenario scenario) {
        System.out.println("Cucumber before");
        test = reports.createTest("Scenario: " + scenario.getName());

    }

    @After
    public void CloseExtentReports(Scenario scenario) {
        System.out.println("Cucumber After");
        if (scenario.getStatus().toString().equalsIgnoreCase("PASSED")) {
            test.info("Test case completed");
        } else if (scenario.getStatus().toString().equalsIgnoreCase("FAILED")) {
//            TakesScreenshot tss = (TakesScreenshot) driver;
//            File file = tss.getScreenshotAs(OutputType.FILE);
//            try {
//                FileUtils.copyFile(file, new File("C:\\Users\\PavanKumarBi_2vwp2tq\\IdeaProjects\\Solutions\\src\\test\\java\\Ss"
//                        + scenario.getName() + ".png"));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            test.addScreenCaptureFromPath("C:\\Users\\PavanKumarBi_2vwp2tq\\IdeaProjects\\Solutions\\src\\test\\java\\Ss");
            test.info("Test case failed");
        }
        reports.flush();


    }

}

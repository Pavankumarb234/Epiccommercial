//package Datafiles;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
//import io.cucumber.testng.AbstractTestNGCucumberTests;
//import io.cucumber.testng.CucumberOptions;
//import libraries.ConfigReader;
//import libraries.ReadEmails;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeSuite;
//import stepDefinations.TestBase;
//
//import java.io.File;
//import java.io.IOException;
//import java.sql.SQLException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//
//
//@CucumberOptions
//        (features = "src/test/resources/features/E2EScenarios.feature",
//                plugin = {"pretty", "html:target/cucumber-html-report.html"
//                        ,"html:target/cucumber_reports/cucumber_pretty.html"
//                        ,"json:target/cucumber_reports/cucumberTestReport.json"
////                        ,"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter"
//                },
//                glue = {"stepDefinations"},
//
//                tags="@Smoke")
//
//public class TestRunner extends AbstractTestNGCucumberTests {
//    static SimpleDateFormat format = null;
//    static Calendar cal = null;
//    String reportTime;
//    public Object[][] data;
//
//    static {
//        System.setProperty("log4j.configurationFile", "log4j2.xml");
//    }
//
////    @AfterMethod
//    public void closeBrowser() throws SQLException {
//        if (TestBase.driver == null) {
//        } else {
//            TestBase.driver.quit();
//        }
//    }
//
//
//    @BeforeSuite
//    public void setUp() throws IOException {
//        String tempBrowser = System.getProperty("Browser");
//        TestBase.browser = ConfigReader.getConfigValue("Browser");
//        TestBase.expWait = Integer.parseInt(ConfigReader.getConfigValue("WaitTime"));
//        if(tempBrowser != null && !tempBrowser.isEmpty()){
//            TestBase.browser = tempBrowser;
//        }
//        System.out.println("Browser#"+TestBase.browser);
//
//        String tempEnv = System.getProperty("env");
//        TestBase.env = ConfigReader.getConfigValue("Env");
//        if (tempEnv != null && !tempEnv.isEmpty()) {
//            TestBase.env = tempEnv;
//        }
//        System.out.println("Environment#"+TestBase.env);
//
//        String tempSendMail = System.getProperty("sendmail");
//        TestBase.sendmail = ConfigReader.getConfigValue("sendmail");
//        if (tempSendMail != null && !tempSendMail.isEmpty()) {
//            TestBase.sendmail = tempSendMail;
//        }
//        System.out.println("SendMail#"+TestBase.sendmail);
//
//        String temprecipients = System.getProperty("recipients");
//        TestBase.recipients = ConfigReader.getConfigValue("recipients");
//        if (temprecipients != null && !temprecipients.isEmpty()) {
//            TestBase.recipients = temprecipients;
//        }
//        System.out.println("Recipients#"+TestBase.recipients);
//
//        String tempUserName = System.getProperty("username");
//        TestBase.username = ConfigReader.getConfigValue("UserName_" + TestBase.env.toUpperCase());
//        if (tempUserName != null && !tempUserName.isEmpty()) {
//            TestBase.username = tempUserName;
//        }
//        System.out.println("Username#"+TestBase.username);
//
//        String tempPassword = System.getProperty("password");
//        TestBase.password = ConfigReader.getConfigValue("Password_" + TestBase.env.toUpperCase());
//        if (tempPassword != null && !tempPassword.isEmpty()) {
//            TestBase.password = tempPassword;
//        }
//
//        setUpExtentReports();
//    }
//
//    private void setUpExtentReports() throws IOException {
//        cal = Calendar.getInstance();
//        format = new SimpleDateFormat("dd_MMM_yyyy_hh_mm_ss");
//        TestBase.reports = new ExtentReports();
//        SimpleDateFormat formatt = new SimpleDateFormat("ddMMMyyyy_HH-mm");
//        reportTime = formatt.format(cal.getTime());
//        TestBase.reportName = System.getProperty("user.dir") + "/Reports/Unqork_Extent_Report.html";
//        TestBase.htmlReporter = new ExtentHtmlReporter(new File(TestBase.reportName));
//        TestBase.htmlReporter.loadXMLConfig(String.valueOf(new File(System.getProperty("user.dir") + "/src/test/resources/extent-config.xml")));
//        TestBase.reports.setSystemInfo("Environment",   TestBase.env);
//        TestBase.reports.setSystemInfo("Browser",   TestBase.browser);
//        TestBase.reports.setSystemInfo("Author", "Vijay Bompally");
//        TestBase.reports.setSystemInfo("Executed By", System.getProperty("user.name"));
//        TestBase.reports.setSystemInfo("Operating System", System.getProperty("os.name"));
//        TestBase.reports.attachReporter( TestBase.htmlReporter);
//    }
//
//    @BeforeClass
//    public static void setup() throws IOException {
//
//    }
//
////    @AfterSuite
//    public void teardown() {
//        try {
//            if(TestBase.sendmail.equalsIgnoreCase("yes")) {
//                ReadEmails.sendReportMail(TestBase.reportName);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
package Solutions;

import Lib.ConfigReader;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;



public class TestBase   {


    public static WebDriver driver;
    public Logger logger = LogManager.getLogger(TestBase.class);
    public static ExtentReports reports;
    public static ExtentTest test;
    public static ExtentHtmlReporter htmlreport;
    public static String reportName = null;


    public void launchbrowser() {

        if (ConfigReader.ipf("BrowserType").equalsIgnoreCase("chrome")) {
            logger.info("hello");
            WebDriverManager.chromedriver().setup();
            ChromeOptions Options = new ChromeOptions();
            Options.addArguments("--Start-Maximized");
            driver = new ChromeDriver(Options);
            logger.info("driver opened");

        }
        else if (ConfigReader.ipf("BrowserType").equalsIgnoreCase("fireFox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        else{
            System.out.println("No driver found");
        }
    }
    public void NavigateTourl(){
        driver.navigate().to(ConfigReader.ipf("url"));
        driver.manage().window().fullscreen();
        driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
    }

    public void log4j2demo() {
        logger.info("information");
        logger.trace("tracing");
        logger.debug("debug");
        logger.error("Error");
        logger.warn("Warning");
        logger.fatal("harmful");
    }

    public void closebrowser() {
        driver.close();
        driver.quit();
    }

    public void clickingOnWebElement(WebElement element, long waitTimeInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver,TimeUnit.SECONDS.toSeconds(waitTimeInSeconds));
        WebElement elements = null;
        elements = wait.until(ExpectedConditions.elementToBeClickable(element));
        elements.click();
    }

    public WebElement WaitForElement(WebElement elementName, long waitTimeInseconds) {
        WebDriverWait wait = new WebDriverWait(driver,  TimeUnit.SECONDS.toSeconds(waitTimeInseconds));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(elementName));
        return element;
    }

    public void SendkeysWebElement(WebElement element, String text) {
        WaitForElement(element, 10);
        element.clear();
        element.click();
        element.sendKeys(text);
    }
//    toolqa
    public void sendKeysUisngByElement(By element, String text) throws InterruptedException {
        WebElement byelement = driver.findElement(element);
         highlightelement(driver, byelement);
        byelement.sendKeys(text);
        Thread.sleep(3000);
    }
    public void clickOnelement(By element){
        WebElement byelement=driver.findElement(element);
        byelement.click();
    }
    public void highlightelement(WebDriver driver,WebElement element){
        JavascriptExecutor js =(JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style','boder:2px solid red;background:pink')",element);
    }




    public void SelectDropdown(WebElement element, String text) {
        try {
            Select select = new Select(element);
            select.selectByVisibleText(text);
            logger.info("text '" + text + "' is selected from the dropdown '" + element + "' ");
        } catch (Exception e) {
            logger.info(" '" + element + "' is not present or '" + text + "' text is not selected");
        }
    }

    public void SendkeysJavascriptExecutor(WebElement element, String text) {
        try {
            WebElement ele = WaitForElement(element, 20);
            JavascriptExecutor js = ((JavascriptExecutor) driver);
            js.executeScript("arguments[0].value '" + text + "';", ele);
            logger.info(" '" + text + "' is entered at '" + element + "' ");
        } catch (Exception e) {
            logger.info(" '" + text + "' is not entered at '" + element + "' ");

        }
    }

    public void AlertAcceptAction(WebDriver driver) {
        driver.switchTo().alert().accept();
    }

    public void AlertDismissAction(WebDriver driver) {
        driver.switchTo().alert().dismiss();
    }

    public void AlertSendkeys(WebDriver driver, String text) {
        driver.switchTo().alert().sendKeys(text);
    }

    public void ClickOnWebElement(WebElement element) {
        element.clear();
        element.click();
    }

    public void MouseHoverOnWebelement(WebElement element) {
        Actions act = new Actions(driver);
        act.moveToElement(element).build().perform();
    }

    public void DragAndDrop(WebElement element1, WebElement element2) {
        Actions act = new Actions(driver);
        act.dragAndDrop(element1, element2).build().perform();
    }

    public void clickAndhold(WebElement element) {
        Actions act = new Actions(driver);
        act.clickAndHold(element).build().perform();
    }

    public void release(WebElement element) {
        Actions act = new Actions(driver);
        act.release(element).build().perform();
    }

}

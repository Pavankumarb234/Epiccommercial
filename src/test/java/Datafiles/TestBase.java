//package Datafiles;
//
//import Lib.ConfigReader;
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.markuputils.CodeLanguage;
//import com.aventstack.extentreports.markuputils.MarkupHelper;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
//import com.google.gson.Gson;
//import io.github.bonigarcia.wdm.WebDriverManager;
//import io.restassured.RestAssured;
//import io.restassured.authentication.PreemptiveBasicAuthScheme;
//import io.restassured.builder.RequestSpecBuilder;
//import io.restassured.builder.ResponseSpecBuilder;
//import io.restassured.path.json.JsonPath;
//import io.restassured.response.Response;
//import io.restassured.specification.RequestSpecification;
//import io.restassured.specification.ResponseSpecification;
//import libraries.ConfigReader;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.openqa.selenium.*;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxOptions;
//import org.openqa.selenium.firefox.FirefoxProfile;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.awt.*;
//import java.awt.datatransfer.StringSelection;
//import java.io.File;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static io.restassured.RestAssured.given;
//
//public class TestBase {
//    public static String JSESSIONID;
//    public static String Authorization;
//    public static Map<String, String> world = new HashMap<>();
//    public static Map<String, List<String>> worldList = new HashMap<>();
//    public static String sendmail;
//    public static String recipients;
//    public RequestSpecification req;
//    public ResponseSpecification resSpec;
//    public static Response response;
//    public static String env;
//    public Logger logger = LogManager.getLogger(TestBase.class);
//    public static WebDriver driver;
//    public static int expWait;
//    public static String browser;
//    public static ExtentReports reports;
//    public static ExtentTest test;
//    public static ExtentHtmlReporter htmlReporter;
//    public static String reportName = null;
//    private static String attachID;
//    private static String attachDate;
//    public static String username;
//    public static String password;
//
//    protected void openBrowser() {
//        try {
//            logger.info("Browser selected#" + browser);
//            logger.info("Operating System#" + System.getProperty("os.name"));
//            if (browser.equalsIgnoreCase("chrome")) {
//                LaunchChromeBrowser();
//            } else if (browser.equalsIgnoreCase("firefox")) {
//                LaunchFirefox();
//            }
//            else if (browser.equalsIgnoreCase("edge")) {
//                LaunchEdge();
//            }
//            driver.manage().window().maximize();
//            driver.manage().deleteAllCookies();
//        } catch (Exception e) {
//            logger.error("Browser failed to opened");
//        }
//    }
//
//    protected void LaunchChromeBrowser() {
//        try {
////            Runtime.getRuntime().exec("taskkill /F /IM ChromeDriver.exe");
////            Runtime.getRuntime().exec("taskkill /f /im operadriver.exe");
////            Runtime.getRuntime().exec("taskkill /f /im geckodriver.exe");
////            Runtime.getRuntime().exec("taskkill /f /im IEDriverServer.exe");
//
//            System.setProperty("webdriver.chrome.silentOutput", "true");
//            WebDriverManager.chromedriver().setup();
//            ChromeOptions chromeOptions = new ChromeOptions();
//            chromeOptions.addArguments("--start-maximized");
//            chromeOptions.addArguments("--no-sandbox");
//            chromeOptions.addArguments("--disable-dev-shm-usage");
//            chromeOptions.addArguments("--window-size=1920x1080");
//            chromeOptions.addArguments("--ignore-ssl-errors=yes");
//            chromeOptions.addArguments("--ignore-certificate-errors");
//
//            if (System.getProperty("os.name").equalsIgnoreCase("linux")) {
//                chromeOptions.addArguments("--headless");
//            }
//            chromeOptions.setExperimentalOption("useAutomationExtension", false);
//            driver = new ChromeDriver(chromeOptions);
//            logger.info("Chrome Browser started");
//        } catch (Exception e) {
//            logger.info(e);
//        }
//    }
//
//    private void LaunchFirefox() {
//        WebDriverManager.firefoxdriver().setup();
//        FirefoxProfile profile = new FirefoxProfile();
//        profile.setPreference("network.proxy.no_proxies_on", "localhost");
//        profile.setPreference("javascript.enabled", true);
//        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
//        capabilities.setCapability("marionette", true);
//        capabilities.setCapability(FirefoxDriver.PROFILE, profile);
//        FirefoxOptions options = new FirefoxOptions();
//        options.merge(capabilities);
//        driver = new FirefoxDriver(options);
//        logger.info("Firefox Browser started");
//    }
//
//
//    public void LaunchEdge() {
//        WebDriverManager.edgedriver().setup();
//        driver = new EdgeDriver();
//        logger.info("Edge Browser started");
//    }
//
//    public void LaunchTHEdge() {
//        WebDriverManager.edgedriver().setup();
//        driver = new EdgeDriver();
//        logger.info("Edge Browser started");
//    }
//
//    public boolean navigatetoLoginURL() {
//        try {
//            driver.get(ConfigReader.getConfigValue("UIBaseURL_" + env.toUpperCase()));
//            logger.info("Loading:\t url");
//        } catch (Exception e) {
//            logger.error("Unable to navigate to URL");
//        }
//        return driver.getCurrentUrl().length() > 0;
//    }
//
//    public boolean navigatetoTHLoginURL() {
//        try {
//            driver.get(ConfigReader.getConfigValue("TestHarnessURL"));
//            logger.info("Loading:\t url");
//        } catch (Exception e) {
//            logger.error("Unable to navigate to URL");
//        }
//        return driver.getCurrentUrl().length() > 0;
//    }
//
//    public boolean sendKeys(By element, Keys text) {
//        try {
//            driver.findElement(element).sendKeys(text);
//        } catch (Exception e) {
//            logger.error("Element Not clicked " + e);
//        }
//        return true;
//    }
//
//    public boolean sendKeys(By element, String text) {
//        try {
//            driver.findElement(element).clear();
//            toHighlight(element);
//            logger.info(text + " entered in element: " + element);
//            driver.findElement(element).sendKeys(text);
//        } catch (Exception e) {
//            logger.error("Element Not clicked " + e);
//        }
//        return true;
//    }
//
//    public boolean sendKeys(WebElement element, String text) {
//        try {
//            element.clear();
//            logger.info(text + " entered in element: " + element);
//            element.sendKeys(text);
//        } catch (Exception e) {
//            logger.error("Element Not clicked " + e);
//        }
//        return true;
//    }
//
//    public boolean sendKeys(WebElement element, char text) {
//        try {
//            element.clear();
//            logger.info(text + " entered in element: " + element);
//            String s = new StringBuilder().append(text).toString();
//            element.sendKeys(s);
//        } catch (Exception e) {
//            logger.error("Element Not clicked " + e);
//        }
//        return true;
//    }
//
//    public boolean clearText(By element) {
//        try {
//            WebElement el = driver.findElement(element);
//            if (el.getTagName().equalsIgnoreCase("input") && el.getAttribute("type").equalsIgnoreCase("text")) {
//                driver.findElement(element).clear();
//            }
//
//        } catch (Exception e) {
//            logger.error(e);
//        }
//        return true;
//    }
//
//    protected boolean waitForStatus(By ele, String taSTatus) {
//        try {
//            WebDriverWait wait = new WebDriverWait(driver, 20);
//            return wait.until(ExpectedConditions.textToBePresentInElementLocated(ele, taSTatus));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public boolean actionClick(By element) {
//        try {
//            WebElement webElement = driver.findElement(element);
//            Actions action = new Actions(driver);
//            action.moveToElement(webElement).click().perform();
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    public void actionClick(WebElement element) {
//        Actions action = new Actions(driver);
//        action.moveToElement(element).click().build().perform();
//    }
//
//    public boolean click(By element) throws Exception {
//        try {
//            logger.info("Clicking object using By element : " + element);
//            try {
//                waitUntilElementIsClickable(element, expWait);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            logger.info("Element is clickable with condition Implict wait");
//            WebElement webElement = driver.findElement(element);
//            if (webElement.isEnabled() && webElement.isDisplayed()) {
//                logger.info("Element is enabled or displayed in page");
//                toHighlight(element);
//                webElement.click();
//                return true;
//            } else {
//                logger.error("Element is not enabled or displayed for click, will try javascript click next.");
//                return clickElementUsingJavaScript(element);
//            }
//        } catch (ElementNotInteractableException e) {
//            logger.error("Element not interactable during click " + e.getMessage());
//
//            return clickElementUsingJavaScript(element);
//        } catch (WebDriverException e) {
//            e.printStackTrace();
//            logger.error("WebDriver exception during click " + e.getMessage());
//            if (e.getMessage().contains("Missing Template ERR_CONNECT_FAIL")) {
//                new WebDriverWait(driver, expWait)
//                        .until(ExpectedConditions.elementToBeClickable(element)).click();
//                return true;
//            } else
//                return clickElementUsingJavaScript(element);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return clickElementUsingJavaScript(element);
//        }
//    }
//
//    public boolean moveToElement(By element) throws Exception {
//        try {
//            logger.info("Moving to element : " + element);
//            try {
//                waitUntilElementIsClickable(element, expWait);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            logger.info("Element is clickable with condition Implict wait");
//            WebElement webElement = driver.findElement(element);
//            if (webElement.isEnabled() && webElement.isDisplayed()) {
//                logger.info("Element is enabled or displayed in page");
//                toHighlight(element);
//                Actions action = new Actions(driver);
//                action.moveToElement(webElement).perform();
//
//                return true;
//            }
//        } catch (ElementNotInteractableException e) {
//            logger.error("Element not interactable during move " + e.getMessage());
//        }
//        return false;
//    }
//
//    public boolean click(WebElement webElement) {
//        try {
//            logger.info("Clicking object using By element : " + webElement);
//            try {
//                waitUntilElementIsClickable(webElement, expWait);
//            } catch (Exception e) {
//
//            }
//            logger.info("Element is clickable with condition Implict wait");
//
//            if (webElement.isEnabled() && webElement.isDisplayed()) {
//                logger.info("Element is enabled or displayed in page");
//                webElement.click();
//                return true;
//            }
//        } catch (WebDriverException e) {
//            e.printStackTrace();
//            logger.error("WebDriver exception during click " + e.getMessage());
//            if (e.getMessage().contains("Missing Template ERR_CONNECT_FAIL")) {
//                new WebDriverWait(driver, expWait)
//                        .until(ExpectedConditions.elementToBeClickable(webElement)).click();
//                return true;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//        return false;
//    }
//
//    public boolean waitUntilElementIsClickable(By locator, long seconds) {
//        WebElement element = null;
//        try {
//            new WebDriverWait(driver, seconds).until(ExpectedConditions.elementToBeClickable(locator));
//        } catch (Exception e) {
//            logger.info("Failed to wait for element to be clickable");
//            throw e;
//        }
//        return true;
//    }
//
//    public boolean waitUntilElementIsInvisible(By locator, long seconds) {
//        WebElement element = null;
//        try {
//            new WebDriverWait(driver, seconds).until(ExpectedConditions.invisibilityOfElementLocated(locator));
//        } catch (Exception e) {
//            logger.info("Failed to wait for element to be invisible");
//            throw e;
//        }
//        return true;
//    }
//
//    public boolean waitUntilElementIsClickable(WebElement element, long seconds) throws Exception {
//        try {
//            new WebDriverWait(driver, seconds).until(ExpectedConditions.elementToBeClickable(element));
//        } catch (Exception e) {
//            logger.info("Failed to wait for element to be clickable");
//            throw e;
//        }
//        return true;
//    }
//
//    public boolean clickElementUsingJavaScript(By locator) throws Exception {
//        JavascriptExecutor jse = (JavascriptExecutor) driver;
//        try {
//            try {
//                waitUntilElementIsClickable(locator, expWait);
//            } catch (Exception e) {
//                // do nothing, continue to try and click element
//            }
//            jse.executeScript("arguments[0].click();", driver.findElement(locator));
//
//            return true;
//        } catch (TimeoutException e) {
//            throw new Exception("Element " + locator.toString() + " was not found\n" + e.getMessage(), e);
//        } catch (WebDriverException e) {
//            if (e.getMessage().contains("JavaScript error")) {
//                logger.warn("Skipping exception with JavaScript error");
//            } else if (!e.getMessage().contains("Missing Template ERR_CONNECT_FAIL")) {
//                logger.info("Failed to click: " + locator + " by javascript. Retrying..");
//                jse.executeScript("arguments[0].click();",
//                        new WebDriverWait(driver, expWait)
//                                .until(ExpectedConditions.elementToBeClickable(locator)));
//                return true;
//            } else
//                throw new Exception("Web driver exception clicking element with javascript " + locator.toString() + "\n"
//                        + e.getMessage());
//        } catch (Exception e) {
//        }
//        return false;
//    }
//
//    public boolean clickElementUsingJavaScript(WebElement locator) throws Exception {
//        JavascriptExecutor jse = (JavascriptExecutor) driver;
//        try {
//            try {
//                waitUntilElementIsClickable(locator, expWait);
//            } catch (Exception e) {
//                // do nothing, continue to try and click element
//            }
//            jse.executeScript("arguments[0].click();", locator);
//
//            return true;
//        } catch (TimeoutException e) {
//            throw new Exception("Element " + locator.toString() + " was not found\n" + e.getMessage(), e);
//        } catch (WebDriverException e) {
//            if (e.getMessage().contains("JavaScript error")) {
//                logger.warn("Skipping exception with JavaScript error");
//            } else if (!e.getMessage().contains("Missing Template ERR_CONNECT_FAIL")) {
//                logger.info("Failed to click: " + locator + " by javascript. Retrying..");
//                jse.executeScript("arguments[0].click();",
//                        new WebDriverWait(driver, expWait)
//                                .until(ExpectedConditions.elementToBeClickable(locator)));
//                return true;
//            } else
//                throw new Exception("Web driver exception clicking element with javascript " + locator.toString() + "\n"
//                        + e.getMessage());
//        } catch (Exception e) {
//        }
//        return false;
//    }
//
//    public boolean isElementCurrentlyDisplayed(By element) throws Exception {
//        boolean isDisplayed = false;
//        List<WebElement> elementList = driver.findElements(element);
//        if (elementList.size() <= 0) {
//            return false;
//        } else if (elementList.size() > 1) {
//            throw new Exception("Error: Found multiple elements");
//        } else {
//            WebElement foundElement = elementList.get(0);
//            if (foundElement.isDisplayed()) {
//                isDisplayed = true;
//            }
//            return isDisplayed;
//        }
//    }
//
//    public String getTextFromElement(By element) throws Exception {
//        try {
//            logger.info("Getting text from element : " + element + "");
//            String innerText = new WebDriverWait(driver, expWait)
//                    .until(ExpectedConditions.visibilityOfElementLocated(element)).getText().trim();
//            logger.info("The Inner Text Of An Element is : " + innerText);
//            return innerText;
//        } catch (StaleElementReferenceException e) {
//            return new WebDriverWait(driver, expWait)
//                    .until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(element)))
//                    .getText();
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.info(e.getMessage());
//            if (e.getMessage().contains("Missing Template ERR_CONNECT_FAIL"))
//                return new WebDriverWait(driver, expWait)
//                        .until(ExpectedConditions.visibilityOfElementLocated(element)).getText();
//            else
//                throw new Exception(e);
//        }
//    }
//
//    public String getTextFromElements(WebElement element) throws Exception {
//        try {
//            logger.info("Getting text from element : " + element + "");
//            String innerText = new WebDriverWait(driver, expWait)
//                    .until(ExpectedConditions.visibilityOf(element)).getText().trim();
//            logger.info("The Inner Text Of An Element is : " + innerText);
//            return innerText;
//        } catch (StaleElementReferenceException e) {
//            return new WebDriverWait(driver, expWait)
//                    .until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)))
//                    .getText();
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.info(e.getMessage());
//            if (e.getMessage().contains("Missing Template ERR_CONNECT_FAIL"))
//                return new WebDriverWait(driver, expWait)
//                        .until(ExpectedConditions.visibilityOf(element)).getText();
//            else
//                throw new Exception(e);
//        }
//    }
//
//    public String getAttributeValueFromElement(By element, String attribute) throws Exception {
//        try {
//            logger.info("Getting text from element : " + element + "");
//            String innerText = new WebDriverWait(driver, expWait)
//                    .until(ExpectedConditions.visibilityOfElementLocated(element)).getAttribute(attribute).trim();
//            logger.info("The attribute value of an Element is : " + innerText);
//            return innerText;
//        } catch (StaleElementReferenceException e) {
//            return new WebDriverWait(driver, expWait)
//                    .until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(element)))
//                    .getAttribute(attribute);
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.info(e.getMessage());
//            if (e.getMessage().contains("Missing Template ERR_CONNECT_FAIL"))
//                return new WebDriverWait(driver, expWait)
//                        .until(ExpectedConditions.visibilityOfElementLocated(element)).getAttribute(attribute);
//            else
//                throw new Exception(e);
//        }
//    }
//
//    public String getAttributeValueFromElement(WebElement element, String attribute) throws Exception {
//        try {
//            logger.info("Getting text from element : " + element + "");
//            String innerText = new WebDriverWait(driver, expWait)
//                    .until(ExpectedConditions.visibilityOf(element)).getAttribute(attribute).trim();
//            logger.info("The attribute value of an Element is : " + innerText);
//            return innerText;
//        } catch (StaleElementReferenceException e) {
//            return new WebDriverWait(driver, expWait)
//                    .until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)))
//                    .getAttribute(attribute);
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.info(e.getMessage());
//            if (e.getMessage().contains("Missing Template ERR_CONNECT_FAIL"))
//                return new WebDriverWait(driver, expWait)
//                        .until(ExpectedConditions.visibilityOf(element)).getAttribute(attribute);
//            else
//                throw new Exception(e);
//        }
//    }
//
//    public String getAttributeValueFromElements(String attribute, WebElement element) throws Exception {
//        try {
//            logger.info("Getting text from element : " + element + "");
//            String innerText = new WebDriverWait(driver, expWait)
//                    .until(ExpectedConditions.visibilityOf(element)).getAttribute(attribute).trim();
//            logger.info("The attribute value of an Element is : " + innerText);
//            return innerText;
//        } catch (StaleElementReferenceException e) {
//            return new WebDriverWait(driver, expWait)
//                    .until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)))
//                    .getAttribute(attribute);
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.info(e.getMessage());
//            if (e.getMessage().contains("Missing Template ERR_CONNECT_FAIL"))
//                return new WebDriverWait(driver, expWait)
//                        .until(ExpectedConditions.visibilityOf(element)).getAttribute(attribute);
//            else
//                throw new Exception(e);
//        }
//    }
//
//    public boolean isElementEnabled(By element) {
//        WebElement ele = driver.findElement(element);
//        if (ele.isEnabled()) {
//            logger.info("Element enabled");
//            return true;
//        } else
//            return false;
//    }
//
//    public boolean isElementSelected(By element) throws Exception {
//        WebElement ele = driver.findElement(element);
//        if (ele.isSelected()) {
//            logger.info("Element enabled");
//            return true;
//        } else
//            return false;
//    }
//
//    public boolean waitForElementToDisplay(By locator, long maxSecondsToWait) {
//        for (int i = 0; i < maxSecondsToWait; i++) {
//            try {
//                Thread.sleep(1000);
//                if (isElementCurrentlyDisplayed(locator)) {
//                    return true;
//                }
//            } catch (Exception e) {
//                // do nothing, let it keep looping to wait for object
//            }
//        }
//        return false;
//    }
//
//    public boolean waitForElementToDisplay(WebElement locator, long maxSecondsToWait) {
//        for (int i = 0; i < maxSecondsToWait; i++) {
//            try {
//                Thread.sleep(1000);
//                if (locator.isDisplayed()) {
//                    return true;
//                }
//            } catch (Exception e) {
//                // do nothing, let it keep looping to wait for object
//            }
//        }
//        return false;
//    }
//
//    public void pause(long ms) {
//        try {
//            Thread.sleep(ms);
//        } catch (InterruptedException ie) {
//        }
//    }
//
//    private void toHighlight(By element) {
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("arguments[0].setAttribute('style','background:yellow;border: 2px solid red;');", driver.findElement(element));
//    }
//
//    private void toRemoveHighlight(By element) {
//        try {
//            JavascriptExecutor js = (JavascriptExecutor) driver;
//            js.executeScript("arguments[0].setAttribute('style','background:nill;border: nill;');", driver.findElement(element));
//        } catch (StaleElementReferenceException e) {
//            e.printStackTrace();
//        }
//    }
//
//    protected boolean selectByVisibleText(By element, String str) {
//        try {
//            waitForElementToDisplay(element,20);
//            WebElement ele = driver.findElement(element);
//            Actions action = new Actions(driver);
//            action.moveToElement(ele).perform();
//            Select select = new Select(ele);
//            select.selectByVisibleText(str);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    protected boolean selectByIndex(By element, int index) {
//        try {
//            waitForElementToDisplay(element,10);
//            WebElement ele = driver.findElement(element);
//            Select select = new Select(ele);
//            select.selectByIndex(index);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    public static boolean isAlertPresent() {
//        try {
//            driver.switchTo().alert();
//            return true;
//        } catch (NoAlertPresentException ex) {
//            return false;
//        }
//    }
//
//    protected boolean isAttribtuePresent(WebElement element, String attribute) {
//        Boolean result = false;
//        try {
//            String value = element.getAttribute(attribute);
//            if (value != null) {
//                result = true;
//            }
//        } catch (Exception e) {
//        }
//        return result;
//    }
//
//    protected boolean isAttribtuePresent(By ele, String attribute) {
//        Boolean result = false;
//        try {
//            WebElement element = driver.findElement(ele);
//            String value = element.getAttribute(attribute);
//            if (value != null) {
//                result = true;
//            }
//        } catch (Exception e) {
//        }
//        return result;
//    }
//
//    public void setClipboardData(String string) {
//        StringSelection stringSelection = new StringSelection(string);
//        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
//    }
//
//    public RequestSpecification requestSpecificationAdmin(String user, String password) throws IOException {
//         RestAssured.baseURI = ConfigReader.getConfigValue("APIBaseURL_" + env.toUpperCase());
//        test.info("BaseURI: " + RestAssured.baseURI);
//        PreemptiveBasicAuthScheme auth = new PreemptiveBasicAuthScheme();
//        auth.setUserName(user);
//        auth.setPassword(password);
//        req = new RequestSpecBuilder().setAuth(auth)
//                .addHeader("Authorization", ConfigReader.getConfigValue("Authorization"))
//                .build();
//        return req;
//    }
//
//    public RequestSpecification requestSpecificationUnauthorizedAdmin() throws IOException {
//        String tempEnv = System.getProperty("env");
//        env = ConfigReader.getConfigValue("Env");
//        if (tempEnv != null && !tempEnv.isEmpty()) {
//            env = tempEnv;
//        }
//        RestAssured.baseURI = ConfigReader.getConfigValue("APIBaseURL_" + env.toUpperCase());
//        PreemptiveBasicAuthScheme auth = new PreemptiveBasicAuthScheme();
//        auth.setUserName(ConfigReader.getConfigValue("UserNameUnAuthorized_" + env.toUpperCase()));
//        auth.setPassword(ConfigReader.getConfigValue("PasswordUnAuthorized_" + env.toUpperCase()));
//        req = new RequestSpecBuilder()
//                .setAuth(auth)
//                .addHeader("Authorization", ConfigReader.getConfigValue("Authorization"))
//                .build();
//        return req;
//    }
//
//
//    public RequestSpecification requestSpecificationLogin(String loginId, String password) throws IOException {
//        String tempEnv = System.getProperty("env");
//        env = ConfigReader.getConfigValue("Env");
//        if (tempEnv != null && !tempEnv.isEmpty()) {
//            env = tempEnv;
//        }
//
//        RestAssured.baseURI = ConfigReader.getConfigValue("APIBaseURL_" + env.toUpperCase());
//        PreemptiveBasicAuthScheme auth = new PreemptiveBasicAuthScheme();
//        auth.setUserName(loginId);
//        auth.setPassword(password);
//        req = new RequestSpecBuilder()
//                .setAuth(auth)
//                .addHeader("Authorization", ConfigReader.getConfigValue("Authorization"))
//                .build();
//        return req;
//    }
//
//
//    public RequestSpecification requestSpecificationGLRC() {
//        req = new RequestSpecBuilder()
//                .addHeader("JSESSIONID", JSESSIONID)
//                .addHeader("Authorization", Authorization)
//                .addHeader("Content-Type", "application/json")
//                .addHeader("Accept", "application/json")
//                .build();
//        return req;
//    }
//
//    public RequestSpecification requestSpecificationGLRCXML() {
//        req = new RequestSpecBuilder()
//                .addHeader("JSESSIONID", JSESSIONID)
//                .addHeader("Authorization", Authorization)
//                .addHeader("Content-Type", "application/json")
//                .addHeader("Accept", "application/xml")
//                .build();
//        return req;
//    }
//
//
//    public RequestSpecification requestSpecificationAttach() {
//        req = new RequestSpecBuilder()
//                .addHeader("JSESSIONID", JSESSIONID)
//                .addHeader("Authorization", Authorization)
//                .build();
//        return req;
//    }
//
//    public RequestSpecification requestSpecificationAttachment() {
//        req = new RequestSpecBuilder()
//                .addHeader("Accept", "application/json")
//                .addHeader("JSESSIONID", JSESSIONID)
//                .addHeader("Authorization", Authorization)
//                .build();
//        return req;
//    }
//
//    public RequestSpecification requestSpecificationUser() {
//        req = new RequestSpecBuilder()
//                .addHeader("Accept", "application/json")
//                .addHeader("Content-Type", "application/json")
//                .build();
//        return req;
//    }
//
//    public String getJsonPath(Response response, String key) {
//        String resp = response.asString();
//        JsonPath js = new JsonPath(resp);
//        logger.info("json key: " + key + " | json value: " + js.get(key).toString());
//        return js.get(key).toString();
//    }
//
//    public String getJsonArrayPath(Response response, String key, int index) {
//        String resp = response.asString();
//        JsonPath js = new JsonPath(resp);
//        logger.info("json key: " + key + " | json value: " + js.getList(key).get(index).toString());
//        return js.getList(key).get(index).toString();
//    }
//
//    public Response sendGetRequestSpecGLRC(String resource) {
//        resSpec = new ResponseSpecBuilder().build();
//        response = given().log().all().spec(requestSpecificationGLRC()).urlEncodingEnabled(false)
//                .when().get(resource)
//                .then().log().all().spec(resSpec).extract().response();
//        world.put("statusCode", "" + response.getStatusCode());
//        return response;
//    }
//
//    public Response sendGetRequestSpecGLRCnologs(String resource) {
//        resSpec = new ResponseSpecBuilder().build();
//        response = given().spec(requestSpecificationGLRC()).urlEncodingEnabled(false)
//                .when().get(resource)
//                .then().spec(resSpec).extract().response();
//        world.put("statusCode", "" + response.getStatusCode());
//        return response;
//    }
//
//    public Response sendDeleteRequestSpecGLRC(String resource) {
//        resSpec = new ResponseSpecBuilder().build();
//        response = given().log().all().spec(requestSpecificationGLRC())
//                .when().delete(resource)
//                .then().log().all().spec(resSpec).extract().response();
//        world.put("statusCode", "" + response.statusCode());
//        return response;
//    }
//
//    public Response sendPostRequestBulkSpecGLRC(String resource, String fil) {
//        File file = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "bulkdata" +
//                System.getProperty("file.separator") + fil);
//        resSpec = new ResponseSpecBuilder().build();
//        response = given().log().all()
//                .header("content-type", "multipart/form-data")
//                .header("JSESSIONID", JSESSIONID)
//                .header("Authorization", Authorization)
//                .multiPart("file", file, "application/json")
//                .when().post(resource)
//                .then().spec(resSpec).extract().response();
//        world.put("statusCode", "" + response.statusCode());
//        return response;
//    }
//
//    public Response sendPostRequestDocSpecGLRC(String resource, String fil) {
//        File file = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "testFiles" +
//                System.getProperty("file.separator") + fil);
//        resSpec = new ResponseSpecBuilder().build();
//        response = given().log().all()
//                .header("content-type", "multipart/form-data")
//                .header("JSESSIONID", JSESSIONID)
//                .header("Authorization", Authorization)
//                .multiPart("file", file, "application/json")
//                .multiPart("inpFormat", "PDF")
//                .multiPart("outFormat", "ELABEL")
//                .multiPart("responseType", "XML")
//                .multiPart("formName", "Adept")
//                .multiPart("formSource", "ASG")
//                .multiPart("customForm", "true")
//                .when().post(resource)
//                .then().log().all().spec(resSpec).extract().response();
//        world.put("statusCode", "" + response.statusCode());
//        return response;
//    }
//
//    public Response sendPostRequestDocSpecGLRCProcessDoc(String resource, String fil) {
//        File file = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "testFiles" +
//                System.getProperty("file.separator") + fil);
//        resSpec = new ResponseSpecBuilder().build();
//        response = given().log().all()
//                .header("content-type", "multipart/form-data")
//                .header("accept", "*/*")
//                .header("JSESSIONID", JSESSIONID)
//                .header("Authorization", Authorization)
//                .multiPart("file", file, "application/json")
//                .multiPart("inpFormat", "XLSX")
//                .multiPart("outFormat", "ELABEL")
//                .multiPart("responseType", "XML")
//                .multiPart("formName", "MReSBanorte")
//                .multiPart("formSource", "ASG")
//                .multiPart("customForm", "true")
//                .when().post(resource)
//                .then().log().all().spec(resSpec).extract().response();
//        world.put("statusCode", "" + response.statusCode());
//        attachID = response.getHeader("X-AdeptExistingAttachmentId");
//        attachDate = response.getHeader("X-AdeptLastKnownUploadTime");
//        if (!(attachID == null)) {
//            test.pass("Attachment ID for the process document '" + fil + "' is: " + attachID);
//            test.pass("Attachment Date for the process document'" + fil + "' is: " + attachDate);
//        } else {
//            test.pass("Attachment ID is not generated for the process document '" + fil + "' is: " + attachID);
//            test.pass("Attachment Date is not generated for the process document'" + fil + "' is: " + attachDate);
//        }
//
//        return response;
//    }
//
//    public Response sendPostRequestDocSpecGLRCProcessDocCont(String resource, String fil) throws InterruptedException {
//        resSpec = new ResponseSpecBuilder().build();
//        response = given().log().all()
//                .header("content-type", "application/x-www-form-urlencoded")
//                .header("accept", "application/json")
//                .header("JSESSIONID", JSESSIONID)
//                .header("Authorization", Authorization)
////                .multiPart("file", file, "application/json")
//                .param("inpFormat", "XLSX")
//                .param("outFormat", "ELABEL")
//                .param("responseType", "XML")
//                .param("formName", "MReSBanorte")
//                .param("formSource", "ASG")
//                .param("customForm", "true")
//                .param("useExistingDocument", "Y")
////                .param("contentType","application%2Fvnd.openxmlformats-officedocument.spreadsheetml.sheet")
//
//                .param("processDocumentResponse", getProcesDocReq(attachID, attachDate))
////                .param("attachmentLocation", "https://asg-adept-qa-us.s3.amazonaws.com/attachments/"+attachID+"/MReSBanorte - Max.xlsx")
////                .param("attachmentAcordUUID",attachID)
////                .param("name","MReSBanorte - Max.xlsx")
////                .param("attachmentUploadDate",attachDate)
////                .param("contentType","*/*")
////                .param("size","114461")
////                .param("checksum","6908acd5e77a6c00abf5d03a47127de5")
////                .param("duplicateCheckFailed","true")
////                .param("attachmentName","MReSBanorte - Max.xlsx")
////                .param("","")
////                .param("","")
//
//                .when().post(resource)
//                .then().log().all().spec(resSpec).extract().response();
//        world.put("statusCode", "" + response.statusCode());
//        System.out.println("Header1 #" + response.prettyPrint());
//        return response;
//    }
//
//    public String getProcesDocReq(String attach, String attachDate) {
//        String processDoc = "{\n" +
//                "  \"duplicateCheckFailed\": true,\n" +
//                "  \"attachmentClientVO\": {\n" +
//                "    \"attachmentId\": \"" + attach + "\",\n" +
//                "    \"attachmentName\": \"MReSBanorte - Max.xlsx\",\n" +
//                "    \"contentType\": \"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet\",\n" +
//                "    \"fileSize\": 114461,\n" +
//                "    \"checksum\": \"6908acd5e77a6c00abf5d03a47127de5\",\n" +
//                "    \"attachmentLocation\": \"https://asg-adept-qa-us.s3.amazonaws.com/attachments/" + attach + "/MReSBanorte - Max.xlsx\",\n" +
//                "    \"existingAttachmentId\": \"cd93749a-8108-4469-a94a-9e4edebe46ba\",\n" +
//                "    \"existingAttachmentName\": \"MReSBanorte - Max.xlsx\",\n" +
//                "    \"attachmentUploadDate\": \"" + attachDate + "\"\n" +
//                "  }\n" +
//                "}";
//
//        return processDoc;
//    }
//
//    public String convertToJson(String req) {
//        Gson gson = new Gson();
//        String json = gson.toJson(req);
//        test.info(MarkupHelper.createCodeBlock(json, CodeLanguage.JSON));
//        return json;
//    }
//
//    public void scrollIntoView(By ele){
//        waitForElementToDisplay(ele, 5);
//        WebElement element = driver.findElement(ele);
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element);
//    }
//
//    public void scrollIntoView(WebElement ele){
//        waitForElementToDisplay(ele, 2);
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",ele);
//    }
//}
package Solutions;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.List;

public class Prac {
    public static WebDriver driver = null;

    @Test
    public void one() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.get("https://www.naukri.com/mnjuser/homepage");
        Thread.sleep(4000);

        WebElement username = driver.findElement(By.xpath("//input[@placeholder='Enter Email ID / Username']"));
        username.click();
        username.sendKeys("pavankumarbingi36@gmail.com");

        WebElement usernamet = driver.findElement(By.xpath("//input[@id='usernameField']"));
        WebElement getTextele = driver.findElement(By.xpath("//span[@class='lbl']"));

        String Usernametext = usernamet.getAttribute("value");
        System.out.println(Usernametext);

        Point p = usernamet.getLocation();
        System.out.println(p.x + "--getLocation-" + p.y);

        Rectangle d = usernamet.getRect();
        System.out.println(d.x + "---getRect--" + d.y);

        String t = usernamet.getCssValue("width");
        System.out.println(t);

        String getText = usernamet.getText();
        System.out.println(getText);

//Wedriver methods
//    get(),getTitle(), getCrrentUrl(),getPageSource(),Close(),quit()

        System.out.println("Title of page :" + driver.getTitle());
        System.out.println("get current URL" + driver.getCurrentUrl());
        System.out.println("get page resource :" + driver.getPageSource());
//locators
//    id,name,tagName(),className(),cssSelector(),xpath(),linkText(),PartialLinkText()
        WebElement name = driver.findElement(By.className(""));
        System.out.println(usernamet.getAttribute("class"));

        Boolean display = usernamet.isDisplayed();
        Boolean select = usernamet.isSelected();
        Boolean enable = usernamet.isEnabled();
        System.out.println("---" + display + "----" + select + "-----" + enable);
        String gettextt = getTextele.getText();
        System.out.println(gettextt);

        String TagName = getTextele.getTagName();
        System.out.println(TagName);

//getAttribute(),getCssValue(),getTagName(),getText(),sendKeys(),getLoction(),getRect(),isDisplayed(),isEnabled(),isSelected(),clear(),click(),submit()
        WebElement password = driver.findElement(By.id("passwordField"));
        password.sendKeys("Pavan*234");
        WebElement LoginButton = driver.findElement(By.xpath("//button[.='Login']"));
        LoginButton.click();

        Thread.sleep(3000);
        driver.findElement(By.xpath("//span[@class='nI-gNb-sb__placeholder']")).click();
        WebElement designation = driver.findElement(By.xpath("(//input[@class='suggestor-input '])[1]"));
        designation.click();
        designation.sendKeys("Automation testing");
        WebElement exp = driver.findElement(By.xpath("(//input[@type='text'])[2]"));
        exp.click();
        WebElement year = driver.findElement(By.xpath("(//div[@id='sa-dd-scrollexperienceDD']/*/*/*)[3]"));
        year.click();
//    WebElement location =driver.findElement(By.xpath("(//input[@type='text'])[3]"));
//    location.sendKeys("hyd");
//    driver.findElement(By.xpath("//b[@class='pre-wrap']"));

//    Actions actions =new Actions(driver);
//    Thread.sleep(3000);
//    actions.keyDown(Keys.ENTER).build().perform();
//    WebElement location =driver.findElement(By.xpath("(//input[@type='text'])[3]"));
//    location.sendKeys("hyd");
//actions.keyDown(Keys.ENTER).build().perform();

        driver.close();
        driver.quit();
    }

    @Test
    public static void SelectionClassMethods() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-Maximized");
        options.addArguments("-disable-notifications");
        driver = new ChromeDriver(options);
        driver.get("https://demoqa.com/automation-practice-form");


        WebElement Gender = driver.findElement(By.xpath("//label[.='Male']"));
        Gender.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)", "");
        WebElement selectele = driver.findElement(By.xpath("(//div[@class=' css-1hwfws3'])[1]"));
        Boolean isdisply = selectele.isDisplayed();
        System.out.println(isdisply);
        selectele.click();
        Thread.sleep(3000);
        Select selectOptions = new Select(selectele);
        List dropdownOptions = selectOptions.getOptions();
        System.out.println(dropdownOptions);
//selectOptions.selectByVisibleText("");
        driver.quit();
        driver.close();
    }
@Test
    public static void selectclass() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions opts = new ChromeOptions();
        opts.addArguments("start-maximized");
        opts.addArguments("disable-notifications");
        driver = new ChromeDriver(opts);
        driver.get("https://www.globalsqa.com/demo-site/select-dropdown-menu/");
System.out.println(driver.getTitle());
        WebElement selectele = driver.findElement(By.xpath("((//div[@rel-title='Select Country']/*)[2]/*)[1]"));
        Select country = new Select(selectele);
//        List<WebElement> options = country.getOptions();
//        for (WebElement Option : options) {
//            System.out.println(Option.getText());
//        }
        country.selectByVisibleText("Latvia");
    }
}


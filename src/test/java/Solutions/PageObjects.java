package Solutions;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageObjects extends TestBase {
    public By clickfirstname = By.xpath("//input[@id='firstName']");
    public By clicklastname = By.xpath("//input[@id='lastName']");
    public By clickemail = By.id("userEmail");
    public By genderradiobtn = By.xpath("//div[@id='genterWrapper']/*/*[1]");

    public PageObjects(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterfirstname(String text1) throws InterruptedException {
        sendKeysUisngByElement(clickfirstname, text1);
    }

    public void enterlastname(String lastname) throws InterruptedException {
        sendKeysUisngByElement(clicklastname, lastname);
    }

    public void enteremail(String text2) throws InterruptedException {
        sendKeysUisngByElement(clickemail, text2);
    }

    public void clickfn() {
        clickOnelement(clickfirstname);
    }

    public void clickln() {
        clickOnelement(clicklastname);
    }

    public void clickEmail() {
        clickOnelement(clickemail);
    }

    public void setGenderradiobtn() {
        clickOnelement(genderradiobtn);
    }


}

package Solutions;

import io.cucumber.java.en.*;

public class definitions extends TestBase {
PageObjects po =new PageObjects(driver);

    @When("user on login home page")
    public void user_on_login_home_page() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("step1");
        launchbrowser();
          logger.info("launch browser");
        NavigateTourl();
          logger.info("url opened");
          test.info("browser launched & navigated to url");

    }

    @And("user enters {string} & {string}&{string}")
    public void user_enters(String text1, String text2,String lastname) throws InterruptedException {
        po.clickfn();
        po.enterfirstname(text1);
        po.clickln();
        po.enterlastname(lastname);
        po.clickEmail();
        po.enteremail(text2);
        po.setGenderradiobtn();
        test.info("entered full name & email");
        System.out.println("step2");
    }

    @And("user clicks on loginbutton")
    public void user_clicks_on_loginbutton() {
        System.out.println("Bourn");
    }

    @Then("user on profile page")
    public void user_on_profile_page() {
        System.out.println("Bourn");
        closebrowser();
        logger.info("browser closed");
    }



}

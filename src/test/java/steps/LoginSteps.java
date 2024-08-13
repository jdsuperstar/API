package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import utilities.Config;
import utilities.Driver;

public class LoginSteps {

    LoginPage page = new LoginPage();

    @Given("user is on login page")
    public void user_is_on_login_page() {
     Driver.getDriver().get(Config.getProperty("sauseDemo"));
    }
    @Then("user will enter username")
    public void user_will_enter_username() {
        page.username.sendKeys(Config.getProperty("username"));
    }
    @Then("user will enter password")
    public void user_will_enter_password() {
       page.password.sendKeys(Config.getProperty("password"));
    }
    @Then("uesr will clicks on login")
    public void uesr_will_clicks_on_login() {
       page.loginButton.click();
    }
    @Then("user verify is logged in")
    public void user_verify_is_loged_in() {
        Assert.assertTrue(page.shoppingCart.isDisplayed());
    }

}

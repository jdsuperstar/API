package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class LoginPage {
    WebDriver driver;
    public LoginPage() {
    this.driver = Driver.getDriver();
        PageFactory.initElements(driver,this);
    }

    @FindBy (xpath = "//input[@id=\"user-name\"]")
    public WebElement username;
    @FindBy (xpath = "//input[@id=\"password\"]")
    public WebElement password;
    @FindBy(xpath = "//input[@id=\"login-button\"]")
    public WebElement loginButton;
    @FindBy(xpath = "//a[@data-test=\"shopping-cart-link\"]")
    public WebElement shoppingCart;
}

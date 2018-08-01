package cloud9.stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class BookingStepDefs {
    WebDriver driver;

    @Given("^I have logged in with email \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void the_cloud_login_page(String string, String string2) {
        System.setProperty("webdriver.chrome.driver","C:/Drivers/chromedriver.exe");
        driver = new ChromeDriver();

        String baseURL = "http://10.9.10.139:81/sqlite/Main/login.html";
        driver.get(baseURL);

        String bodyText = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Text not found!", bodyText.contains("Cloud9 - Sign in"));

        driver.findElement(By.id("inputEmail")).sendKeys(string);
        driver.findElement(By.id("inputPassword")).sendKeys(string2);

        driver.findElement(By.xpath("/html/body/div/form/button")).click();
    }

    @When("^I book a flight from \"([^\"]*)\" to \"([^\"]*)\"$")
    public void i_register_with_the_name(String string, String string2) {
        driver.findElement(By.xpath("/html/body/center[3]/a")).click();
        driver.findElement(By.xpath("/html/body/div/div/div[1]/ul/li[1]/a")).click();

        //make the actual booking
        Select oselect = new Select(driver.findElement(By.id("Origin")));
        oselect.selectByVisibleText(string);

        Select dselect = new Select(driver.findElement(By.id("Destination")));
        dselect.selectByVisibleText(string2);
    }

    @When("^in seat \"([^\"]*)\" and in class \"([^\"]*)\"$")
    public void email_and_password(String string, String string2) {

        driver.findElement(By.id("seat")).sendKeys(string);

        Select fselect = new Select(driver.findElement(By.id("Flightclass")));
        fselect.selectByVisibleText(string2);

        driver.findElement(By.xpath("/html/body/div/div/div[2]/form/button")).click();
    }

    @Then("the booking will be successful")
    public void the_registration_will_be_successful() {
        By bodyTextLocator = By.tagName("body");
        String successHeader = "Flight booked successfully";
        String bodyText = driver.findElement(bodyTextLocator).getText();
        Assert.assertTrue("Text not found!", bodyText.contains(successHeader));
    }
}

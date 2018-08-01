package cloud9.stepdefs;

import cucumber.api.PendingException;
import cucumber.api.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegisterStepDefs {
    WebDriver driver;

    @Given("The cloud login page")
    public void the_cloud_login_page() {
        System.setProperty("webdriver.chrome.driver","C:/Drivers/chromedriver.exe");
        driver = new ChromeDriver();

        String baseURL = "http://10.9.10.139:81/sqlite/Main/login.html";
        driver.get(baseURL);
    }

    @When("^I register with the name \"([^\"]*)\" \"([^\"]*)\"$")
    public void i_register_with_the_name(String string, String string2) {
        driver.findElement(By.linkText("Register")).click();

        String bodyText = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Text not found!", bodyText.contains("Cloud9 - Register"));

        driver.findElement(By.id("inputfirstname")).sendKeys(string);
        driver.findElement(By.id("inputlastname")).sendKeys(string2);
    }

    @When("^email \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void email_and_password(String string, String string2) {
        driver.findElement(By.id("inputEmail")).sendKeys(string);
        driver.findElement(By.id("inputPassword")).sendKeys(string2);
    }

    @Then("the registration will be successful")
    public void the_registration_will_be_successful() {
        driver.findElement(By.xpath("/html/body/div/form/button")).click();
        String bodyText = driver.findElement(By.tagName("h2")).getText();
        Assert.assertTrue("Text not found!", bodyText.contains("Registration Successful"));
    }
}

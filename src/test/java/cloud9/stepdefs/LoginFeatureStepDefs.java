package cloud9.stepdefs;

import cucumber.api.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginFeatureStepDefs
{
    WebDriver driver;

    @Given("A cloud login page")
    public void a_cloud_login_page() {
        System.setProperty("webdriver.chrome.driver","C:/Drivers/chromedriver.exe");
        driver = new ChromeDriver();

        String baseURL = "http://10.9.10.139:81/sqlite/Main/login.html";
        driver.get(baseURL);

        String bodyText = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Text not found!", bodyText.contains("Cloud9 - Sign in"));
    }

    @When("^we login with email \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void we_login_with_email_and_password(String string, String string2) {
        String bodyText = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Text not found!", bodyText.contains("Cloud9 - Sign in"));

        driver.findElement(By.id("inputEmail")).sendKeys(string);
        driver.findElement(By.id("inputPassword")).sendKeys(string2);

        driver.findElement(By.xpath("/html/body/div/form/button")).click();
    }

    @Then("the login will be successful")
    public void the_login_will_be_successful() {
        String bodyText = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Text not found!", bodyText.contains("Welcome"));
    }
}

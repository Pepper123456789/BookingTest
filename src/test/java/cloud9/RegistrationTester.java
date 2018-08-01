package cloud9;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationTester
{
    WebDriver driver;

    public RegistrationTester(WebDriver driver)
    {
        this.driver = driver;
    }

    public void navigateToRegistration()
    {
        driver.findElement(By.linkText("Register")).click();
    }

    public void clickRegisterButton()
    {
        driver.findElement(By.xpath("/html/body/div/form/button")).click();
    }

    public void populateRegisterFields(User user)
    {
        driver.findElement(By.id("inputfirstname")).sendKeys(user.getfName());
        driver.findElement(By.id("inputlastname")).sendKeys(user.getlName());
        driver.findElement(By.id("inputEmail")).sendKeys(user.getEmail());
        driver.findElement(By.id("inputPassword")).sendKeys(user.getPassword());
    }

    public void assertRegistrationHeader()
    {
        String bodyText = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Text not found!", bodyText.contains("Cloud9 - Register"));
    }

    public void assertRegistrationComplete()
    {
        String bodyText = driver.findElement(By.tagName("h2")).getText();
        Assert.assertTrue("Text not found!", bodyText.contains("Email already exists."));
    }
}

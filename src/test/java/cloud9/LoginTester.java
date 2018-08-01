package cloud9;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginTester
{
    WebDriver driver;

    public LoginTester(WebDriver driver)
    {
        this.driver = driver;
    }

    public void populateLogin(User user)
    {
        driver.findElement(By.id("inputEmail")).sendKeys(user.getEmail());
        driver.findElement(By.id("inputPassword")).sendKeys(user.getPassword());
    }

    public void clickLogin()
    {
        driver.findElement(By.xpath("/html/body/div/form/button")).click();
    }

    public void assertLoginHeader()
    {
        String bodyText = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Text not found!", bodyText.contains("Cloud9 - Sign in"));
    }

    public void assertLoginComplete()
    {
        String bodyText = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Text not found!", bodyText.contains("Welcome"));
    }
}

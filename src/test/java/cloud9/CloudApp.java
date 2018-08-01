package cloud9;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class CloudApp
{
    WebDriver driver;
    LoginTester log;
    RegistrationTester reg;
    BookingTester book;
    ArrayList<User> users = new ArrayList<>();

    @Test
    public void testAccess()
    {
        String expectedTitle = "Cloud9 Airlines";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle,expectedTitle);
    }

    @Test
    public void testBooking()
    {
        log.populateLogin(users.get(0));
        log.clickLogin();

        book.navigateToBooking();

        book.populateBooking(users.get(0).getBookings().get(0));
        book.clickBookingButton();
    }

    @Test
    public void testLogin()
    {
        log.assertLoginHeader();
        log.populateLogin(users.get(0));
        log.clickLogin();
        log.assertLoginComplete();
    }

    @Test
    public void testUpdate()
    {
        users.get(0).getBookings().get(0).setOrigin("Chicago");
        users.get(0).getBookings().get(0).setDestination("Dubai");
        users.get(0).getBookings().get(0).setFlightClass("Business");
        users.get(0).getBookings().get(0).setSeat("32C");

        log.assertLoginHeader();
        log.populateLogin(users.get(0));
        log.clickLogin();
        log.assertLoginComplete();
        book.navigateToItinerary();
        book.testUpdateBooking("193", users.get(0).getBookings().get(0));
    }

    @Test
    public void testDelete()
    {
        log.assertLoginHeader();
        log.populateLogin(users.get(0));
        log.clickLogin();
        log.assertLoginComplete();

        book.navigateToItinerary();
        book.testDeleteBooking("195");
    }

    @Test
    public void testRegister()
    {
        reg.navigateToRegistration();
        reg.assertRegistrationHeader();
        reg.populateRegisterFields(users.get(0));
        reg.clickRegisterButton();
        reg.assertRegistrationComplete();
    }

    @Before
    public void setUpBeforeTest()
    {
        System.setProperty("webdriver.chrome.driver","C:/Drivers/chromedriver.exe");
        driver = new ChromeDriver();
        log = new LoginTester(driver);
        reg = new RegistrationTester(driver);
        book = new BookingTester(driver);

        users.add(new User("Not", "Real", "notarealmail@hotmail.com", "password"));
        users.get(0).addBooking("London", "Dubai", "12E", "Economy");

        String baseURL = "http://10.9.10.139:81/sqlite/Main/login.html";
        driver.get(baseURL);
    }

    @After
    public void tearDownTest()
    {
        //driver.close();
    }
}
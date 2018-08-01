package cloud9;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class BookingTester
{
    WebDriver driver;

    public BookingTester(WebDriver driver)
    {
        this.driver = driver;
    }

    public void navigateToBooking()
    {
        driver.findElement(By.linkText("Homepage")).click();
        driver.findElement(By.linkText("Book Flight")).click();
    }

    public void navigateToItinerary()
    {
        driver.findElement(By.linkText("Homepage")).click();
    }

    public void populateBooking(Booking booking)
    {
        Select oSelect = new Select(driver.findElement(By.id("Origin")));
        oSelect.selectByVisibleText(booking.getOrigin());
        oSelect = new Select(driver.findElement(By.id("Destination")));
        oSelect.selectByVisibleText(booking.getDestination());
        driver.findElement(By.id("seat")).sendKeys(Keys.chord(Keys.CONTROL, "a"),booking.getSeat());
        oSelect = new Select(driver.findElement(By.id("Flightclass")));
        oSelect.selectByVisibleText(booking.getFlightClass());
    }

    public void testUpdateBooking(String flightID, Booking booking)
    {
        List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr"));
        // /html/body/div/div/div[2]/div/table
        int bookings = rows.size()-1;
        int rowID = -1;

        for(int i=1; i<rows.size(); i++)
        {
            String checkFlightID = rows.get(i).findElements(By.tagName("td")).get(3).getText();
            if(checkFlightID.equals(flightID))
            {
                rowID = i;
                break;
            }
        }

        List<WebElement> colVals = rows.get(rowID).findElements(By.tagName("td"));
        String flightNo = colVals.get(3).getText();
        String seatID = colVals.get(4).getText();

        if(rowID>0)
        {
            colVals.get(0).findElement(By.linkText("Update")).click();

            populateBooking(booking);
            clickBookingButton();

            driver.findElement(By.linkText("itinerary")).click();
            rows = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr"));
            colVals = rows.get(rowID).findElements(By.tagName("td"));

            Assert.assertEquals(flightNo, colVals.get(3).getText());
            Assert.assertNotEquals(seatID, colVals.get(4).getText());
        }
    }

    public void testDeleteBooking(String flightID)
    {
        List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr"));
        int bookings = rows.size()-1;
        int rowID = -1;

        for(int i=1; i<rows.size(); i++)
        {
            String checkFlightID = rows.get(i).findElements(By.tagName("td")).get(3).getText();
            if(checkFlightID.equals(flightID))
            {
                rowID = i;
                break;
            }
        }

        if(rowID > 0)
        {
            List<WebElement> colVals = rows.get(rowID).findElements(By.tagName("td"));
            colVals.get(0).findElement(By.linkText("Delete")).click();

            List<WebElement> rows2 = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr"));
            Assert.assertTrue(rows.size() > rows2.size());
        }
    }

    public void clickBookingButton()
    {
        driver.findElement(By.xpath("/html/body/div/div/div[2]/form/button")).click();
    }

    public void clickUpdateButton()
    {
        driver.findElement(By.xpath("/html/body/div/div/div[2]/form/button")).click();
    }
}

package stepDefinitions;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FlightRunner {

	 WebDriver driver;
	    WebDriverWait wait;

@Given("The user opens the MakeMyTrip website")
public void the_user_opens_the_make_my_trip_website() {
    
	ChromeOptions options = new ChromeOptions();
    options.addArguments("--disable-notifications");
    driver = new ChromeDriver(options);
    wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    driver.manage().window().maximize();
    driver.get("https://www.makemytrip.com/");
 // Dismiss popup
	  try {
        // Wait briefly for popup to appear
        WebDriverWait popupWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        
        // Check if "Personal Account" text exists in popup
        WebElement personalAccountPopup = popupWait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//*[contains(text(),'Personal Account')]")
        ));

        if (personalAccountPopup.isDisplayed()) {
            System.out.println("🔔 Personal Account popup detected. Attempting to close it...");
            // Try clicking somewhere else to dismiss it (like body or a close button)
            driver.findElement(By.xpath("//*[@id=\"SW\"]/div[1]/div[2]/div[2]/div/section/span")).click();
            Thread.sleep(1000); // Give it a moment to close
        }
    } catch (Exception e) {
        System.out.println("✅ No 'Personal Account' popup appeared.");
    }

}
@When("The user clicks on {string}")
public void the_user_clicks_on(String string) {
    
	  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Flights']"))).click();
}
@When("The user selects {string}")
public void the_user_selects(String string) {
	 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@data-cy='roundTrip']"))).click();
}
@When("The user enters {string} in the from field")
public void the_user_enters_in_the_from_field(String string) {
	   // Enter FROM: HYD
    WebElement fromInput = driver.findElement(By.id("fromCity"));
    fromInput.click();
    WebElement fromTextBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='From']")));
    fromTextBox.sendKeys("HYD");
    WebElement fromOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'Hyderabad')]")));
    fromOption.click();

}

@When("The user enters {string} in the to field")
public void the_user_enters_in_the_to_field(String string) {
	 // Enter TO: MAA
    WebElement toTextBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"toCity\"]")));
    toTextBox.sendKeys("MAA");
    WebElement toOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'Chennai')]")));
    toOption.click();
	
}
@When("The user selects the departure date")
public void the_user_selects_the_departure_date() {
	 WebElement departureDate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"top-banner\"]/div[2]/div/div/div/div/div[2]/div[1]/div[3]/label/span")));
     departureDate.click();
     WebElement depDateSelect = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label='Wed Jun 25 2025']"))); // Adjust date
     depDateSelect.click();
}
@When("The user selects the return date")
public void the_user_selects_the_return_date() {
	 WebElement retDateSelect = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label='Fri Jun 27 2025']"))); // Adjust date
     retDateSelect.click();
}
@When("The user clicks on the {string} button")
public void the_user_clicks_on_the_button(String string) {
	 // Click Search
    driver.findElement(By.xpath("//a[text()='Search']")).click();
}
@Then("The search results page is displayed")
public void the_search_results_page_is_displayed() {
	  String bodyText = driver.findElement(By.tagName("body")).getText();
      org.junit.Assert.assertTrue(bodyText.contains("200-OK"));
}
}


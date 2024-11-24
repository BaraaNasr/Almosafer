import java.time.LocalDate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AlmosaferTestClass {
	WebDriver driver = new ChromeDriver();
	String WebsiteUrl = "https://global.almosafer.com/en";
	
	@BeforeTest 
	public void MySetup () {
		driver.manage().window().maximize();
		driver.get(WebsiteUrl);
		WebElement CheckTheCurrencyButton = driver.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__continue.btn.btn-primary"));
		CheckTheCurrencyButton.click();
	}
	
	
	
	
	@Test (priority=1)
	public void CheckTheEnglishLanguageIsDefault () {
		String ActualResult = driver.findElement(By.tagName("html")).getAttribute("lang");
		String ExpectedResult = "en";
		Assert.assertEquals(ActualResult, ExpectedResult);
	}
	
	
	@Test (priority=2)
	public void CheckTheCurrencyIsSar () {
		String ActualResult = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']")).getText();
		String ExpectedResult = "SAR";
		Assert.assertEquals(ActualResult, ExpectedResult);
	}

	
	
	@Test (priority=3)
	public void CheckContactNumber () {
		
		String ActualResult = driver.findElement(By.cssSelector(".sc-hUfwpO.bWcsTG")).getText();
		String ExpectedResult = "+966554400000";
		Assert.assertEquals(ActualResult, ExpectedResult);
	}
	
	@Test (priority=4)
	public void CheckTheVisaLogoIsDisplayed () {
		boolean ActualResult = driver.findElement(By.cssSelector(".sc-kNBZmU.jEzChs")).isDisplayed();
		boolean ExpectedResult = true;
		Assert.assertEquals(ActualResult, ExpectedResult);
	
		
	}
	
	@Test (priority=5)
	public void CheckTheHotelTabIsNotSelected () {
		
		WebElement HotelTab=  driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		 String ActualResult = HotelTab.getAttribute("aria-selected");
		 String ExpectedResult = "false" ; 
		Assert.assertEquals(ActualResult, ExpectedResult);
	}
	
	@Test (priority=6)
	public void CheckDepatureDate () {
		
		int Today = LocalDate.now().getDayOfMonth();
		int Tomorrow = LocalDate.now().plusDays(1).getDayOfMonth();
		String ActualResult = driver.findElement(By.cssSelector("div[class='sc-OxbzP sc-lnrBVv gKbptE'] span[class='sc-fvLVrH hNjEjT']")).getText();
		String ExpectedResult = Integer.toString(Tomorrow);
		Assert.assertEquals(ActualResult, ExpectedResult);
	}
	
	@Test (priority=7)
	public void CheckReturnDate () { 
	int Today = LocalDate.now().getDayOfMonth();
	int DayAfterTomorrow = LocalDate.now().plusDays(2).getDayOfMonth();
	String ActualResult = driver.findElement(By.cssSelector("div[class='sc-jtHxuu sc-bYTsla hWmAgB'] span[class='sc-fFTYTi gRMBNc']")).getText();
	String ExpectedResult =Integer.toString(DayAfterTomorrow);
	Assert.assertEquals(ActualResult, ExpectedResult);
	}
}

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
	
}

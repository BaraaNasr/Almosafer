import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AlmosaferTestClass {
	WebDriver driver = new ChromeDriver();
	String WebsiteUrl = "https://global.almosafer.com/en";
	Random rand = new Random () ;
	
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
	String ActualResult = driver.findElement(By.cssSelector("div[class='sc-OxbzP sc-bYnzgO bojUIa'] span[class='sc-fvLVrH hNjEjT']")).getText();
	String ExpectedResult =Integer.toString(DayAfterTomorrow);
	Assert.assertEquals(ActualResult, ExpectedResult);
	}
	
	@Test (priority=8)
	public void RandomChangeTheLanguage () throws InterruptedException {
	String[] MyWebsites = {"https://global.almosafer.com/en" , "https://global.almosafer.com/ar" };
	int RandomIndex = rand.nextInt(MyWebsites.length);
	if(driver.getCurrentUrl().equals("https://global.almosafer.com/ar")) {
		String ActualResult = driver.findElement(By.tagName("html")).getAttribute("lang"); 
		String ExpectedResult = "ar";
		Assert.assertEquals(ActualResult, ExpectedResult);
		
	}
	else {
		driver.getCurrentUrl().equals("https://global.almosafer.com/en" );
		String ActualResult = driver.findElement(By.tagName("html")).getAttribute("lang"); 
		String ExpectedResult = "en";
		Assert.assertEquals(ActualResult, ExpectedResult);
	}
	
}
	
	
    @Test (priority=9)
public void ChangeTheLanguage () throws InterruptedException {
    	String[] MyWebsites = {"https://global.almosafer.com/en" , "https://global.almosafer.com/ar" };
    	String[] ArabicCities = {"جدة" , "دبي" };
    	int RandomArabicCities = rand.nextInt(ArabicCities.length);
    	String [] EnglishCities = {"Dubai","Riyadah","Jeddah"};
    	int RandomEnglishCities = rand.nextInt(EnglishCities.length);
    	WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
    	HotelTab.click();
    	WebElement HotelSearch = driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input"));
    	HotelSearch.click();
    	if (driver.getCurrentUrl().equals("https://global.almosafer.com/ar")) {
    		HotelSearch.sendKeys(ArabicCities[RandomArabicCities]);}
    	
    	else { 
    		driver.getCurrentUrl().equals("https://global.almosafer.com/en");
    		HotelSearch.sendKeys(EnglishCities[RandomEnglishCities]);
    	}
    	Thread.sleep(2000);
        WebElement CitiesList = driver.findElement(By.cssSelector(".sc-phbroq-4.gGwzVo.AutoComplete__List"));
        CitiesList.findElements(By.tagName("li")).get(1).click();
    	
    	}
    
    @Test (priority=10) 
    public void NumOfVisitors () {
    	WebElement NumberOfVisitors = driver.findElement(By.cssSelector(".sc-tln3e3-1.gvrkTi"));
    
        Select select = new Select (NumberOfVisitors);
        int randomNumberOfVisitors = rand.nextInt(2);
        select.selectByIndex(randomNumberOfVisitors);
        WebElement SearchTab = driver.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']"));
        SearchTab.click();
    }
    
    @Test (priority=11)
    public void RefreshThePage () throws InterruptedException {
    	Thread.sleep(35000);
    	WebElement SearchResult = driver.findElement(By.xpath("//span[@data-testid=\'srp_properties_found']"));
    	boolean ActualResult = SearchResult.getText().contains("found") || SearchResult.getText().contains("اماكن");
    	boolean ExpectedResult =true;
    	Assert.assertEquals(ActualResult, ExpectedResult);
    	
    }
    
    @Test (priority=12)
    public void SortPage () {
    	
    	WebElement LowestPriceTab = driver.findElement(By.xpath("//div[@data-testid='srp_sort_LOWEST_PRICE']"));
    	LowestPriceTab.click();
    	
    	List<WebElement> ListsOfPrices =  driver.findElements(By.cssSelector(".__ds__comp.undefined.MuiBox-root muiltr-0"));
    	System.out.println(ListsOfPrices.size());
    	
    	
    }
    
    
    
	
}
    

	


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Pages.MainPage;

public class TriseptClass extends MainPage {

	WebDriver driver;

	private boolean isAttribtuePresent(WebElement element, String attribute) {
		Boolean result = false;
		try {
			String value = element.getAttribute(attribute);
			if (value != null) {
				result = true;
			}
		} catch (Exception e) {
		}
 
		return result;
	}

	@BeforeTest
	public void beforeTest() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.applevacations.com/");
		driver.manage().window().maximize();
		Thread.sleep(3000);
	}
//  Test method to test Apple Ratings
	@Test(enabled = false)
	public void sample2() throws InterruptedException {

		MainPage page = PageFactory.initElements(driver, MainPage.class);
		page.ClickClosePopUp();
		page.clickDestinationTab();

		List<WebElement> Destoptions = driver.findElements(By.xpath("//ul[@class='sub-nav open']//li//a"));
		Destoptions.get(3).click();

		page.ClickClearAll();

		// driver.navigate().refresh();
		JavascriptExecutor jc = (JavascriptExecutor) driver;
		jc.executeScript("window.scrollBy(0,600)", "");
		Thread.sleep(3000);

		page.clickDropdownButton();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement TextT = driver.findElement(By.xpath("//h2[.='Cozumel Featured Deals']"));
		WebElement AllInclusive = driver.findElement(By.xpath("//label[contains(@for,'allinclusive')]"));
		List<WebElement> dropdownList = driver.findElements(By.xpath(
				"//div[@class='chosen-container chosen-container-single chosen-with-drop chosen-container-active']//div[@class='chosen-drop']//ul//li"));

		dropdownList.get(103).click();
		Thread.sleep(3000);
		// ***************************************************************************************************************************************************************

		// ************************************checking for Apple
		// Ratings****************************************

		WebElement NoDeals = driver.findElement(By.xpath("*//div[@class='specials-hotel-empty']"));
		WebElement Hotel_Flight = driver
				.findElement(By.xpath("*//div[@class='checkboxContainer']//label[.='Hotel + Flight']"));

		WebElement HotelOnly = driver
				.findElement(By.xpath("*//div[@class='checkboxContainer']//label[.='Hotel Only']"));
		if (HotelOnly.getCssValue("background-color").equals("rgba(0, 89, 163, 1)")) {
			Thread.sleep(3000);
			Hotel_Flight.click();
			Thread.sleep(3000);
			if (!NoDeals.getText().equals("There are no deals at this time. Please check back later.")) {
				System.out.println("Deals are available for [Hotel+Flight] for " + dropdownList.get(1)
						+ " Ratings but not selected==>");
			} else {
				System.out.println("******Verification of Hotel Only is succesfull******");
			}

			HotelOnly.click();
			Thread.sleep(2000);
		}

		List<WebElement> AppleDataValues = driver
				.findElements(By.xpath("//ul[@class='onsale-filter-validhotelratings']//li//div"));
		List<WebElement> NewAppleRates = driver
				.findElements(By.xpath("//ul[@class='onsale-filter-validhotelratings']//li"));

		List<WebElement> CheckBoxesRatings = driver
				.findElements(By.xpath("(//div[@class='hotelrating-filter-wrap']//label)"));
		List<WebElement> RatingsInput = driver
				.findElements(By.xpath("//ul[@class='onsale-filter-validhotelratings']//li//div//label//span[2]"));

		System.out.println(TextT.getText());


		for (int i = 0; i < AppleDataValues.size(); i++) {

			String[] Applecheckings = { "7", "6", "5", "4", "3", "2", "1" };

			WebElement NewAppleRate = NewAppleRates.get(i);
			WebElement CheckBoxRating = CheckBoxesRatings.get(i);
			WebElement RatingInputValue = RatingsInput.get(i);

			Thread.sleep(5000);
//List<WebElement> AppleImagesFromBox=driver.findElements(By.xpath("*//div[@class='rating-img']"));
			System.out.println(NewAppleRate.getAttribute("data-hotelratings"));

// CSS value for Blue color:rgba(0, 89, 163, 1)
// CSS value for white color:	rgba(255, 255, 255, 0.15) 	  

			if (!NewAppleRate.getAttribute("data-value").equals("(0)")) {
				CheckBoxRating.click();
				Thread.sleep(3000);

				List<WebElement> ApplesFomBox = driver.findElements(By.xpath("*//div[@class='rating-img']"));

				Thread.sleep(3000);
//System.out.println(HotelOnly.getCssValue("background-color"));System.out.println(Hotel_Flight.getCssValue("background-color"));
//{

				for (int j = 0; j < ApplesFomBox.size(); j++) {
					Thread.sleep(2000);
					WebElement ShowAll = driver.findElement(By.xpath("//button[.='Show All Deals ']"));

					jc.executeScript("window.scrollBy(0,3000)", " ");
					Thread.sleep(1000);
					if (ShowAll.isDisplayed()) {

						page.ClickShowAllDeals();
						Thread.sleep(1000);
						jc.executeScript("window.scrollBy(0,-4000)", " ");
					}
					WebElement AppleImageFromBox = ApplesFomBox.get(j);
					String AppleRateText1 = NewAppleRate.getText();

					if (!NoDeals.getText().equals("There are no deals at this time. Please check back later.")) {

						if (AppleImageFromBox.getAttribute("aria-label").startsWith(AppleRateText1)) {
							System.out.println("Apple Image Rating is Correct for "
									+ NewAppleRate.getAttribute("data-hotelratings") + " Rating of " + j + "th Hotel");

						} else {
							System.out.println("Apple Image Rating is wrong for "
									+ NewAppleRate.getAttribute("data-hotelratings") + " Rating of " + j + "th Hotel");
						}
					}

				}
				CheckBoxRating.click();
				Thread.sleep(1000);
			}

		}

	}
//  Test method to test slider
	@Test(enabled = false)
	public void SliderTest() throws InterruptedException {
		MainPage page = PageFactory.initElements(driver, MainPage.class);
		page.ClickClosePopUp();
		page.clickDestinationTab();

		List<WebElement> Destoptions = driver.findElements(By.xpath("//ul[@class='sub-nav open']//li//a"));
		Destoptions.get(3).click();

		page.ClickClearAll();
		Thread.sleep(1000);
		WebElement slider2 = driver
				.findElement(By.xpath("(//span[@class='ui-slider-handle ui-corner-all ui-state-default'])[2]"));
		WebElement slider1 = driver
				.findElement(By.xpath("(//span[@class='ui-slider-handle ui-corner-all ui-state-default'])[1]"));
		Actions move = new Actions(driver);
//	 Action action = (Action) move.dragAndDropBy(slider, 30, 0).build();
//	    ((Actions) action).perform();

		Thread.sleep(1000);
		move.dragAndDropBy(slider1, 46, 0).build().perform();
		Thread.sleep(1000);
		move.dragAndDropBy(slider2, -106, 0).build().perform();
		Thread.sleep(1000);
		WebElement calender1 = driver.findElement(By.xpath("//input[@id='mobilefromDate']"));
		WebElement calender2 = driver.findElement(By.xpath("//input[@id='mobiletoDate']"));
		// calender1.click();
		// WebElement selectDate1 = driver.findElement(By.xpath("//a[@aria-label='3
		// March 2020']"));
		// WebElement selectDate2 = driver.findElement(By.xpath("//a[@aria-label='3
		// March 2021']"));
//	selectDate1.click();
//	selectDate2.click();

		SoftAssert sf = new SoftAssert();

		Thread.sleep(1000);

	}
//  Test method to test Number of nights
	@Test(enabled = false)
	public void NumberOfNightsTest() throws InterruptedException {

		MainPage page = PageFactory.initElements(driver, MainPage.class);
		page.ClickClosePopUp();
		page.clickDestinationTab();

		List<WebElement> Destoptions = driver.findElements(By.xpath("//ul[@class='sub-nav open']//li//a"));
		Destoptions.get(3).click();

		page.ClickClearAll();

		// driver.navigate().refresh();
		JavascriptExecutor jc = (JavascriptExecutor) driver;
		jc.executeScript("window.scrollBy(0,600)", "");
		Thread.sleep(3000);

		String[] ArrayNights = { "7 or more", "6", "5", "4", "3", "2", "1" };
		jc.executeScript("window.scrollBy(0,300)", "");
		Thread.sleep(1000);
		List<WebElement> NoNights = driver.findElements(By.xpath("//ul[@class='onsale-filter-lengthofstay']//li//div//label//span[2]"));
		List<WebElement> NightsOptions = driver.findElements(By.xpath("//ul[@class='onsale-filter-lengthofstay']//li"));
		List<WebElement> LabelNights = driver
				.findElements(By.xpath("(//div[@class='lengthofstay-filter-wrap']//label)"));

		for (int i = 0; i < LabelNights.size(); i++) {

			WebElement Nights = LabelNights.get(i);
			WebElement NoNight = NoNights.get(i);
			WebElement NightsOption = NightsOptions.get(i);

			if (!NightsOption.getAttribute("data-value").contentEquals("(0)")) {
				Nights.click();
				Thread.sleep(3000);
				System.out.println("\nNumber of nights==>" + ArrayNights[i]);
				List<WebElement> NoOfNights = driver
						.findElements(By.xpath("//div[@class='specials-hotel-nights-info']"));

				for (int j = 0; j < NoOfNights.size(); j++) {
					WebElement NumberNights = NoOfNights.get(j);

					Thread.sleep(2000);
					System.out.println(NumberNights.getText());
				}
				Nights.click();
				Thread.sleep(2000);
			} else

			{
				System.out.println("\nNumber of nights==>" + ArrayNights[i]);
				System.out.println(" Number of nights are unavailable");
			}
		}

	

		Thread.sleep(2000);

	}
	
	
	
	@Test(enabled=true)
	public void RecommendedDropdown() throws InterruptedException
	{  	MainPage page = PageFactory.initElements(driver, MainPage.class);
	page.ClickClosePopUp();
	page.clickDestinationTab();

	List<WebElement> Destoptions = driver.findElements(By.xpath("//ul[@class='sub-nav open']//li//a"));
	Destoptions.get(3).click();
	
	JavascriptExecutor jc = (JavascriptExecutor) driver;
	jc.executeScript("window.scrollBy(0,600)", "");
	Thread.sleep(3000);

	page.clickDropdownButton();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	WebElement TextT = driver.findElement(By.xpath("//h2[.='Cozumel Featured Deals']"));
	WebElement AllInclusive = driver.findElement(By.xpath("//label[contains(@for,'allinclusive')]"));
	List<WebElement> dropdownList = driver.findElements(By.xpath(
			"//div[@class='chosen-container chosen-container-single chosen-with-drop chosen-container-active']//div[@class='chosen-drop']//ul//li"));

	String dropdownOptionName=dropdownList.get(103).getText();dropdownList.get(103).click();
	Thread.sleep(5000); 
	
	page.clickRecommended();
	List<WebElement> Recommendedoptions = driver.findElements(By.xpath("(//ul[@class='chosen-results'])[2]//li"));
	String RecommendedFilterOptionForLowToHigh=Recommendedoptions.get(1).getText();
	Recommendedoptions.get(1).click();Thread.sleep(3000);
	ArrayList<String> obtainedList = new ArrayList<>(); 
		List<WebElement> elementList = driver.findElements(By.xpath("//a[@class='specials-hotel-description-link']"));
		
		
		
		for(WebElement we:elementList){
		   obtainedList.add(we.getText());
		}
		
		ArrayList<String> sortedList = new ArrayList<>(); 
	
			sortedList.addAll(obtainedList);
		
		for (String obtainedListText : obtainedList) {
			
			System.out.println(obtainedListText);
		}
		System.out.println("*****************************");
		Collections.sort(sortedList);
	
	
for (String sortedListText : sortedList) {
			
			System.out.println(sortedListText);
		}
		
		
		
			
			if(obtainedList.equals(sortedList))
			{
				
				System.out.println("\nList from "+dropdownOptionName+" is in Correct Sorted Order for Recommended dropdown option ' "+RecommendedFilterOptionForLowToHigh+ "'");
			}
			else
			{
				System.out.println("List from "+dropdownOptionName+" is Not in Correct Sorted Order for Recommended dropdown option ' "+RecommendedFilterOptionForLowToHigh+ "'");
			}
			Thread.sleep(3000);
		
			page.clickRecommended();Thread.sleep(3000);
		

//			if(obtainedList.equals(sortedList))
//			{
//				
//				System.out.println("\nList from "+dropdownOptionName+" is in Correct Sorted Order for Recommended dropdown option ' "+RecommendedFilterOptionForLowToHigh+ "'");
//			}
//			else
//			{
//				System.out.println("\nList from "+dropdownOptionName+" is Not in Correct Sorted Order for Recommended dropdown option ' "+RecommendedFilterOptionForLowToHigh+ "'");
//			}
		
			List<WebElement> Recommendedoptions1 = driver.findElements(By.xpath("(//ul[@class='chosen-results'])[2]//li"));
			String RecommendedFilterOptionForHighToLow=Recommendedoptions1.get(2).getText();
			Recommendedoptions1.get(2).click();Thread.sleep(3000);
		
			List<WebElement> ReverseList = driver.findElements(By.xpath("//a[@class='specials-hotel-description-link']"));
			ArrayList<String> ArrayList = new ArrayList<>();
			for (WebElement Newlist : ReverseList) {
				
				ArrayList.add(Newlist.getText());
			}
			
			ArrayList<String> sortedList1 = new ArrayList<>();
            sortedList1.addAll(ArrayList);for (String ArrayListText2 : ArrayList) {
				
				System.out.println(ArrayListText2);
			}
			Collections.sort(sortedList1);
			
			
			Collections.reverse(sortedList1);
			
			System.out.println("*************************************");
for (String ArrayText2 : ArrayList) {
				
				System.out.println(ArrayText2);
			}
		if(ArrayList.equals(sortedList1))
		{
			
			System.out.println("\nList from "+dropdownOptionName+" is in Correct Sorted Order for Recommended dropdown option ' "+RecommendedFilterOptionForHighToLow+ "'");
		}
		else
		{
			System.out.println("List from "+dropdownOptionName+" is Not in Correct Sorted Order for Recommended dropdown option ' "+RecommendedFilterOptionForHighToLow+ "'");
		}
		
		page.clickRecommended();Thread.sleep(3000);
		
		List<WebElement> Recommendedoptions2 = driver.findElements(By.xpath("(//ul[@class='chosen-results'])[2]//li"));
		String RecommendedFilterOptionForPriceLowToHigh=Recommendedoptions2.get(3).getText();
		Recommendedoptions2.get(3).click();Thread.sleep(3000);
		
		List<WebElement> PricesLowToHigh = driver.findElements(By.xpath("//div[@class='specials-hotel-price-summary']//span//span/span[2]"));
		
		ArrayList ArrayList2 = new ArrayList();
		for (WebElement PriceLowToHigh : PricesLowToHigh) {
			
			ArrayList2.add(PriceLowToHigh.getText());
		}
		
		
		
		ArrayList SortedArrayPricesLowToHigh = new ArrayList();
		
		SortedArrayPricesLowToHigh.addAll(ArrayList2);
		
		Collections.sort(SortedArrayPricesLowToHigh);
		
		if(ArrayList2.equals(SortedArrayPricesLowToHigh))
		{
			
			System.out.println("\nList from '"+dropdownOptionName+"' is in Correct Sorted Order for Recommended dropdown option ' "+RecommendedFilterOptionForPriceLowToHigh+ "'");
			
			System.out.println(ArrayList2);
		}
		else
		{
			System.out.println("List from '"+dropdownOptionName+"' is Not in Correct Sorted Order for Recommended dropdown option ' "+RecommendedFilterOptionForPriceLowToHigh+ "'");
			
			System.out.println(ArrayList2);
		}
		
		page.clickRecommended();Thread.sleep(3000);
		
		List<WebElement> Recommendedoptions4 = driver.findElements(By.xpath("(//ul[@class='chosen-results'])[2]//li"));Thread.sleep(1000);
		String RecommendedFilterOptionForPriceHighToLow=Recommendedoptions4.get(4).getText();
		Recommendedoptions4.get(4).click();Thread.sleep(3000);
		
		List<WebElement> PricesHighToLow = driver.findElements(By.xpath("//div[@class='specials-hotel-price-summary']//span//span/span[2]"));
		
		ArrayList ArrayList3 = new ArrayList();
		
		ArrayList SortedArrayPricesHighToLow = new ArrayList();
		for (WebElement PriceHighToLow : PricesHighToLow) {
			
			ArrayList3.add(PriceHighToLow.getText());
		}
		
		SortedArrayPricesHighToLow.addAll(ArrayList3);
		
		Collections.sort(SortedArrayPricesHighToLow);
		
		Collections.reverse(SortedArrayPricesHighToLow);	
		
		if(ArrayList3.equals(SortedArrayPricesHighToLow))
		{
			
			System.out.println("\nList from '"+dropdownOptionName+"' is in Correct Sorted Order for Recommended dropdown option ' "+RecommendedFilterOptionForPriceHighToLow+ "'");
			
			System.out.println(ArrayList3);
		}
		else
		{
			System.out.println("List from '"+dropdownOptionName+"' is Not in Correct Sorted Order for Recommended dropdown option ' "+RecommendedFilterOptionForPriceHighToLow+ "'");
			
			System.out.println(ArrayList3);
		}
		
page.clickRecommended();Thread.sleep(3000);
		
		List<WebElement> Recommendedoptions5 = driver.findElements(By.xpath("(//ul[@class='chosen-results'])[2]//li"));Thread.sleep(1000);
		String RecommendedFilterOptionForAppleRatingHighToLow=Recommendedoptions5.get(5).getText();
		Recommendedoptions5.get(5).click();Thread.sleep(3000);
		
		ArrayList ArrayList5 = new ArrayList();
		
		List<WebElement> AppleRatingHighToLow = driver.findElements(By.xpath("//div[@class='rating-img']"));
		
		for (WebElement AppleHighToLow : AppleRatingHighToLow) {
			
			String AppleHighToLowText = AppleHighToLow.getAttribute("aria-label");
			
			String AppleRatingNumber = AppleHighToLowText.substring(0, 1);
			
			ArrayList5.add(AppleRatingNumber);
			
		}
		
		ArrayList SortedArray5 = new ArrayList();
		
		SortedArray5.addAll(ArrayList5);
		Collections.sort(SortedArray5);
		
		Collections.reverse(SortedArray5);
		
		if(ArrayList5.equals(SortedArray5))
		{
			
			System.out.println("\nList from '"+dropdownOptionName+"' is in Correct Sorted Order for Recommended dropdown option ' "+RecommendedFilterOptionForAppleRatingHighToLow+ "'");
			
			System.out.println(ArrayList5);
		}
		else
		{
			System.out.println("List from '"+dropdownOptionName+"' is Not in Correct Sorted Order for Recommended dropdown option ' "+RecommendedFilterOptionForAppleRatingHighToLow+ "'");
			
			System.out.println(ArrayList5);
		}
		
		System.out.println("*************************************************************");
		
page.clickRecommended();Thread.sleep(3000);
		
		List<WebElement> Recommendedoptions6 = driver.findElements(By.xpath("(//ul[@class='chosen-results'])[2]//li"));Thread.sleep(1000);
		String RecommendedFilterOptionForAppleRatingLowToHigh=Recommendedoptions6.get(6).getText();
		Recommendedoptions6.get(6).click();Thread.sleep(3000);
		
		ArrayList ArrayList6 = new ArrayList();
		
		List<WebElement> AppleRatingLowToHigh = driver.findElements(By.xpath("//div[@class='rating-img']"));
		
		for (WebElement AppleLowToHigh : AppleRatingLowToHigh) {
			
			String AppleLowToHighText = AppleLowToHigh.getAttribute("aria-label");
			
			String AppleRatingNumberLowToHigh = AppleLowToHighText.substring(0, 1);
			
			ArrayList6.add(AppleRatingNumberLowToHigh);
			
		}
		
		ArrayList SortedArray6 = new ArrayList();
		
		SortedArray6.addAll(ArrayList6);
		Collections.sort(SortedArray6);
		
		if(ArrayList6.equals(SortedArray6))
		{
			
			System.out.println("\nList from '"+dropdownOptionName+"' is in Correct Sorted Order for Recommended dropdown option ' "+RecommendedFilterOptionForAppleRatingLowToHigh+ "'");
			
			System.out.println(ArrayList6);
		}
		else
		{
			System.out.println("List from '"+dropdownOptionName+"' is Not in Correct Sorted Order for Recommended dropdown option ' "+RecommendedFilterOptionForAppleRatingLowToHigh+ "'");
			
			System.out.println(ArrayList6);
		}
		
	}

	@AfterTest
	public void AfterTest() {
		driver.close();
	}
}

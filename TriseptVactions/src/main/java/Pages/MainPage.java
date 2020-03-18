package Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

	
	public WebDriver driver;
	
@FindBy(how = How.XPATH, using = "//div[@class='rating-img']")

	 List<WebElement> AppleRatings;
	 
	@FindBy(how=How.XPATH,using = "(//a[@class='chosen-single'])[3]")
	WebElement DropdownButton;
//	 public MainPage(WebDriver driver)
//		{
//			this.driver=driver;
//			PageFactory.initElements(driver, this);
//		}
	@FindBy(how=How.XPATH, using ="(//div[@class='hotelrating-filter-wrap']//label)[1]")
	WebElement SixRatings;
	@FindBy(how=How.XPATH,using ="//button[.='Show All Deals ']")
	WebElement ShowAllDeals;
	@FindBy(how=How.XPATH, using="(//div[@class='lengthofstay-filter-wrap']//label)[1]")
	WebElement SevenNights;
	@FindBy(how=How.XPATH, using="//div[@class='specials-hotel-nights-info']")
	WebElement LabelSevenNights;
	@FindBy(how=How.XPATH, using="//div[@class='close-toaster']")
	WebElement closePopUp;
	@FindBy(how=How.XPATH, using="(//a[contains(@id,'accessible-megamenu')])[2]")
	WebElement destinationTab;
	@FindBy(how=How.LINK_TEXT, using="href=\"https://www.applevacations.com/destinations/mexico\"")
	WebElement Mexico;
	@FindBy(how=How.XPATH, using="(//button[@class='clear-all'])[1]")
	WebElement  ClearAll; 
	@FindBy(how=How.XPATH, using="(//a[@class='chosen-single'])[2]")
	WebElement Recommended;
	
	
	
	 
	 
	

	
	
	public void clickRecommended()
	{
		Recommended.click();
	}
	public void ClickClearAll()
	{
		ClearAll.click();
	}
	
	public void clickDestinationTab()
	{
		destinationTab.click();
	}
	public void ClickClosePopUp()
	{
		closePopUp.click();
	}

	public void ClickSevenNights()
	{
		SevenNights.click();
	}
	public void ClickShowAllDeals()
	{
		ShowAllDeals.click();
	}
	
	public void ClickSixRatingCheckBox()
	{
		SixRatings.click();
	}
	 public void clickDropdownButton()
	 {
		 DropdownButton.click();
	 }
	 public void AppleRate()
	 {
		 for (WebElement Apple : AppleRatings) {
			
			 String AppleText = Apple.getAttribute("aria-label");
			 System.out.println(AppleText);
		}
	 }
	 
//	public WebElement getDropdownButton() {
//		return DropdownButton;
//	}


//	public void setDropdownButton(WebElement dropdownButton) {
//		DropdownButton = dropdownButton;
//	}
}

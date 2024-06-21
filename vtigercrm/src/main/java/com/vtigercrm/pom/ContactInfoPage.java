package com.vtigercrm.pom;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {
	@FindBy(name = "lastname")
	private WebElement lastnametxtfield;
	@FindBy(name = "support_end_date")
	private WebElement supportenddatetxtfield;
	@FindBy(name = "support_start_date")
	private WebElement supportstartdatetxtfield;
//	@FindBy(name = "//input[@name='account_name']/following-sibling::img")
//	private WebElement orgnametxtfield;
	@FindBy(xpath = "(//input[@accesskey])[1]")
	private WebElement savebtn;
	
	
	public ContactInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);	
		
		}
	public void creatcontact(String lastname) {
		lastnametxtfield.sendKeys(lastname);
		savebtn.click();
		
	}
//	public void creatcontact(WebDriver driver, String lastname, String eurl) throws InterruptedException {
//		lastnametxtfield.sendKeys(lastname);
//		Thread.sleep(2000);
//		orgnametxtfield.click();
//		Set<String> allwinid = driver.getWindowHandles();
//		for (String winid : allwinid) {
//			driver.switchTo().window(winid);
//			String aurl = driver.getCurrentUrl();
//			if(aurl.equals(eurl)) {
//				break;
//			}
			
//		}
		
		
//	}
	public WebElement getSavebtn() {
		return savebtn;
	}
	public void creatcontact(String lastname, String enddate, String startdate) {
		lastnametxtfield.sendKeys(lastname);
		supportenddatetxtfield.clear();;
		
		supportstartdatetxtfield.clear();;
supportenddatetxtfield.sendKeys(enddate);
		supportstartdatetxtfield.sendKeys(startdate);
		savebtn.click();
		
	}
	public WebElement getLastnametxtfield() {
		return lastnametxtfield;
	}
	public WebElement getSupportenddatetxtfield() {
		return supportenddatetxtfield;
	}
	public WebElement getSupportstartdatetxtfield() {
		return supportstartdatetxtfield;
	}
	
	
	
}

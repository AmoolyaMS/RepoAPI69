package com.vtigercrm.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	@FindBy(linkText = "Organizations")
	private WebElement orgmainbtn;
	@FindBy(linkText = "Contacts")
	private WebElement contactmainbtn;
	@FindBy(linkText = "Campaigns")
	private WebElement campaignmainbtn; 
	@FindBy(linkText = "More")
	private WebElement morelink;
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminbtn ;
	
	@FindBy(linkText = "Sign Out")
	private WebElement logoutbtn;
	
	public WebElement getLogoutbtn() {
		return logoutbtn;
	}

	public HomePage(WebDriver driver) {
	PageFactory.initElements(driver, this);	
	}
//	public void getcreatorg() {
//		orgmainbtn.click();
//		
//	}	
//	public void getcreatcontact() {
//		contactmainbtn.click();	
//	}

	public WebElement getOrgmainbtn() {
		return orgmainbtn;
	}

	public WebElement getContactmainbtn() {
		return contactmainbtn;
	}
	public void getlogout(WebDriver driver) {
		adminbtn.click();
		Actions a=new Actions(driver);
		a.moveToElement(logoutbtn);
		logoutbtn.click();
		
	}

	
}

package com.vtigercrm.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class OrganizationInfoPage {
	@FindBy(name = "accountname")
	private WebElement  orgNametxtfield;
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement savebtn;
	@FindBy(name = "industry")
	private WebElement industrytxtfield;
	@FindBy(name = "accounttype")
	private WebElement typetextfield;
	@FindBy(id = "phone")
	private WebElement orgphonetxtfield ;

	
	
	public OrganizationInfoPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	public WebElement getOrgNametxtfield() {
		return orgNametxtfield;
	}

	public WebElement getSavebtn() {
		return savebtn;
	}
	
	public void creatorg(String orgname) {
		orgNametxtfield.sendKeys(orgname);
		savebtn.click();
		
	}
	
	public void creatorg(String orgname, String value, String value1) {
		orgNametxtfield.sendKeys(orgname);
		Select s=new Select(industrytxtfield);
		s.selectByValue(value);
		Select s1=new Select(typetextfield);
		s1.selectByValue(value1);
		savebtn.click();
		
	}
	public void creatorg(String orgname, String value, String value1, String phonenum) {
		orgNametxtfield.sendKeys(orgname);
		orgphonetxtfield.sendKeys(phonenum);
		Select s=new Select(industrytxtfield);
		s.selectByValue(value);
		Select s1=new Select(typetextfield);
		s1.selectByValue(value1);
		savebtn.click();
		
	}
	public void creatorg(String orgname, String phonenum) {
		orgNametxtfield.sendKeys(orgname);
		orgphonetxtfield.sendKeys(phonenum);
		savebtn.click();
		
	
	}
	
		
	
	
	public WebElement getOrgphonetxtfield() {
		return orgphonetxtfield;
	}
	
	
}

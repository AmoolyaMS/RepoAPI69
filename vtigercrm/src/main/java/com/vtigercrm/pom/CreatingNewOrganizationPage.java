package com.vtigercrm.pom;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewOrganizationPage {
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement orgheaderid;
	@FindBy(id = "dtlview_Industry")
	private WebElement industryid ;
	@FindBy(id = "dtlview_Organization Name")
	private WebElement orgnameid ;
	@FindBy(id = "dtlview_Phone")
	private WebElement orgphoneid ;
	
	
	
	public WebElement getOrgphoneid() {
		return orgphoneid;
	}

	public WebElement getOrgnameid() {
		return orgnameid;
	}

	public CreatingNewOrganizationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getIndustryid() {
		return industryid;
	}
	public WebElement getOrgheaderid() {
		return orgheaderid;
	}
}

package com.vtigercrm.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewContactPage {
	@FindBy(className = "dvHeaderText")
	private WebElement lastnameid ;
	@FindBy(id = "dtlview_Support Start Date")
	private WebElement supportstartdateid ;
	@FindBy(id =  "dtlview_Support End Date")
	private WebElement supportenddateid ;
	

	public CreatingNewContactPage(WebDriver driver) {
		PageFactory.initElements(driver, this);	
		}
	public WebElement getLastnameid() {
		return lastnameid;
	}
	public WebElement getSupportstartdateid() {
		return supportstartdateid;
	}
	public WebElement getSupportenddateid() {
		return supportenddateid;
	}
	
}

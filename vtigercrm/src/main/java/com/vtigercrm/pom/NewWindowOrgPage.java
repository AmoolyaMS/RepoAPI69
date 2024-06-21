package com.vtigercrm.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewWindowOrgPage {
	@FindBy( name = "search")
	private WebElement searchbtn ;
	@FindBy(id = "search_txt")
	private WebElement searchtxtfield;
//	@FindBy(xpath = "//a[text()='\"+orgname+\"']")
//	private WebElement orgfield;

	public NewWindowOrgPage(WebDriver driver) {
		PageFactory.initElements(driver, this);	
		}
	public void searchforcreatedorg(CharSequence orgname) {
		searchtxtfield.sendKeys(orgname);
		searchbtn.click();
	
		
		
	}
	
}

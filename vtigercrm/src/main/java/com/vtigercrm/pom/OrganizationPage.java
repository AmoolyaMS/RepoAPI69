package com.vtigercrm.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class OrganizationPage {
@FindBy(xpath = "//img[@alt='Create Organization...']")
private WebElement neworgbtn;
@FindBy(name = "search_text")
private WebElement orgsearchtextfield;
@FindBy(name = "submit")
private WebElement orgsearchbtn;
@FindBy(id = "bas_searchfield")
private WebElement orgsearchdropdown;




public OrganizationPage(WebDriver driver) {
	PageFactory.initElements(driver, this);
	
}

public WebElement getNeworgbtn() {
	return neworgbtn;
}
public void getorgbyname(String orgname, String value) {
	orgsearchtextfield.sendKeys(orgname);
	Select s=new Select(orgsearchdropdown);
	s.selectByValue(value);
	orgsearchbtn.click();
}



}

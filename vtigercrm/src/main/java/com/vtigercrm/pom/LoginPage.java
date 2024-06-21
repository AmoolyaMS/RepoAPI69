package com.vtigercrm.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mysql.jdbc.Driver;

public class LoginPage {
	@FindBy(name = "user_name")
	private WebElement usernametxbox;
	@FindBy(name = "user_password")
	private WebElement pwdtxbox;
	@FindBy(id = "submitButton")
	private WebElement loginbtn;
	public LoginPage()
	{
		
	}
	 
	public LoginPage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);	
	}

	public void login(String un, String pwd) {
		System.out.println(usernametxbox);
		usernametxbox.sendKeys(un);
		pwdtxbox.sendKeys(pwd);
		loginbtn.click();
	}
	public void login( WebDriver driver, String url, String un, String pwd) throws InterruptedException {
		driver.get(url);
		Thread.sleep(2000);	
		usernametxbox.sendKeys(un);
		pwdtxbox.sendKeys(pwd);
		loginbtn.click();
	}
	public void closeBrowser(WebDriver driver) {
		driver.quit();
	}
	
}

package com.vtigercrm.pom;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mysql.jdbc.Driver;

public class LoginPage {
	WebDriver driver;
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
		this.driver=driver;
		PageFactory.initElements(driver, this);	
	}

	public void login(String un, String pwd) {
		System.out.println(usernametxbox);
		usernametxbox.sendKeys(un);
		pwdtxbox.sendKeys(pwd);
		loginbtn.click();
	}
	public void login(String url, String un, String pwd) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(url);
		driver.manage().window().maximize();
		usernametxbox.sendKeys(un);
		pwdtxbox.sendKeys(pwd);
		loginbtn.click();
	}
	public void closeBrowser() {
		driver.quit();
	}
	
}

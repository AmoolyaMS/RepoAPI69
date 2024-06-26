package com.vtigercrm.contacttest;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.vtiger.baseutitlity.BaseClass;
import com.vtigercrm.generic.fileutility.ExcelUtility;
import com.vtigercrm.generic.fileutility.FileUtility;
import com.vtigercrm.generic.webdriverutility.JavaUtils;
import com.vtigercrm.generic.webdriverutility.WebdriverUtils;
import com.vtigercrm.pom.ContactInfoPage;
import com.vtigercrm.pom.ContactPage;
import com.vtigercrm.pom.CreatingNewContactPage;
import com.vtigercrm.pom.HomePage;
import com.vtigercrm.pom.LoginPage;
import com.vtigercrm.pom.NewWindowOrgPage;
import com.vtigercrm.pom.OrganizationInfoPage;
import com.vtigercrm.pom.OrganizationPage;

public class CreatContactWithOrgTest extends BaseClass {

	public static void main(String[] args) throws IOException, InterruptedException {
		FileUtility flib=new FileUtility();
		ExcelUtility elib=new ExcelUtility();
		WebdriverUtils wlib=new WebdriverUtils();
		String lastname = elib.getdatafromExcel("contact", 1, 2);
		JavaUtils jlib=new JavaUtils();
		int rannum = jlib.getRandomNum();
		String orgname=elib.getdatafromExcel("org", 1, 2)+rannum;

		String Browser = flib.getDataFromPropertyFile("browser");
		String Url = flib.getDataFromPropertyFile("url");
		String user = flib.getDataFromPropertyFile("un");
		String pwd = flib.getDataFromPropertyFile("pwd");
		WebDriver driver=null;
		if (Browser.equals("chrome")) {	
			driver=new ChromeDriver();	
		}
		else if(Browser.equals("Edge")) 
		{
			driver=new EdgeDriver();
		} 
		else if(Browser.equals("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else  
		{driver=new ChromeDriver();}
		driver.get(Url);
		LoginPage lpom=new LoginPage(driver);
		lpom.login(user, pwd);
		HomePage hpom=new HomePage(driver);
		hpom.getOrgmainbtn().click();
		OrganizationPage opom=new OrganizationPage(driver);
		opom.getNeworgbtn().click();
		OrganizationInfoPage oipom=new OrganizationInfoPage(driver);
		oipom.creatorg(orgname);
		Thread.sleep(2000);
		hpom.getContactmainbtn().click();
		ContactPage cpom=new ContactPage(driver);
		cpom.getNewcontactBtn().click();
		ContactInfoPage cipom=new ContactInfoPage(driver);
		//cipom.creatcontact(driver, lastname, "module=Accounts" );
		cipom.getLastnametxtfield().sendKeys(lastname);
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		wlib.SwitcWindowsByUrl(driver,"module=Accounts");
		NewWindowOrgPage npom=new NewWindowOrgPage(driver);
		npom.searchforcreatedorg(orgname);
		driver.findElement(By.xpath("//a[text()='"+orgname+"']")).click(); 
		wlib.SwitcWindowsByUrl(driver,"module=Contacts");
		cipom.getSavebtn().click();
		CreatingNewContactPage cnpom=new CreatingNewContactPage(driver);
		String acontactid =cnpom.getLastnameid().getText();
		if(acontactid.contains(lastname))
		{
			System.out.println(acontactid+" is created");
		}
		else
		{
			System.out.println(acontactid+" is not created");	
		}


	}

}

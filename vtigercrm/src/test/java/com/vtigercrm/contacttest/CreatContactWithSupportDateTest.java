package com.vtigercrm.contacttest;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.vtigercrm.generic.fileutility.ExcelUtility;
import com.vtigercrm.generic.fileutility.FileUtility;
import com.vtigercrm.generic.webdriverutility.JavaUtils;
import com.vtigercrm.pom.ContactInfoPage;
import com.vtigercrm.pom.ContactPage;
import com.vtigercrm.pom.CreatingNewContactPage;
import com.vtigercrm.pom.HomePage;
import com.vtigercrm.pom.LoginPage;

public class CreatContactWithSupportDateTest {

	public static void main(String[] args) throws IOException {
		
		FileUtility flib=new FileUtility();
		ExcelUtility elib=new ExcelUtility();
		JavaUtils jlib=new JavaUtils();
		
		String Browser = flib.getDataFromPropertyFile("browser");
		String Url = flib.getDataFromPropertyFile("url");
		String user = flib.getDataFromPropertyFile("un");
	    String pwd = flib.getDataFromPropertyFile("pwd");
	    String lastnum = elib.getdatafromExcel("contact", 1, 2);
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(05));
		HomePage hpom=new HomePage(driver);
		hpom.getContactmainbtn().click();
		ContactPage cpom=new ContactPage(driver);
		cpom.getNewcontactBtn().click();
		ContactInfoPage cipom=new ContactInfoPage(driver);
		String startdate = jlib.getsystemDateyyyymmdd();
		String enddate = jlib.getrequiredDateyyyymmdd(30);
		cipom.creatcontact(lastnum,enddate,startdate);
		
		CreatingNewContactPage cnpom=new CreatingNewContactPage(driver);

		String acontactid = cnpom.getLastnameid().getText();
		if(acontactid.contains(lastnum))
		{
			System.out.println(acontactid+" is created");
		}
		else
		{
			System.out.println(acontactid+" is not created");	
		}
		
		
		String astartdate = cnpom.getSupportstartdateid().getText();
		String aenddate = cnpom.getSupportenddateid().getText();
		
		if(astartdate.equals(startdate) && aenddate.equals(enddate))
		{
			System.out.println("date verified");
	}
		else {
			System.out.println("date not verified");
		}
		hpom.getlogout(driver);
		driver.quit();
			}

}

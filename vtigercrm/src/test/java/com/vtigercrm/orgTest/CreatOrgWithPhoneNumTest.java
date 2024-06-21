package com.vtigercrm.orgTest;

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
import com.vtigercrm.generic.webdriverutility.WebdriverUtils;
import com.vtigercrm.pom.CreatingNewOrganizationPage;
import com.vtigercrm.pom.HomePage;
import com.vtigercrm.pom.LoginPage;
import com.vtigercrm.pom.OrganizationInfoPage;
import com.vtigercrm.pom.OrganizationPage;

public class CreatOrgWithPhoneNumTest {

	public static void main(String[] args) throws IOException {
		FileUtility flib=new FileUtility();
		ExcelUtility elib=new ExcelUtility();
		JavaUtils jlib=new JavaUtils();
		WebdriverUtils wlib=new WebdriverUtils();
		String orgname = elib.getdatafromExcel("org", 1, 2)+jlib.getRandomNum();
		String num = elib.getdatafromExcel("org", 7, 3);
		
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(05));
	HomePage hpom=new HomePage(driver);
	hpom.getOrgmainbtn().click();
	OrganizationPage opom=new OrganizationPage(driver);
	opom.getNeworgbtn().click();
		
		OrganizationInfoPage oipom=new OrganizationInfoPage(driver);
		oipom.getOrgphonetxtfield().sendKeys(num);
		oipom.getSavebtn().click();
		wlib.switchAlertaccept(driver);
		oipom.creatorg(orgname);
		CreatingNewOrganizationPage cpom=new CreatingNewOrganizationPage(driver);
		
		String aheader=cpom.getOrgnameid().getText();
		System.out.println(aheader);
		if(aheader.contains(orgname))
		{
			System.out.println(orgname+" is created");
		}
		else 
		{
			System.out.println(orgname+" is not created");
		}
		String anum = cpom.getOrgphoneid().getText();
		
		if(anum.equals(num))
		{
			System.out.println("pass");
		} else
		{
			System.out.println("fail");
		}
	}

}

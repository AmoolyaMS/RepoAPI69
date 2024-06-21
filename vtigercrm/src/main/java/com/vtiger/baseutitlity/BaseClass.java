package com.vtiger.baseutitlity;


import java.io.IOException;
import java.sql.SQLException;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.vtigercrm.generic.databaseutility.DatabaseUtils;
import com.vtigercrm.generic.fileutility.ExcelUtility;
import com.vtigercrm.generic.fileutility.FileUtility;
import com.vtigercrm.generic.webdriverutility.JavaUtils;
import com.vtigercrm.generic.webdriverutility.UtilityClassObject;
import com.vtigercrm.generic.webdriverutility.WebdriverUtils;
import com.vtigercrm.pom.HomePage;
import com.vtigercrm.pom.LoginPage;

public class BaseClass {
	/*creat obj*/
	public WebDriver driver;
	public static WebDriver sdriver;
	public DatabaseUtils dlib=new DatabaseUtils();
	public FileUtility flib=new FileUtility();
	public ExcelUtility elib=new ExcelUtility();
	public WebdriverUtils wlib=new WebdriverUtils();
	public JavaUtils jlib=new JavaUtils();
	
	@BeforeSuite(groups = {"smoke test","regression test"})
	public void congigBS() throws SQLException
	{
		//UtilityClassObject.getTest().log(Status.INFO, "connected to db");
		dlib.getConnection();	
		
	}
	//@Parameters("BROWSER")
	@BeforeTest(groups = {"smoke test","regression test"})
	//public void congigBC(String browser) throws IOException
	public void congigBC() throws IOException
	{
		
	String browser=flib.getDataFromPropertyFile("browser");
		
		if (browser.equals("chrome")) {	
			driver=new ChromeDriver();	
		}
		else if(browser.equals("Edge")) 
		{
			driver=new EdgeDriver();
		} 
		else if(browser.equals("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else  
		{driver=new ChromeDriver();}
		sdriver=driver;
	}
	@BeforeMethod(groups = {"smoke test","regression test"})
	public void congigBM() throws IOException, InterruptedException
	{
		 LoginPage lpom=new LoginPage(driver);
		 String url = flib.getDataFromPropertyFile("url");
		String un = flib.getDataFromPropertyFile("un");
		String pwd = flib.getDataFromPropertyFile("pwd");
		wlib.waitForPageToLoad(driver);
        lpom.login(driver, url, un, pwd);;
	}
	@AfterMethod(groups = {"smoke test","regression test"})
	public void congigAM()
	{
		HomePage hpom=new HomePage(driver);
		hpom.getlogout(driver);
	}
	@AfterTest(groups = {"smoke test","regression test"})
	public void congigAC()
	{
     driver.quit();
	}

	@AfterSuite(groups = {"smoke test","regression test"})
	public void congigAS() throws SQLException
	{
		dlib.closeConnection();
	
	}

}

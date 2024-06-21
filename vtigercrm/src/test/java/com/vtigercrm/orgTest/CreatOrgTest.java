package com.vtigercrm.orgTest;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.vtiger.baseutitlity.BaseClass;
import com.vtigercrm.generic.fileutility.ExcelUtility;
import com.vtigercrm.generic.fileutility.FileUtility;
import com.vtigercrm.generic.webdriverutility.JavaUtils;
import com.vtigercrm.generic.webdriverutility.WebdriverUtils;
import com.vtigercrm.pom.CreatingNewOrganizationPage;
import com.vtigercrm.pom.HomePage;
import com.vtigercrm.pom.LoginPage;
import com.vtigercrm.pom.OrganizationInfoPage;
import com.vtigercrm.pom.OrganizationPage;
//@Listeners(com.vtigercrm.generic.listnerutility.ListenerImplimentation.class)
public class CreatOrgTest extends BaseClass {
	@Test(groups = "smoke test", retryAnalyzer = com.vtigercrm.generic.listnerutility.RetrylistenerImplementation.class)
	public void creatorgwithmandfieldTest() throws IOException {

		JavaUtils jlib=new JavaUtils();
		int rannum = jlib.getRandomNum();
		String orgname=elib.getdatafromExcel("org", 1, 2)+rannum;
		HomePage hpom=new HomePage(driver);
		hpom.getOrgmainbtn().click();
		OrganizationPage opom=new OrganizationPage(driver);
		opom.getNeworgbtn().click();
		OrganizationInfoPage oipom=new OrganizationInfoPage(driver);
		oipom.creatorg(orgname);
		CreatingNewOrganizationPage cpom=new CreatingNewOrganizationPage(driver);
		//verify with header msg
		wlib.waitForPageToLoad(driver);
		String aheader=	cpom.getOrgheaderid().getText();
		//System.out.println(aheader);
		boolean status = aheader.contains(orgname);
		Assert.assertTrue(status);
		
		//verify with header org name
		String aorgname = cpom.getOrgnameid().getText();
		boolean status1 = aorgname.contains(orgname);
		Assert.assertTrue(status1);

	}
	@Test(groups = "regression test")
	public void creatorgwithindustryTest() throws EncryptedDocumentException, IOException
	{
		JavaUtils jlib=new JavaUtils();
		int rannum = jlib.getRandomNum();
		String orgname=elib.getdatafromExcel("org", 1, 2)+rannum;
		HomePage hpom=new HomePage(driver);
		hpom.getOrgmainbtn().click();
		OrganizationPage opom=new OrganizationPage(driver);
		opom.getNeworgbtn().click();
		OrganizationInfoPage oipom=new OrganizationInfoPage(driver);
		oipom.creatorg(orgname, "Entertainment", "Partner");
		CreatingNewOrganizationPage cpom=new CreatingNewOrganizationPage(driver);
		String aheader = cpom.getOrgheaderid().getText();
		boolean status = aheader.contains(orgname);
		Assert.assertTrue(status);
		String aindustry = cpom.getIndustryid().getText();
		String eindustry = elib.getdatafromExcel("org", 4, 3);
		SoftAssert soft=new SoftAssert();
		soft.assertEquals(aindustry, eindustry);
		soft.assertAll();
	}
	@Test(groups = "regression test")
	private void creatorgwithphnumTest() throws EncryptedDocumentException, IOException {
		JavaUtils jlib=new JavaUtils();
		String orgname = elib.getdatafromExcel("org", 1, 2)+jlib.getRandomNum();
		String num = elib.getdatafromExcel("org", 7, 3);
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
		boolean status = aheader.contains(orgname);
		Assert.assertTrue(status);
		String anum = cpom.getOrgphoneid().getText();
		SoftAssert soft=new SoftAssert();
		soft.assertEquals(anum, num);
	}
	@Test
	public void deleteOrgTest() throws EncryptedDocumentException, IOException
	{
		JavaUtils jlib=new JavaUtils();
		int rannum = jlib.getRandomNum();
		String orgname=elib.getdatafromExcel("org", 1, 2)+rannum;
		HomePage hpom=new HomePage(driver);
		hpom.getOrgmainbtn().click();
		OrganizationPage opom=new OrganizationPage(driver);
		opom.getNeworgbtn().click();
		OrganizationInfoPage oipom=new OrganizationInfoPage(driver);
		oipom.creatorg(orgname);
		CreatingNewOrganizationPage cpom=new CreatingNewOrganizationPage(driver);
		//verify with header msg
		wlib.waitForPageToLoad(driver);
		String aheader=	cpom.getOrgheaderid().getText();
		System.out.println(aheader);
		boolean status = aheader.contains(orgname);
		Assert.assertTrue(status);
		//verify with header org name
		String aorgname = cpom.getOrgnameid().getText();
		boolean id = aorgname.contains(orgname);
		SoftAssert soft=new SoftAssert();
		soft.assertTrue(id);
		hpom.getOrgmainbtn().click();
		opom.getorgbyname(orgname, "accountname");
		driver.findElement(By.xpath("(//a[text()='"+orgname+"'])[2]/../../td[8]//a[text()='del']")).click();
		wlib.switchAlertaccept(driver);
	}
}

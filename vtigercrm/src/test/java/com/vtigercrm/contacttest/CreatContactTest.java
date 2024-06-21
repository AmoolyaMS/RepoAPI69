package com.vtigercrm.contacttest;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.vtiger.baseutitlity.BaseClass;
import com.vtigercrm.generic.listnerutility.ListenerImplimentation;
import com.vtigercrm.generic.webdriverutility.JavaUtils;
import com.vtigercrm.generic.webdriverutility.UtilityClassObject;
import com.vtigercrm.pom.ContactInfoPage;
import com.vtigercrm.pom.ContactPage;
import com.vtigercrm.pom.CreatingNewContactPage;
import com.vtigercrm.pom.HomePage;
import com.vtigercrm.pom.NewWindowOrgPage;
import com.vtigercrm.pom.OrganizationInfoPage;
import com.vtigercrm.pom.OrganizationPage;
@Listeners(com.vtigercrm.generic.listnerutility.ListenerImplimentation.class)
/**class is used to create a contact with mandatory field, with support date, 
 * with org name
 * @author:  ams
 */
public class CreatContactTest extends BaseClass
{
	/**Create a contact with mandatory fields
	 * @author msamoolya
	 * @throws IOException
	 */
	@Test(groups = "smoke test")
	public  void creatcontactTest() throws IOException {
		//read last name from excel
		UtilityClassObject.getTest().log(Status.INFO, "read data from ecxel");
		String lastnum = elib.getdatafromExcel("contact", 1, 2);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(05));
		
		//navigate to home page
		UtilityClassObject.getTest().log(Status.INFO, "navigate to home page");
		HomePage hpom=new HomePage(driver);
		hpom.getContactmainbtn().click();
		
		//navigate to contact page
		UtilityClassObject.getTest().log(Status.INFO, "navigate to contact page");
		ContactPage cpom=new ContactPage(driver);
		cpom.getNewcontactBtn().click();
		
		//navigate to contact info page
		UtilityClassObject.getTest().log(Status.INFO, "navigate to contact info page");
		ContactInfoPage cipom=new ContactInfoPage(driver);
		cipom.creatcontact(lastnum);
		
		//navigate to creating new contact page
		UtilityClassObject.getTest().log(Status.INFO, "navigate to creating new contact page");
		CreatingNewContactPage cnpom=new CreatingNewContactPage(driver);
		String acontactid = cnpom.getLastnameid().getText();
		boolean status = acontactid.contains(lastnum);
		
		//validate contact textfield
		AssertJUnit.assertTrue(status);
		UtilityClassObject.getTest().log(Status.PASS, "contact is created");
	}
	/**Create a contact with mandatory fields and support date 
	 * @author msamoolya
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	@Test(groups = "regression test")
	private void creatcontactwithsupportdateTest() throws EncryptedDocumentException, IOException {
		//read last name from excel
		UtilityClassObject.getTest().log(Status.INFO, "read data from ecxel");
		String lastnum = elib.getdatafromExcel("contact", 1, 2);
		//navigate to home page
		UtilityClassObject.getTest().log(Status.INFO, "navigate to home page");
		HomePage hpom=new HomePage(driver);
		hpom.getContactmainbtn().click();
		//navigate to contact page
		UtilityClassObject.getTest().log(Status.INFO, "navigate to contact page");
		ContactPage cpom=new ContactPage(driver);
		cpom.getNewcontactBtn().click();
		//navigate to contact info page, enter required fields
		UtilityClassObject.getTest().log(Status.INFO, "navigate to contact info page");
		ContactInfoPage cipom=new ContactInfoPage(driver);
		String startdate = jlib.getsystemDateyyyymmdd();
		String enddate = jlib.getrequiredDateyyyymmdd(30);
		cipom.creatcontact(lastnum,enddate,startdate);
		//navigate to creating new contact page
		UtilityClassObject.getTest().log(Status.INFO, "navigate to creating new contact page");
		CreatingNewContactPage cnpom=new CreatingNewContactPage(driver);
		String acontactid = cnpom.getLastnameid().getText();
		boolean status = acontactid.contains(lastnum);
		//validating contact textfield 
		Assert.assertTrue(status);
		SoftAssert sa=new SoftAssert();
		String astartdate = cnpom.getSupportstartdateid().getText();
		String aenddate = cnpom.getSupportenddateid().getText();
		//validating support date textfields
		sa.assertEquals(astartdate, startdate);
		sa.assertEquals(aenddate, enddate);
		sa.assertAll();
		UtilityClassObject.getTest().log(Status.PASS, "contact is created");
	}
	/**
	 * @author msamoolya
	 * @throws InterruptedException
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	@Test(groups = "regression test")
	private void creatcontactwithorgTest() throws InterruptedException, EncryptedDocumentException, IOException {
		//read last name from excel
		UtilityClassObject.getTest().log(Status.INFO, "read data from ecxel");
		String lastname = elib.getdatafromExcel("contact", 1, 2);
		JavaUtils jlib=new JavaUtils();
		int rannum = jlib.getRandomNum();
		String orgname=elib.getdatafromExcel("org", 1, 2)+rannum;
		//navigate to home page
		UtilityClassObject.getTest().log(Status.INFO, "navigate to home page");
		HomePage hpom=new HomePage(driver);
		hpom.getOrgmainbtn().click();
		//navigate to org page
		UtilityClassObject.getTest().log(Status.INFO, "navigate to org page");
		OrganizationPage opom=new OrganizationPage(driver);
		opom.getNeworgbtn().click();
		//navigate to org info page, enter required fields
		UtilityClassObject.getTest().log(Status.INFO, "navigate to org info page");
		OrganizationInfoPage oipom=new OrganizationInfoPage(driver);
		oipom.creatorg(orgname);
		Thread.sleep(2000);
		//back to home page
		hpom.getContactmainbtn().click();
		//navigate to contact page
		UtilityClassObject.getTest().log(Status.INFO, "navigate to contact page");
		ContactPage cpom=new ContactPage(driver);
		cpom.getNewcontactBtn().click();
		//navigate to contact info page, enter required fields
		UtilityClassObject.getTest().log(Status.INFO, "navigate to contact info page");
		ContactInfoPage cipom=new ContactInfoPage(driver);
		cipom.getLastnametxtfield().sendKeys(lastname);
		//find org name textfield + image to get lookup table
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		//switch to child window
		wlib.SwitcWindowsByUrl(driver, "module=Accounts");
		NewWindowOrgPage npom=new NewWindowOrgPage(driver);
		npom.searchforcreatedorg(orgname);
		driver.findElement(By.xpath("//a[text()='"+orgname+"']")).click(); 
		wlib.SwitcWindowsByUrl(driver, "module=Contacts");
		cipom.getSavebtn().click();
		//navigate to creating new contact page
		UtilityClassObject.getTest().log(Status.INFO, "navigate to creating new contact page");
		CreatingNewContactPage cnpom=new CreatingNewContactPage(driver);
		String acontactid =cnpom.getLastnameid().getText();
		boolean status = acontactid.contains(lastname);
		//validate contact text field

		Assert.assertTrue(status);
		UtilityClassObject.getTest().log(Status.PASS, "contact is created");

	}

}



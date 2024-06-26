package com.vtigercrm.generic.webdriverutility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.vtiger.baseutitlity.BaseClass;

public class WebdriverUtils{

	public void waitForPageToLoad(WebDriver driver) {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(05));
	}

	public void SwitcWindowsByUrl(WebDriver driver, CharSequence eurl) {
		Set<String> allwinid = driver.getWindowHandles();
		for (String winid : allwinid) {
			driver.switchTo().window(winid);
			String aurl = driver.getCurrentUrl();
			if(aurl.contains(eurl)) {
				break;
			}	
		}	
	}
	public void SwitcWindowsByTitle(WebDriver driver, String etitle) {
		Set<String> allwinid = driver.getWindowHandles();
		for (String winid : allwinid) {
			driver.switchTo().window(winid);
			String atitle = driver.getTitle();
			if(atitle.contains(etitle)) {
				break;
			}	
		}	
	}
	public void getMouseHover(WebDriver driver, WebElement ele) {

		Actions a=new Actions(driver);
		a.moveToElement(ele).perform();
	}
	public void getSelect(WebElement ele, String value) {
		Select s=new Select(ele);
		s.selectByValue(value);

	}
	public void getSelect(WebElement ele, int index) {
		Select s=new Select(ele);
		s.selectByIndex(index);

	}
	public void switchAlertaccept(WebDriver driver) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
	}
	public void switchAlertdismiss(WebDriver driver) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().dismiss();
	}
	public void switchFrame(WebDriver driver,WebElement ele) {

		driver.switchTo().frame(ele);
	}
	public void waitForElementPresent(WebDriver driver,WebElement ele) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

}

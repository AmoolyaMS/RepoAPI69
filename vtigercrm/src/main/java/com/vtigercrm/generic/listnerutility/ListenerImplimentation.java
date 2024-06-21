package com.vtigercrm.generic.listnerutility;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.vtiger.baseutitlity.BaseClass;
import com.vtigercrm.generic.webdriverutility.UtilityClassObject;



public class ListenerImplimentation extends BaseClass implements ITestListener, ISuiteListener  {
	
	public ExtentReports report;
	public ExtentTest test;
	@Override
	public void onStart(ISuite suite) {
		String	date=new Date().toString().replace(' ','_').replace(':', ' ');
		ExtentSparkReporter spark=new ExtentSparkReporter("./advancereport/Report_"+date+".html");
		spark.config().setDocumentTitle("CRM report");
		spark.config().setReportName("crm creatcontact report");
		spark.config().setTheme(Theme.DARK);
		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "WINDOWS 10");
		report.setSystemInfo("BROWSER", "CHROME");

	}

	@Override
	public void onFinish(ISuite suite) {
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		String methodname = result.getName();
		
		test = report.createTest(methodname);
		UtilityClassObject.setTest(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {

	}

	@Override
	public void onTestFailure(ITestResult result) {
		String methodname = result.getName();
		TakesScreenshot ts=(TakesScreenshot)sdriver;
		String filepath = ts.getScreenshotAs(OutputType.BASE64);
		String date = LocalDate.now().toString();
		//
        test.addScreenCaptureFromBase64String(filepath, methodname+"+"+date);

	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {


	}

	@Override
	public void onFinish(ITestContext context) {


	}



}



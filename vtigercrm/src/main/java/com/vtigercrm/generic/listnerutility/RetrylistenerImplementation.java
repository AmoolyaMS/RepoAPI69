package com.vtigercrm.generic.listnerutility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetrylistenerImplementation implements IRetryAnalyzer{


	int count=0;
	int limit=4;
	public boolean retry(ITestResult result) {
	if (count < limit) {
		count++;
		return true;
	}
		return false;
	}
	

}

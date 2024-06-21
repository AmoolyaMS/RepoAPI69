package com.vtigercrm.generic.fileutility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class FileUtility {
public String getDataFromPropertyFile(String key) throws IOException {
	FileInputStream fis=new FileInputStream("./congifdata/commondata.properties");
	Properties pobj=new Properties();
	pobj.load(fis);
	String data=pobj.getProperty(key);
	return data;

	
}

}

package com.vtigercrm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	public String getdatafromExcel(String sheet, int row, int cell) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream("./testdata/testscript.xlsx");
		 Workbook wb = WorkbookFactory.create(fis);
		String data= wb.getSheet(sheet).getRow(row).getCell(cell).toString();
		wb.close();//to avoid crash bcs excel obj will be created in apochipoi
		return data;
	}
	public void setdataintoExcel(String sheet, int row, int cell, Date data) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream("./testdata/testscript.xlsx");
		 Workbook wb = WorkbookFactory.create(fis);
		Sheet sheet1 = wb.getSheet(sheet);
		Cell cell1=sheet1.getRow(row).createCell(cell);
		cell1.setCellType(CellType.STRING);
		cell1.setCellValue(data);
		FileOutputStream fos=new FileOutputStream("./testdata/testscript.xlsx");
		wb.write(fos);
		wb.close();
		
	}
public int getrowCount(String sheetname) throws EncryptedDocumentException, IOException {
	FileInputStream fis=new FileInputStream("./testdata/testscript.xlsx");
	 Workbook wb = WorkbookFactory.create(fis);
	Sheet sheet = wb.getSheet(sheetname);
	int rowcount=sheet.getLastRowNum();
	return rowcount;
}
}

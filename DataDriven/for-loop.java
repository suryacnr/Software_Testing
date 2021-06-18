package com.selenium;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class datariveforLoop {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		File src= new File("/home/surya/eclipse/demo_execl.xlsx");
	    FileInputStream fis =new FileInputStream(src);
	    //to load xlsx file
	    XSSFWorkbook xs = new XSSFWorkbook(fis);
	    //to load xls file
	    // HSSFworkbook hs =new HSSFWorkbook(fis);
	    //to find which sheet example sheet1 sheet2
	    
	    XSSFSheet sheet1 = xs.getSheetAt(0);
	    //to get lastnumber in row to use it for for loop
	    int rowcount = sheet1.getLastRowNum();
	    System.out.println(rowcount);
	     for(int i=0; i<rowcount; i++)
	     {
	    	String data0 = sheet1.getRow(i).getCell(0).getStringCellValue();
	    	//String data1 = sheet1.getRow(i).getCell(1).getStringCellValue();
	    	System.out.println("data in the string: " + data0 );
	     }
	    xs.close();

	}

}

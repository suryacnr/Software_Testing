package com.selenium;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class selenium_test {

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
	    //to get rows and colum
	   String data0 =  sheet1.getRow(1).getCell(1).getStringCellValue();
	    System.out.println("printing from excel " + data0);
	    String data1 =  sheet1.getRow(1).getCell(2).getStringCellValue();
	    System.out.println("printing from excel " + data1);
	    xs.close();
	    
	    
	    
	    
	    		
	
	    // declaration and instantiation of objects/variables  
	  //  System.setProperty("webdriver.chrome.driver", "/home/surya/eclipse/chromedriver");  
	   // WebDriver driver=new ChromeDriver();  
	      
	// Launch website  
	  //  driver.navigate().to("http://www.google.com/"); 
	    
	}

}

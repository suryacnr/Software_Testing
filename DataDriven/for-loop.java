package com.selenium;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class datariveforLoop {
	
	
	
	
	
	
	@DataProvider(name ="userdifinename")
    public Object[][] excelreader( ) {
    	XSSFWorkbook  book = null;
    	Object[][] data=null;
    	try {
			String file = System.getProperty("user.dir")+"/TestData/XlData.xlsx";
			FileInputStream getfile = new FileInputStream(file);
			System.out.println(getfile);
			book = new XSSFWorkbook(getfile);
			XSSFSheet sheet =book.getSheetAt(0);
			data =new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			for(int i=0;i<sheet.getLastRowNum();i++) {
				for(int j=0;j<sheet.getRow(0).getLastCellNum();j++) {
					data[i][j]=sheet.getRow(i+1).getCell(j).toString();
					
				}
			}
			
			//return data;
			
			//int lastrow=sheet.getLastRowNum();
			//int lastcolum =sheet.getRow(1).getLastCellNum();
//			for(int i=1;i<lastrow;i++) {
//				XSSFRow row =sheet.getRow(i);
//				for(int j=0;j<lastcolum;j++) {
//					String cell =row.getCell(j).toString();
//					
//					System.out.print(" | ");
//					
//				}
//				System.out.println();
				
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	finally {
    		try {
				book.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
		
		
		return data;
    	
    	
    }

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

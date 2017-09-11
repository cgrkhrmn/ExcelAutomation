package utilities;

import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelPractice {

	public static void main(String[] args)  throws Exception{
		// TODO Auto-generated method stub
		/// .xls older version
		String excelFilePath= "./src/test/resources/TestData/AmazonPracticeFile.xls";
		FileInputStream fis= new FileInputStream(excelFilePath);
		//Wokbook
		HSSFWorkbook wb1= new HSSFWorkbook(fis);
		//WorkSheet
		HSSFSheet sh1=wb1.getSheet("Sheet1");
		
		HSSFCell cell=sh1.getRow(2).getCell(1);
	
		
		System.out.println(cell.toString());
		int numOfRows=sh1.getPhysicalNumberOfRows();
		HSSFRow row=sh1.getRow(0);
		int numOfCells=row.getPhysicalNumberOfCells();

		for(int rownum=0;rownum<numOfRows;rownum++){
			for(int cellnum=0;cellnum<numOfCells;cellnum++){
				
				System.out.print(sh1.getRow(rownum).getCell(cellnum)+"--------");
			}
			System.out.println();
		}
		
		
 			
	}

	}


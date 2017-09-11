package excelreadwrite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelConditionalRead {
public static void main(String[] args) throws IOException {
	String excelPath="/Users/cagrikahraman/Desktop/EmpData.xlsx";
	FileInputStream in=new FileInputStream(excelPath);
	
	XSSFWorkbook workbook= new XSSFWorkbook(in);
	
	XSSFSheet worksheet=workbook.getSheet("TestData");
	
	int rowsCount=worksheet.getPhysicalNumberOfRows();
	
	for(int rownum=1;rownum<rowsCount;rownum++){
		String execute=worksheet.getRow(rownum).getCell(0).toString();
		if(execute.equals("Y")){
			String searchItem=worksheet.getRow(rownum).getCell(1).toString();
			System.out.println("Searching for "+searchItem);
		}else{
			
		}
	}
	
	in.close();
	
}
}

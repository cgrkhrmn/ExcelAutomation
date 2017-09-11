package utilities;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
    private static XSSFSheet excelWSheet;
    private static XSSFWorkbook excelWBook;
    private static XSSFCell cell;
    private static XSSFRow row;
    private static String excelFilePath;
  
    
    
    
    //This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method
    public static void openExcelFile(String path,String sheetName) {
    		   excelFilePath=path;
           try {
               // Open the Excel file
            FileInputStream ExcelFile = new FileInputStream(path);
            // Access the required test data sheet
            excelWBook = new XSSFWorkbook(ExcelFile);
            excelWSheet = excelWBook.getSheet(sheetName);
            
            } catch (Exception e){
                e.printStackTrace();
            }
    }
  
    //This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num
    public static String getCellData(int rowNum, int colNum) {
          try{
        	  	  cell = excelWSheet.getRow(rowNum).getCell(colNum);
              String cellData = cell.toString();
              return cellData;
          }catch (Exception e){
        	  		e.printStackTrace();
                return"";
          }
    }
   
   
    
    //This method is to write in the Excel cell, Row num and Col num are the parameters 
	public static void setCellData(String value,  int rowNum, int colNum) {
           try{
              row  = excelWSheet.getRow(rowNum);
              cell = row.getCell(colNum);
                
              if(cell == null) {
                 cell = row.createCell(colNum);
                 cell.setCellValue(value);
              } else {
                 cell.setCellValue(value);
              }
              // Constant variables Test Data path and Test Data file name
              FileOutputStream fileOut = new FileOutputStream(excelFilePath);
              excelWBook.write(fileOut);
               
              fileOut.close();
            }catch(Exception e){
                 e.printStackTrace();
            }
    }
        

    	
    	public static int getUsedRowsCount(){
    		try{
    			int rowCount = excelWSheet.getPhysicalNumberOfRows();
    			return rowCount;
    		}catch (Exception e){
    			e.printStackTrace();
    			return 0;
    		}

    	}
    	
//    	//this method change the background color of the cell
//    	public static String changeColor(int rowNum){
//    		try{
//      	  	  cell = excelWSheet.getRow(rowNum).getCell(3);
//      	  	  
//      	  	  	
//            String cellData = cell.toString();
//
//           //Cell cell1 = getRow(rowNum);
////    	    cell1.setCellValue("ID");
////    	    cell1.setCellStyle(style);
//           
//            if(cellData.equalsIgnoreCase("fail")){
//            	CellStyle colorChange= excelWBook.createCellStyle();
//    			colorChange.setFillForegroundColor(IndexedColors.RED.index);
//    			return cellData;
//            }else if (cellData.equalsIgnoreCase("pass")) {
//            	CellStyle colorChange= excelWBook.createCellStyle();
//            	cell.setCellStyle(colorChange);
//    			colorChange.setFillForegroundColor(IndexedColors.GREEN.index);
//            	return cellData;
//            }else{
//            	CellStyle colorChange= excelWBook.createCellStyle();
//    			colorChange.setFillForegroundColor(IndexedColors.YELLOW.index);
//            	return cellData;
//            }
//        }catch (Exception e){
//      	  		e.printStackTrace();
//              return"";
//    		}
//    		
//    		
//    	}
    	
    
    		
    	
}
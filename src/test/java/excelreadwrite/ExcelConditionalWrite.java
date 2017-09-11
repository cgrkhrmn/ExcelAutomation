package excelreadwrite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelConditionalWrite {

	public static void main(String[] args) throws Exception {

		String excelPath = "/Users/cagrikahraman/Desktop/Bookson.xlsx";

		// Open the file to read

		FileInputStream in = new FileInputStream(excelPath);

		// Let the Apache XSSFWorkbook class handle the data

		XSSFWorkbook workbook = new XSSFWorkbook(in);

		// Jump to Worksheet level

		XSSFSheet worksheet = workbook.getSheet("Sheet1");

		// Look for SearchItem that contains Cucumber

		// Then mark the status as Pass

		// Use for loop, with condition

		int rowsCount = worksheet.getPhysicalNumberOfRows();

		for (int rownum = 1; rownum < rowsCount; rownum++) {

			String searchItem = worksheet.getRow(rownum).getCell(1).toString();

			if (searchItem.contains("Java")) {

				XSSFCell status = worksheet.getRow(rownum).getCell(2);

				if (status == null)

					status = worksheet.getRow(rownum).createCell(2);

				status.setCellValue("Pass");

				break;

			}

		}

		// Save changes

		FileOutputStream ExcelWriteFile = new FileOutputStream(excelPath);

		workbook.write(ExcelWriteFile);

		ExcelWriteFile.close();

		workbook.close();

		in.close();

	}

}

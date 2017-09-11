package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import utilities.ExcelUtils;

public class SqlToExcel {
	private static String URL = "jdbc:mysql://localhost:3306/hr";
	private static String DBUserName = "root";
	private static String DbPassword = "root";
	private static String excelPath="./src/test/resources/TestData/SQL try.xlsx";
	private static String sheetName="Sheet1";
	

	public static void main(String[] args) throws SQLException {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		
		try {
			connection = DriverManager.getConnection(URL, DBUserName, DbPassword);
			System.out.println("Connected to MySQL database .....");
			statement = connection.createStatement();

			resultSet = statement.executeQuery("Select country_id, country_name from countries");
			
			resultSet.next();// how to push the line in the next line
			
			resultSet.last();
			int rowCount=resultSet.getRow();
			resultSet.beforeFirst();
			resultSet.next();
			ExcelUtils.openExcelFile(excelPath, sheetName);

			//System.out.println(resultSet.getString("country_name")+ "'s ID is "+resultSet.getString("country_id"));
			while(resultSet.next()){
				System.out.println(resultSet.getString("country_name")+ "'s ID is "+resultSet.getString("country_id"));
				for (int i = 0; i < rowCount; i++) {
					
				
			//	System.out.println(resultSet.getString("country_name")+ "'s ID is "+resultSet.getString("country_id"));
				
				ExcelUtils.setCellData(resultSet.getString("country_id"), i, 0);
				ExcelUtils.setCellData(resultSet.getString("country_name"), i, 1);
				}
				
			}
			resultSet.previous();
			
			System.out.println(resultSet.getString("country_name")+ "'s ID is "+resultSet.getString("country_id"));
		} catch (SQLException e) {
			System.out.println("something went wrong");
			e.printStackTrace();
		} finally { //finally block will always run  and below conditions make sure that we are not trying to close.
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}

	}
}


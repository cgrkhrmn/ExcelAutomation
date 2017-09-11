package calculator;

import utilities.ExcelUtils;

public class Calculator {
	// Create Calculator class which has 1 static methods called Operate that
	// take 3 parameters : Operation, number1 , number2 to do math operations as
	// above and return result.
	
	String addition="+";
	String subtraction="-";
	String division="/";
	String multiplication="*";
	int result;
	int num1;
	int num2;
	////asdfa oooossssmmmmaaan
	public static double Operate(String operation, double num1, double num2){
		double result=0.0;
		
		switch (operation) {
		case "addition":
			result= num1 + num2;
			break;
		case "subtraction":
			result= num1 - num2;
			break;
		case "division":
			result= num1 / num2;
			break;
		case "multiplication":
			result= num1 * num2;
			break;
		
		}
		
		return result;
		
		
		
	
		
	}
}

package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Driver;



public class CalcPage {
	public CalcPage() {
		PageFactory.initElements(Driver.getInstance(), this);
	}
	
@FindBy (id="lst-ib")
public WebElement searchBox;

@FindBy (id="cwmcwd")
public WebElement calculator;

@FindBy (id="cwtltblr")
public WebElement calculatorBox;

@FindBy (css="span.cwos")
public WebElement calcResult;

	
	


}

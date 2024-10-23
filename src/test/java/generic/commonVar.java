package generic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class commonVar {

	public static final String CONFIG_PATH="./config.properties";
	public static final String XL_PATH="./data/input.xlsx";
	public static ExtentReports extent;
	
	public WebDriver driver;
	public WebDriverWait wait;	
	public ExtentTest test;
}

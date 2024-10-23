package generic;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseTest{
	public static final String CONFIG_PATH="./config.properties";
	public static final String XL_PATH="./data/input.xlsx";
	public static ExtentReports extent;
	
	public WebDriver driver;
	public WebDriverWait wait;	
	public ExtentTest test;
	
	@BeforeSuite
	public void initReport() {
		extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("target/spark.html");
		extent.attachReporter(spark);
	}
	
	@Parameters("property")
	@BeforeMethod
	public void preCondition(Method method,@Optional(CONFIG_PATH) String property) throws Exception {
		
		String testName = method.getName();
		test = 	extent.createTest(testName);
		
		String grid = utility.getproperty(property, "GRID");
		String gridURL = utility.getproperty(property, "GRIDURL");
		String browser = utility.getproperty(property, "BROWSER");
		String appURL = utility.getproperty(property, "APPURL");
		String ITO = utility.getproperty(property, "ITO");
		String ETO = utility.getproperty(property, "ETO");
		
		if (grid.equalsIgnoreCase("yes")) {
			
			if (browser.equalsIgnoreCase("chrome")) {
				test.info("Open Chrome Browser in Remote System");
				driver = new RemoteWebDriver(new URL(gridURL), new ChromeOptions());
			}else if (browser.equalsIgnoreCase("firefox")) {
				test.info("Open firefox Browser in Remote System");
				driver = new RemoteWebDriver(new URL(gridURL), new FirefoxOptions());
			}else {
				test.info("Open Edge Browser in Remote System");
				driver = new RemoteWebDriver(new URL(gridURL), new EdgeOptions());
			}
		}else {
			if (browser.equalsIgnoreCase("chrome")) {
				test.info("Open Chrome Browser in Local System");
				driver = new ChromeDriver();
			}else if (browser.equalsIgnoreCase("firefox")) {
				test.info("Open firefox Browser in Local System");
				driver = new FirefoxDriver();
			}else {
				test.info("Open Edge Browser in Local System");
				driver = new EdgeDriver();
			}
		}
		
		test.info("Enter the URL: "+appURL);
		driver.get(appURL);
		
		test.info("maximize the browser");
		driver.manage().window().maximize();
		
		test.info("Set Implicit Wait: "+ITO);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(ITO)));
		
		test.info("Set Explicit wait: "+ETO);
		wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(ETO)));	
	}

	@AfterMethod
	public void postCondition(ITestResult testResult) throws IOException {
		
		String testName = testResult.getName();
		
		int status = testResult.getStatus();
		if (status == 1) {
			test.pass("Test is Passed");
		}
		else {			
			String timestamp = utility.timestamp();			
			utility.takeScreenshot(driver, "target/"+testName+timestamp+".png");
			test.fail("Test is Failed",MediaEntityBuilder.createScreenCaptureFromPath(testName+timestamp+".png").build());
		}
		test.info("Close the Browser");
		driver.quit();
	}
	
	@AfterSuite
	public void generateReport() {
		extent.flush();
	}

}

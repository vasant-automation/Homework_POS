package script;

import org.testng.Assert;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.utility;
import page.HomePage;
import page.LoginPage;

public class ValidLogin extends BaseTest{
	
	
	@Test(priority=1, enabled=true)
	public void TestValidLogin() {
		
		String un = utility.getXLData(XL_PATH, "ValidLogin", 1, 0);
		String pw  = utility.getXLData(XL_PATH, "ValidLogin", 1, 1);
		
//		1. enter valid un
		test.info("enter valid un: "+un);
		LoginPage loginPage = new LoginPage(driver);
		loginPage.setUserName(un);
		
//		2. enter valid pw
		test.info("enter valid pw: "+pw);
		loginPage.setPassword(pw);
		
//		3. click on login
		test.info("click on login");
		loginPage.clickGoBtn();
		
//		4. home page should be displayed
		test.info("home page should be displayed");
		HomePage homePage = new HomePage(driver);
		boolean result = homePage.verifyHomePageIsDisplayed(wait);
		Assert.assertTrue(result);
	}
	

}

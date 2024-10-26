package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	@FindBy(id="input-username")
	private WebElement unTB;
	
	@FindBy(id="input-password")
	private WebElement pwTB;	
	
	@FindBy(xpath="//button[@name='login-button']")
	private WebElement btnGo;
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		
	}
	
	public void setUserName(String un) {
		unTB.sendKeys(un);
	}
	
	public void setPassword(String pw) {
		pwTB.sendKeys(pw);
	}
	
	public void clickGoBtn() {
		btnGo.click();
	}

}

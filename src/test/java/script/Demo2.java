package script;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import generic.BaseTest;

public class Demo2 extends BaseTest{

	@Test(enabled=false)
	public void TestB() {

		String title = driver.getTitle();
		test.info("Title is: "+title);
		Assert.fail();
		
	}
}

package script;

import org.testng.Reporter;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.utility;

public class Demo1 extends BaseTest{

	@Test
	public void TestA() {
		
		String data = utility.getXLData(XL_PATH, "Sheet1", 0, 0);
		test.info("Data from Excel: "+data);
		String title = driver.getTitle();
		test.info("Title is: "+title);
		
	}
}

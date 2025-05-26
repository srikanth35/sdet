package packagetest;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FirstTest {
	
	@Test
	public void sayHi() {
		System.out.print("Hi");
		 Assert.assertEquals(true, false);
	}
	
	@BeforeTest
	public void sayBefore() {
		System.out.println("Hi Before");
	}

}

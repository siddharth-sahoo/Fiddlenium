package com.awesome.pro.fiddlenium.example;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.awesome.pro.fiddlenium.TestNGBaseDriver;

public class Test1 extends TestNGBaseDriver {

	@Test
	public void test1() throws InterruptedException {
		driver.get("https://google.com");
		sleep(3000);

		GoogleSearch page = PageFactory.initElements(
				this.driver.getDriver(), GoogleSearch.class);
		page.search("gadha");
		sleep(5000);
	}

}

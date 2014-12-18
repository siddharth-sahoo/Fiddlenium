package com.awesome.pro.fiddlenium.impl;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.awesome.pro.fiddlenium.AbstractWebDriver;
import com.awesome.pro.fiddlenium.IWebDriver;

/**
 * Implementation of web driver for Chrome browser.
 * Wrapper of remote web driver having Chrome capabilities.
 * It invokes the executable contained in project accessories.
 * @author siddharth.s
 */
class ChromeWebDriver extends AbstractWebDriver implements IWebDriver {

	/**
	 * Instantiates a new Chrome driver instance.
	 */
	@Override
	public void suiteSetup() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("test-type");
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		this.driver = new ChromeDriver(capabilities);
	}

}

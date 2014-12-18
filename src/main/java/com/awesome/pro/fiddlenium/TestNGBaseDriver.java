package com.awesome.pro.fiddlenium;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.awesome.pro.fiddlenium.impl.WebDriverFactory;
import com.awesome.pro.fiddlenium.references.FiddleniumReferences;
import com.awesome.pro.proxy.INetworkProxy;
import com.awesome.pro.proxy.ProxyManager;
import com.awesome.pro.utilities.PropertyFileUtility;

/**
 * Base driver script which can be extended by test classes.
 * It only provides implementations for before class initializations
 * and after class cleanup activities.
 * This file is used when the Test NG framework is used.
 * @author siddharth.s
 */
public class TestNGBaseDriver {

	/**
	 * Web driver instance.
	 */
	protected IWebDriver driver;
	
	/**
	 * Instance of proxy actions.
	 */
	private INetworkProxy proxy;
	
	/**
	 * Root logger instance.
	 */
	private static final Logger LOGGER = Logger.getLogger(
			TestNGBaseDriver.class);
	
	/**
	 * Will be invoked before tests in a test class are executed.
	 * It is responsible for reading the properties file for browser
	 * preferences and accordingly instantiate the web driver instance.
	 */
	@BeforeClass(alwaysRun = true)
	public void suiteSetup() {
		FiddleniumReferences.initialize();
		if (isProxyNeeded()) {
			LOGGER.info("Starting proxy server.");
			this.proxy = ProxyManager.getInstance(
					FiddleniumReferences.CONFIG_FILE);
		} else {
			LOGGER.info("Not starting proxy server.");
		}
		this.driver = WebDriverFactory.getWebDriverInstance();
		this.driver.suiteSetup();
	}
	
	/**
	 * Will be invoked after all the tests in a test class are executed.
	 * It is responsible for clearing all the browser cookies and closes
	 * the web driver instance.
	 */
	@AfterClass(alwaysRun = true)
	public void suiteTearDown() {
		driver.deleteAllCookies();
		driver.suiteTearDown();
		this.proxy.stop();
	}

	/**
	 * @return Whether to start proxy server as configured in property file.
	 */
	private boolean isProxyNeeded() {
		final PropertyFileUtility config = new PropertyFileUtility(
				FiddleniumReferences.CONFIG_FILE);
		return config.getBooleanValue(
				FiddleniumReferences.PROPERTY_ENABLE_PROXY,
				FiddleniumReferences.DEFAULT_ENABLE_PROXY);
	}

	/**
	 * @param milliseconds Number of milliseconds to sleep.
	 */
	public void sleep(long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			LOGGER.error("Interrupted.", e);
		}
	}

	/**
	 * Restarts the browser, clears the traffic.
	 */
	public void reset() {
		this.driver.resetBrowser();
		this.proxy.clear();
	}

}

package com.awesome.pro.fiddlenium.impl;

import org.apache.log4j.Logger;

import com.awesome.pro.fiddlenium.Browser;
import com.awesome.pro.fiddlenium.IWebDriver;
import com.awesome.pro.fiddlenium.references.FiddleniumReferences;


/**
 * Instantiates the appropriate driver and returns the driver object.
 * @author siddharth.s
 */
public class WebDriverFactory {

	/**
	 * Root logger instance.
	 */
	private static final Logger LOGGER = org.apache.log4j.Logger.getLogger(
			WebDriverFactory.class);


	private final static class Holder {
		private static IWebDriver INSTANCE;
	}

	/**
	 * @return Existing web driver instance, will be null if not running.
	 */
	public static IWebDriver getExistingWebDriverInstance() {
		return Holder.INSTANCE;
	}

	/**
	 * @return Instantiated Web Driver according to input parameter.
	 * It also replaces the stored instance with the new one.
	 */
	public static IWebDriver getWebDriverInstance() {
		Browser browser = getBrowser();
		switch(browser) {
		case CHROME :
			Holder.INSTANCE = new ChromeWebDriver();
			return Holder.INSTANCE;

		case FIREFOX :
			Holder.INSTANCE = new FirefoxWebDriver();
			return Holder.INSTANCE;

		case IE :
			Holder.INSTANCE = new IEWebDriver();
			return Holder.INSTANCE;

		case SAFARI :
			Holder.INSTANCE = new SafariWebDriver();
			return Holder.INSTANCE;

		default: 
			LOGGER.error("Unhandled browser: " + browser);
			throw new Error("Unhandled browser: " + browser);
		}
	}

	/**
	 * Reads the properties file for the browser parameter.
	 * @return Parsed browser enumeration object.
	 */
	public static final Browser getBrowser() {
		final String browserString = FiddleniumReferences.CONFIG.
				getStringValue(FiddleniumReferences.PROPERTY_BROWSER,
						FiddleniumReferences.DEFAULT_BROWSER);
		Browser browser = Browser.parseBrowser(browserString);

		if(browser == null) {
			LOGGER.warn("Unable to parse specified browser: "
					+ browserString + ". Continuing with "
					+ FiddleniumReferences.DEFAULT_BROWSER);
			browser = FiddleniumReferences.DEFAULT_BROWSER_ENUM;
		}
		return browser;
	}

	/**
	 * @param browser Browser instance to open.
	 * @return A new driver instance of the specified browser
	 * without storing it.
	 */
	public static final IWebDriver getDriver(final Browser browser) {
		switch (browser) {
		case CHROME:
			return new ChromeWebDriver();
		case FIREFOX:
			return new FirefoxWebDriver();
		case IE:
			return new IEWebDriver();
		case SAFARI:
			return new SafariWebDriver();
		default:
			throw new Error("Unhandled browser: " + browser.toString());	
		}
	}

}

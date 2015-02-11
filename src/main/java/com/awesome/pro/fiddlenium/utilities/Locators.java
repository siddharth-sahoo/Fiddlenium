package com.awesome.pro.fiddlenium.utilities;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.awesome.pro.fiddlenium.IWebDriver;
import com.awesome.pro.fiddlenium.references.FiddleniumReferences;


public class Locators {
	
	private static final Logger LOGGER = Logger.getLogger(Locators.class);
	
	/**
	 * Default maximum time for which web driver will try to
	 * locate a specified element.
	 */
	private static final long CONST_DEFAULT_TIMEOUT_SECONDS
		= FiddleniumReferences.LOCATOR_TIME_OUT_SECONDS;
	
	/**
	 * Default polling time at the interval of which web driver
	 * will try to locate the specified element.
	 */
	private static final long CONST_DEFAULT_POLLING_INTERVAL_MILISECONDS
		= FiddleniumReferences.LOCATOR_POLL_TIME_MILLISECONDS;

	/**
	 * @param locatorType Locator type - xpath, id etc.
	 * @param expression Locator expression according to the type.
	 * @return Locator which can be used in web driver.
	 */
	public static By getLocator(String locatorType, String expression) {
		LocatorType parsedLocatorType = LocatorType.parseLocator(locatorType);
		
		if(parsedLocatorType == null || expression == null) {
			LOGGER.error("Invalid locator specified: " + locatorType
					+ " " + expression);
			return null;
		}
			
		switch (parsedLocatorType) {
			case XPATH:
				return By.xpath(expression);
			
			case CSS_SELECTOR:
				return By.cssSelector(expression);
		
			case ID:
				return By.id(expression);
		
			case NAME:
				return By.name(expression);
		
			case CLASS_NAME:
				return By.className(expression);
		
			case TAG_NAME:
				return By.tagName(expression);
		
			case LINK_TEXT:
				return By.linkText(expression);
				
			case PARTIAL_LINK_TEXT:
				return By.partialLinkText(expression);
				
			default:
				LOGGER.error("Unhandled locator type: " + parsedLocatorType.toString());
				return null;
		}
	}

	/**
	 * @param locatorType Locator type - xpath, id etc.
	 * @param expression Locator expression according to the type.
	 * @return Locator which can be used in web driver.
	 */
	public static By getLocator(LocatorType locatorType, String expression) {
		if(locatorType == null || expression == null) {
			LOGGER.error("Invalid locator specified: " + locatorType
					+ " " + expression);
			return null;
		}

		switch (locatorType) {
			case XPATH:
				return By.xpath(expression);
			
			case CSS_SELECTOR:
				return By.cssSelector(expression);
		
			case ID:
				return By.id(expression);
		
			case NAME:
				return By.name(expression);
		
			case CLASS_NAME:
				return By.className(expression);
		
			case TAG_NAME:
				return By.tagName(expression);
		
			case LINK_TEXT:
				return By.linkText(expression);
				
			case PARTIAL_LINK_TEXT:
				return By.partialLinkText(expression);
				
			default:
				LOGGER.error("Unhandled locator type: " + locatorType.toString());
				return null;
		}
	}

	/**
	 * @param driver Webdriver implementation.
	 * @param locator Selenium locator using which web driver will try
	 * to find the web element.
	 * @return If found, reference to the web element is returned.
	 * Else, null is returned.
	 */
	public static WebElement find(IWebDriver driver, By locator) {
		return find(driver, locator, CONST_DEFAULT_TIMEOUT_SECONDS, 
				CONST_DEFAULT_POLLING_INTERVAL_MILISECONDS);
	}
	
	/**
	 * @param driver Webdriver implementation.
	 * @param locator Selenium locator using which web driver will try
	 * to find the web element.
	 * @param timeout Maximum time until which web driver will try to find
	 * the specified web element on a polling basis. It is expressed in seconds.
	 * @param pollingInterval Intervals at which web driver will try to
	 * find the specified web element. It is expressed in milli seconds.
	 * @return If found, reference to the web element is returned.
	 * Else, null is returned.
	 */
	public static WebElement find(IWebDriver driver, By locator, long timeout, long pollingInterval) {
		double timeSpent = 0;
		while (timeSpent < timeout) {
			WebElement element = driver.findElement(locator);
			if (element != null)
				return element;
			
			sleeping(pollingInterval);
			timeSpent += pollingInterval/1000;
		}
		return null;
	}	

	/**
	 * @param pollingInterval Sleeps the active thread for
	 * the specified duration
	 */
	private static void sleeping(long sleepDuration) {
		try {
			Thread.sleep(sleepDuration);
		}
		catch (InterruptedException e) {
			LOGGER.error("Interrupted while sleeping.", e);
		}
	}

}

package com.awesome.pro.fiddlenium;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebElement;

import com.awesome.pro.fiddlenium.AbstractWebDriver.JSExecutor;

/**
 * Web driver instance which can be implemented by specific
 * browser drivers.
 * @author siddharth.s
 */
public interface IWebDriver {

	/**
	 * Instantiate web driver with required capabilities.
	 */
	void suiteSetup();

	/**
	 * Shut down the web driver instance.
	 */
	void suiteTearDown();

	/**
	 * Navigates the driver to the specified URL.
	 * @param url Address of the page.
	 */
	void get(String url);

	/**
	 * @return URL of the current page.
	 */
	String getCurrentUrl();

	/**
	 * @return HTML source of the current page as a String.
	 */
	String getPageSource();

	/**
	 * @return Title of the current page.
	 */
	String getTitle();

	/**
	 * @return Window handle of the current page.
	 */
	String getWindowHandle();

	/**
	 * @return Set of all the window handles.
	 */
	Set<String> getWindowHandles();

	/**
	 * @return The options interface which has advanced actions including
	 * interaction with cookies.
	 */
	Options manage();

	/**
	 * @return The navigation interface which allows page naviagation
	 * actions for web driver.
	 */
	Navigation navigate();

	/**
	 * Finds a single web element.
	 * @param by Locator to find the element.
	 * @return Reference to the web element.
	 */
	WebElement findElement(By by);

	/**
	 * Finds multiple web elements.
	 * @param by Locator to find the element.
	 * @return Reference to the web element.
	 */
	List<WebElement> findElements(By by);

	/**
	 * Switch to frame defined by the locator parameters.
	 * @param locatorType Locator type. e.g. id, xpath etc.
	 * @param locatorExpression Expression to be used for the locator.
	 */
	void switchToFrame(String locatorType, String locatorExpression);

	/**
	 * Switches focus to the default content.
	 */
	void switchToDefaultContent();

	/**
	 * @return JS executor instance.
	 */
	JSExecutor getJSExecutor();

	/**
	 * Resets the browser and the proxy.
	 */
	void resetBrowser();

	/**
	 * Delete all cookies present in the browser
	 */
	void deleteAllCookies();

	/**
	 * Reloads the currently visited page.
	 */
	void refreshPage();

	/**
	 * @return Target locator instance to specify target of switch.
	 */
	TargetLocator switchTo();

	/**
	 * @return Selenium web driver instance.
	 */
	WebDriver getDriver();

	/**
	 * @return Screenshot bytes which can be written to a file.
	 */
	byte[] getScreenshotAsBytes();

}

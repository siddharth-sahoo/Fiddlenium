package com.awesome.pro.fiddlenium;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebElement;

import com.awesome.pro.fiddlenium.utilities.Locators;
import com.awesome.pro.utilities.Assert;

/**
 * Base web driver abstract class which implements the common methods
 * that don't vary across browser specific implementations.
 * It can be inherited by specific browser implementations.
 * This only includes actions that are implemented using Web Driver. 
 * @author siddharth.s
 */
public abstract class AbstractWebDriver implements IWebDriver {

	/**
	 * Web driver instance which is wrapped.
	 */
	protected WebDriver driver;
	
	/**
	 * Shuts down the web driver instance.
	 */
	@Override
	public void suiteTearDown() {
		//FIXME: Verify the following is not required.
		//deleteAllCookies();
		this.driver.quit();
	}	

	/**
	 * Navigates the web driver instance to the specified URL.
	 * @param url URL to visit.
	 */
	@Override
	public void get(String url) {
		this.driver.get(url);
	}
	
	/**
	 * @return URL of the current page of the driver instance.
	 */
	@Override
	public String getCurrentUrl() {
		return this.driver.getCurrentUrl();
	}
	
	/**
	 * @return HTML source of the current page.
	 */
	@Override
	public String getPageSource() {
		return this.driver.getPageSource();
	}
	
	/**
	 * @return Title of current page.
	 */
	@Override
	public String getTitle() {
		return this.driver.getTitle();
	}
	
	/**
	 * @return Window handle of the currently active window.
	 */
	@Override
	public String getWindowHandle() {
		return this.driver.getWindowHandle();
	}
	
	/**
	 * @return Set of window handles of all the open windows.
	 */
	@Override
	public Set<String> getWindowHandles() {
		return this.driver.getWindowHandles();
	}
	
	/**
	 * The options interface which has advanced actions
	 * including interaction with cookies.
	 */
	@Override
	public Options manage() {
		return this.driver.manage();
	}
	
	/**
	 * @return The navigation interface which allows page naviagation
	 * actions for web driver.
	 */
	@Override
	public Navigation navigate() {
		return this.driver.navigate();
	}
	
	/**
	 * Finds a single web element.
	 * @param by Locator to find the element.
	 * @return Reference to the web element.
	 */
	@Override
	public WebElement findElement(By by) {
		return this.driver.findElement(by);
	}
	
	/**
	 * Finds multiple web elements.
	 * @param by Locator to find the element.
	 * @return Reference to the web element.
	 */
	@Override
	public List<WebElement> findElements(By by) {
		return this.driver.findElements(by);
	}
	
	@Override
	public void switchToFrame(String locatorType, String locatorExpression) {
		By locator = Locators.getLocator(locatorType, locatorExpression);
		WebElement frame = driver.findElement(locator);
		this.driver.switchTo().frame(frame);
	}
	
	@Override
	public void switchToDefaultContent() {
		this.driver.switchTo().defaultContent();
	}
	
	@Override
	public JSExecutor getJSExecutor() {
		return new JSExecutor(this.driver);
	}
	
	@Override
	public void deleteAllCookies(){
		driver.manage().deleteAllCookies();
		
	}
	
	@Override
	public void resetBrowser() {
		deleteAllCookies();
		suiteTearDown();
		suiteSetup();
	}
	
	@Override
	public void refreshPage() {
		driver.navigate().refresh();
	}
	
	@Override
	public TargetLocator switchTo() {
		return this.driver.switchTo();
	}
	
	@Override
	public WebDriver getDriver() {
		return this.driver;
	}

	@Override
	public byte[] getScreenshotAsBytes() {
		return ((TakesScreenshot)this.driver).getScreenshotAs(
				OutputType.BYTES);
	}
	
	/**
	 * Utility to execute JavaScript in the browser context.
	 * Wrapper on Selenium JavaScript Executor.
	 * @author siddharth.s
	 */
	public class JSExecutor {

		/**
		 * JavaScript executor instance.
		 */
		private JavascriptExecutor driver;

		/**
		 * JavaScript executor utility executor constructor.
		 * @param webDriver Web driver instance.
		 */
		private JSExecutor(WebDriver webDriver) {
			this.driver = (JavascriptExecutor) webDriver;
			webDriver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
		}

		/**
		 * @return Boolean status of whether JavaScript is enabled in the browser.
		 */
		public boolean isJSEnabled() {
			Object response = driver.executeScript("return true;");
			return response != null;
		}

		/**
		 * @param script JavaScript to be executed.
		 * It should be in the format: return ...
		 * Else a null pointer exception will be thrown.
		 * @return Return value of the script.
		 */
		public Object executeJS(String script) {
			return driver.executeScript(script);
		}

		/**
		 * @param script JavaScript to execute.
		 * It should be in the format: return ...
		 * Else a null pointer exception will be thrown.
		 * @param args Arguments that can be passed to the JavaScript.
		 * @return Return value of the script.
		 */
		public Object executeJS(String script, Object... args) {
			return driver.executeScript(script, args);
		}

		/**
		 * @param script JavaScript to be executed in an asynchronous manner.
		 * It should be in the format: return ...
		 * Else a null pointer exception will be thrown.
		 * @return Return value of the script.
		 */
		public Object executeAsyncJS(String script) {
			return driver.executeAsyncScript(script);
		}

		/**
		 * Executes the specified script and asserts that the response
		 * is not null.
		 * @param script JavaScript to be executed.
		 */
		public void executeAssertNotNull(String script) {
			try {
				Assert.assertNotNull(executeJS(script));
			} catch (Exception e) {
				Assert.assertEquals("Script threw an exception: " + script,
						"Check the script.");
			}
		}

		/**
		 * Executes the specified JavaScript and asserts that the actual output
		 * matches the expected output.
		 * @param script JavaScript to be executed.
		 * @param expected Expected output of the JavaScript.
		 */
		public void executeAssertEquals(String script, String expected) {
			try {
				Assert.assertEquals(executeJS(script).toString(), expected);
			} catch (Exception e) {
				Assert.assertEquals("Script threw an exception: " + script,
						"Check the script.");
			}
		}

	}
}

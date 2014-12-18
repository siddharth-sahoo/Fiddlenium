package com.awesome.pro.fiddlenium.references;

import com.awesome.pro.fiddlenium.Browser;
import com.awesome.pro.utilities.PropertyFileUtility;

/**
 * References used in Fiddlenium.
 * @author siddharth.s
 */
public class FiddleniumReferences {

	// Locator time out intervals.
	public final static long LOCATOR_TIME_OUT_SECONDS = 30;
	public final static long LOCATOR_POLL_TIME_MILLISECONDS = 500;

	// Configuration file.
	public static PropertyFileUtility CONFIG;
	public static final String CONFIG_FILE = "Fiddlenium.properties";

	// Configuration properties.
	public final static String PROPERTY_BROWSER = "browser";

	// Default configurations.
	public static final String DEFAULT_BROWSER = "chrome";
	public static final Browser DEFAULT_BROWSER_ENUM = Browser.CHROME;

	public static final String PROXY_PAGE_KEY = "page";
	public static final String FIDDLER_CONF_FILE = "FiddlerConfig.properties";

	/**
	 * @param configFile Configuration file to be read for Fiddlenium.
	 */
	public static final void initialize() {
		CONFIG = new PropertyFileUtility(CONFIG_FILE);
	}

}

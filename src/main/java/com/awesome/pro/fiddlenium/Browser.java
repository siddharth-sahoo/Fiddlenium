package com.awesome.pro.fiddlenium;

/**
 * Enumeration of supported browsers.
 * @author siddharth.s
 */
public enum Browser {
	
	CHROME,
	
	FIREFOX,
	
	IE,
	
	SAFARI;
	
	/**
	 * @param browser Browser in string format.
	 * @return Parsed browser enumeration object.
	 */
	public static Browser parseBrowser(final String browser) {
		switch(browser.toLowerCase()) {
			case "chrome" : return Browser.CHROME;
			case "ff" : return Browser.FIREFOX;
			case "firefox" : return Browser.FIREFOX;
			case "safari" : return Browser.SAFARI;
			case "ie" : return Browser.IE;
			case "internetexplorer" : return Browser.IE;
			default : return null;
		}
	}

}

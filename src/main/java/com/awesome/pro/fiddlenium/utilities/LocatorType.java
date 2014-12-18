package com.awesome.pro.fiddlenium.utilities;

/**
 * Enumeration of the supported locators to find a web
 * element in web driver.
 * @author siddharth.s
 */
public enum LocatorType {
	
	CLASS_NAME,
	
	CSS_SELECTOR,
	
	XPATH,
	
	ID,
	
	LINK_TEXT,
	
	NAME,
	
	PARTIAL_LINK_TEXT,
	
	TAG_NAME;
	
	/**
     * @param locator String formatted locator. 
     * @return Parsed locator type enumeration object.
     */
	public static LocatorType parseLocator(String locator) {
		locator = locator.trim().toLowerCase();
		
		if (locator.startsWith("xpath"))
			return LocatorType.XPATH;
		
		else if (locator.startsWith("css"))
			return LocatorType.CSS_SELECTOR;
		
		else if (locator.startsWith("name"))
			return LocatorType.NAME;
		
		else if (locator.startsWith("class"))
			return LocatorType.CLASS_NAME;
		
		else if (locator.startsWith("tag"))
			return LocatorType.TAG_NAME;
		
		else if (locator.startsWith("link"))
			return LocatorType.LINK_TEXT;
		
		else if (locator.startsWith("id"))
			return LocatorType.ID;
		
		return null;
	}

}

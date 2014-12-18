package com.awesome.pro.fiddlenium.impl;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import com.awesome.pro.fiddlenium.AbstractWebDriver;
import com.awesome.pro.fiddlenium.IWebDriver;

/**
 * Implementation of web driver for the Safari browser.
 * Wrapper of Safari driver.
 * It needs Safari to be installed in the system where it
 * is being run.
 * @author siddharth.s
 */
class SafariWebDriver extends AbstractWebDriver implements IWebDriver {

	/**
	 * Instantiates a new Safari driver.
	 */
	@Override
	public void suiteSetup() {
		DesiredCapabilities capabilities = DesiredCapabilities.safari();
		capabilities.setBrowserName("safari");
		capabilities.setJavascriptEnabled(true);
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		this.driver = new SafariDriver(capabilities);
	}

}

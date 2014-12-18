package com.awesome.pro.fiddlenium.impl;

import java.io.File;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.awesome.pro.fiddlenium.AbstractWebDriver;
import com.awesome.pro.fiddlenium.IWebDriver;

/**
 * Implementation of web driver for IE browser.
 * Wrapper of IE driver service builder.
 * It invokes driver server included in project accessories.
 * @author siddharth.s
 */
class IEWebDriver extends AbstractWebDriver implements IWebDriver {

	/**
	 * IE driver service instance.
	 */
	private InternetExplorerDriverService service;

	@Override
	public void suiteSetup() {
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);			
		this.service =  new InternetExplorerDriverService.Builder()
		.usingDriverExecutable(new File("src/main/resources/IEDriverServer.exe"))
		.usingAnyFreePort()
		.build();
		this.driver = new InternetExplorerDriver(this.service, capabilities);
	}

	@Override
	public void suiteTearDown() {
		super.suiteTearDown();
		if(this.service != null) {
			if(this.service.isRunning())
				this.service.stop();
		}
	}

}

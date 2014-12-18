package com.awesome.pro.fiddlenium.impl;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.awesome.pro.fiddlenium.AbstractWebDriver;
import com.awesome.pro.fiddlenium.IWebDriver;
/**
 * Implementation of web driver for Firefox browser.
 * Wrapper of Firefox driver.
 * It needs Firefox to be installed on the system where
 * it is being run.
 * @author siddharth.s
 */
class FirefoxWebDriver extends AbstractWebDriver implements IWebDriver {

	/**
	 * Instantiates a new Firefox driver.
	 */
	@Override
	public void suiteSetup() {
		FirefoxProfile profile = new FirefoxProfile();

		profile.setEnableNativeEvents(false);
		profile = handleCertificateErrors(profile);
		//profile = installFirebug(profile);

		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability(FirefoxDriver.PROFILE, profile);
		this.driver = new FirefoxDriver(capabilities);
	}

	/**
	 * Configures Firefox profile to ignore certificate errors.
	 * @param profile Firefox profile instance.
	 * @return Updated Firefox profile.
	 */
	private FirefoxProfile handleCertificateErrors(FirefoxProfile profile) {
		profile.setAcceptUntrustedCertificates(true);
		profile.setAssumeUntrustedCertificateIssuer(false);
		profile.setPreference("security.default_personal_cert", "Select Automatically");
		return profile;
	}

	/**
	 * Checks the configuration file to determine whether Firebug should
	 * be installed and then installs the extension.
	 * @param profile Firefox profile instance.
	 * @return Updated Firefox profile.
	 */
	@SuppressWarnings("unused")
	private FirefoxProfile installFirebug(FirefoxProfile profile) {
		File firebug = new File("accessories/firebug-1.12.5-fx.xpi");
		try {
			profile.addExtension(firebug);
		} catch (IOException e) {
			System.out.println("ERROR: Unable to find or load Firebug: " + firebug.getPath());
			System.exit(1);
		}
		profile.setPreference("app.update.enabled", false);
		String domain = "extensions.firebug.";
		profile.setPreference(domain + "currentVersion", "1.12.5");
		profile.setPreference(domain + "allPagesActivation", "on");
		profile.setPreference(domain + "defaultPanelName", "net");
		profile.setPreference(domain + "net.enableSites", true);
		return profile;
	}

}

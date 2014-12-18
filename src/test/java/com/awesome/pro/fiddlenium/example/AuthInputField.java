package com.awesome.pro.fiddlenium.example;

import org.openqa.selenium.WebElement;

public class AuthInputField {

	private WebElement usernameField;

	private WebElement passwordField;

	private WebElement submitButton;

	// Construtor.
	public AuthInputField() { }

	public void authenticate(final String user, final String password) {
		usernameField.sendKeys(user);
		passwordField.sendKeys(password);
		submitButton.click();
	}

}

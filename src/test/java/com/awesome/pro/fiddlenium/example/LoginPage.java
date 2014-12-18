package com.awesome.pro.fiddlenium.example;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {

	@FindBy(how = How.ID, using = "username")
	private WebElement usernameField;

	@FindBy(how = How.ID, using = "password")
	private WebElement passwordField;

	@FindBy(how = How.XPATH, using = ".//*[@id='login']/button")
	private WebElement submitButton;

	private AuthInputField authField;

}

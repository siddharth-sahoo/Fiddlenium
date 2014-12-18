package com.awesome.pro.fiddlenium.example;

public class GoogleSearch {

	@org.openqa.selenium.support.FindBy(how = org.openqa.selenium.support.How.NAME, using = "q")
	private org.openqa.selenium.WebElement searchBox;

	public void search(final String text) {
		searchBox.sendKeys(text);
		searchBox.submit();
	}

}

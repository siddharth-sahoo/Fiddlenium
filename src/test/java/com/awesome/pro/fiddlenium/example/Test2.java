package com.awesome.pro.fiddlenium.example;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.awesome.pro.fiddlenium.TestNGBaseDriver;
import com.awesome.pro.utilities.AnnotationMemberType;
import com.awesome.pro.utilities.CompilerTools;

public class Test2 extends TestNGBaseDriver {

	@Test
	public void test1() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		Set<String> elements = new HashSet<>();
		elements.add("private org.openqa.selenium.WebElement searchBox;");

		Set<String> methods = new HashSet<>();
		methods.add("public void search(String text) { "
				+ "		CharSequence[] temp = new CharSequence[1]; "
				+ "		temp[0] = text; "
				+ "		searchBox.sendKeys(temp); "
				+ "		searchBox.submit(); "
				+ "}");

		CompilerTools.compile("com.pages.GoogleHomePage", elements, methods);

		Map<String, Entry<String, AnnotationMemberType>> annotationMembers = new HashMap<>();
		annotationMembers.put("how", new AbstractMap.SimpleEntry<>(
				"org.openqa.selenium.support.How~NAME",
				AnnotationMemberType.ENUM));
		annotationMembers.put("using", new AbstractMap.SimpleEntry<>(
				"q", AnnotationMemberType.STRING));

		CompilerTools.annotateField("com.pages.GoogleHomePage", "searchBox",
				"org.openqa.selenium.support.FindBy", annotationMembers);

		driver.get("https://www.google.com");
		sleep(2000);

		Object page = CompilerTools.instantiate("com.pages.GoogleHomePage");
		PageFactory.initElements(driver.getDriver(), page);

		Method method = page.getClass().getDeclaredMethod("search", String.class);
		method.invoke(page, "gadha");
		sleep(5000);
	}

}

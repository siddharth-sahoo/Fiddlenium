package com.awesome.pro.fiddlenium.example;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.awesome.pro.fiddlenium.TestNGBaseDriver;
import com.awesome.pro.fiddlenium.utilities.PageObjectFactory;

public class Test3 extends TestNGBaseDriver {

	@Test
	public void test() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		driver.get("http://the-internet.herokuapp.com/login");
		
		Object page = PageObjectFactory.instantiate("com.awesome.pro.fiddlenium.example.LoginPage", driver.getDriver());
		
		Field authField = page.getClass().getDeclaredField("authField");
		authField.setAccessible(true);
		Object value = authField.get(page);
		Method method = value.getClass().getDeclaredMethod("authenticate", String.class, String.class);
		Object[] userpwd = {"tomsmith", "SuperSecretPassword!"};
		method.invoke(value, userpwd);
		sleep(2000);
	}

}

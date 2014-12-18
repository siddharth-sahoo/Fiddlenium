package com.awesome.pro.fiddlenium.utilities;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.awesome.pro.utilities.AnnotationMemberType;
import com.awesome.pro.utilities.CompilerTools;

/**
 * Wrapper around Selenium page factory to incorporate runtime class
 * declarations and annotations.
 * @author siddharth.s
 */
public class PageObjectFactory {

	/**
	 * Root logger instance.
	 */
	private static final Logger LOGGER = Logger.getLogger(
			PageObjectFactory.class);

	/**
	 * Fully qualified name of the annotation to find web elements.
	 */
	public static final String ANN_FIND_BY = "org.openqa.selenium.support.FindBy";

	/**
	 * @param name Fully qualified name of the page object class.
	 * @param fields 
	 */
	public static final void declarePageObject(final String name,
			final Map<String, Map<String, Entry<String, AnnotationMemberType>>> fields) {
		CompilerTools.compile(name, fields.keySet(), null);

		final Iterator<Entry<String, Map<String, Entry<String, AnnotationMemberType>>>> iter =
				fields.entrySet().iterator();
		while (iter.hasNext()) {
			final Entry<String, Map<String, Entry<String, AnnotationMemberType>>> entry =
					iter.next();
			CompilerTools.annotateField(name, entry.getKey(),
					ANN_FIND_BY, entry.getValue());
		}
		LOGGER.info("Declared page object class: " + name);
	}

	/**
	 * @param name Fully qualified name of the page object.
	 * @param driver Web driver instance.
	 * @return Page object instance.
	 */
	public static final Object instantiate(final String name,
			final WebDriver driver) {
		Object page = CompilerTools.instantiate(name);
		PageFactory.initElements(driver, page);
		page = populateFields(page);
		return page;
	}

	/**
	 * @param object Object to re-populate following internal conventions.
	 * @return Re-populated object.
	 */
	private static final Object populateFields(final Object object) {
		if (object == null) {
			LOGGER.warn("Object not populated at all.");
			return null;
		}

		final Field[] fields = object.getClass().getDeclaredFields();

		try {
		for (int i = 0; i < fields.length; i ++) {
			fields[i].setAccessible(true);
			if (fields[i].get(object) != null) {
				continue;
			}

			Object temp = fields[i].getType().newInstance();
			Field[] tempFields = temp.getClass().getDeclaredFields();

			for (int j = 0; j < tempFields.length; j ++) {
				tempFields[j].setAccessible(true);
				Field tempField = object.getClass().getDeclaredField(
						tempFields[j].getName());
				tempField.setAccessible(true);
				tempFields[j].set(temp, tempField.get(object));
			}
			fields[i].set(object, temp);
		}
		} catch (IllegalAccessException | IllegalArgumentException
				| InstantiationException | NoSuchFieldException
				| SecurityException e) {
			LOGGER.error("Instatiation error.", e);
			return object;
		}
		return object;
	}

	// Private constructor for a utility class.
	private PageObjectFactory() { }

}

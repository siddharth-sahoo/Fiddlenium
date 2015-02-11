package com.awesome.pro.fiddlenium.example;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.awesome.pro.fiddlenium.TestNGBaseDriver;
import com.awesome.pro.utilities.network.IHTTPResponse;

public class Test4 extends TestNGBaseDriver {

	private static final Logger LOGGER = Logger.getLogger(Test4.class);

	@Test
	public void test() {
		driver.get("https://google.com");
		sleep(5000);
		Map<String, List<IHTTPResponse>> traffic = proxy.getAllTraffic();
		Iterator<Entry<String, List<IHTTPResponse>>> iter = traffic.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, List<IHTTPResponse>> entry = iter.next();
			LOGGER.info("Page: " + entry.getKey());
			int size = entry.getValue().size();
			for (int i = 0; i < size; i ++) {
				LOGGER.info(entry.getValue().get(i).getBaseURL());
			}
			LOGGER.info("--------------");
		}
	}

}

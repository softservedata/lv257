package com.softserve.training;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Some {
	public static final Logger logger = LoggerFactory.getLogger(Some.class); // LoggerFactory

	public void someMethod() {
		logger.error("Some Error");
		logger.warn("Some Warning");
		logger.info("Some Info");
		logger.debug("Some Debug");
		logger.trace("Some Trace");
	}
}

package com.softserve.edu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
	public static final Logger logger = LoggerFactory.getLogger(App.class); // org.slf4j.LoggerFactory
	//public static final Logger logger = Logger.getLogger(App.class); //
	// org.apache.log4j.Logger

	public static void main(String[] args) {
		System.out.println("Hello World!");
		System.out.println("Thread id = " + Thread.currentThread().getId());
		System.out.println("Thread id = " + Thread.currentThread().getName());
		//
		logger.error("App Error, Thread id = " + Thread.currentThread().getId());
		logger.warn("App Warning, Thread id = " + Thread.currentThread().getId());
		logger.info("App Info, Thread id = " + Thread.currentThread().getId());
		logger.debug("App Debug, Thread id = " + Thread.currentThread().getId());
		logger.trace("App Trace, Thread id = " + Thread.currentThread().getId());
	}

}

package com.softserve.edu;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserve.training.Calc;
import com.softserve.training.Some;

public class App2 {
    Calendar calendar;
    
	public static final Logger logger = LoggerFactory.getLogger(App.class); // org.slf4j.LoggerFactory
	//public static final Logger logger = Logger.getLogger(App.class); // org.apache.log4j.Logger

	public static void main(String[] args) {
		System.out.println("Hello from App:");
		App2 app = new App2();
		Calc calc = new Calc();
		Some some = new Some();

		app.appMethod();
		calc.calcMethod();
		some.someMethod();
		//
	}

	public void appMethod() {
		logger.error("App Error");
		logger.warn("App Warning");
		logger.info("App Info");
		logger.debug("App Debug");
		logger.trace("App Trace");
		//
		for (int i=0;i<7;i++){
			logger.error("ha7-ha-ha-ha-ha-ha-ha-ha-ha-ha-ha-ha-ha-ha-ha-ha-ha");
		}
	}
}

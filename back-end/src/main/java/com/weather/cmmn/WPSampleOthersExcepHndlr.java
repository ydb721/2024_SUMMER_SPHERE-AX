package com.weather.cmmn;

import org.egovframe.rte.fdl.cmmn.exception.handler.ExceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WPSampleOthersExcepHndlr implements ExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(WPSampleOthersExcepHndlr.class);

	@Override
	public void occur(Exception exception, String packageName) {
		LOGGER.debug(" WPServiceExceptionHandler run...............");
	}

}

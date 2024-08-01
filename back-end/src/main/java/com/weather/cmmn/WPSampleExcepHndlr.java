package com.weather.cmmn;
import org.egovframe.rte.fdl.cmmn.exception.handler.ExceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WPSampleExcepHndlr implements ExceptionHandler {

	// logger 인스턴스 생성
	private static final Logger LOGGER = LoggerFactory.getLogger(WPSampleExcepHndlr.class);

	// occur 메소드 구현
	@Override
	public void occur(Exception ex, String packageName) {
		LOGGER.debug(" WPServiceExceptionHandler run...............");
	}
}

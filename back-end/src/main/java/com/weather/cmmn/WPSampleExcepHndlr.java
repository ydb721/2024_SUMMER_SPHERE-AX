package com.weather.cmmn;

import org.egovframe.rte.fdl.cmmn.exception.handler.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WPSampleExcepHndlr implements ExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(WPSampleExcepHndlr.class);

    // ExceptionHandler 인터페이스의 기본 메소드 구현
    @Override
    public void occur(Exception ex, String packageName) {
        LOGGER.error("Exception occurred in package: " + packageName, ex);
    }

    // HttpServletRequest, HttpServletResponse 처리하는 추가 메소드
    public void occur(Exception ex, String packageName, HttpServletRequest request, HttpServletResponse response) {
        LOGGER.error("Exception occurred in package: " + packageName, ex);

        try {
            // 에러 메시지를 request 속성에 추가
            request.setAttribute("errorMessage", ex.getMessage());

            // error.jsp로 포워딩
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            LOGGER.error("Failed to forward to the error page", e);
            throw new RuntimeException("Error while handling the exception", e);
        }
    }
}

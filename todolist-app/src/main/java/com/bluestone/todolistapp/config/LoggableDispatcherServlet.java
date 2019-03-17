package com.bluestone.todolistapp.config;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import com.bluestone.todolistapp.model.LogMessage;
import com.bluestone.todolistapp.serviceImpl.LoginService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class LoggableDispatcherServlet extends DispatcherServlet{
	@Autowired
	ObjectMapper mapper;
	
	private static final Logger logger = LogManager.getLogger(LoginService.class);

	    @Override
	    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
	        if (!(request instanceof ContentCachingRequestWrapper)) {
	            request = new ContentCachingRequestWrapper(request);
	        }
	        if (!(response instanceof ContentCachingResponseWrapper)) {
	            response = new ContentCachingResponseWrapper(response);
	        }
	        HandlerExecutionChain handler = getHandler(request);

	        try {
	            super.doDispatch(request, response);
	        } finally {
	            log(request, response, handler);
	            updateResponse(response);
	        }
	    }

	    private void log(HttpServletRequest requestToCache, HttpServletResponse responseToCache, HandlerExecutionChain handler) throws JsonProcessingException {
	        LogMessage log = new LogMessage();
	        log.setHttpStatus(responseToCache.getStatus());
	        log.setHttpMethod(requestToCache.getMethod());
	        log.setPath(requestToCache.getRequestURI());
	        log.setClientIp(requestToCache.getRemoteAddr());
	        log.setJavaMethod(handler.toString());
	        log.setResponse(getResponsePayload(responseToCache));
	        logger.debug(mapper.writeValueAsString(log));
	    }

	    private String getResponsePayload(HttpServletResponse response) {
	        ContentCachingResponseWrapper wrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
	        if (wrapper != null) {

	            byte[] buf = wrapper.getContentAsByteArray();
	            if (buf.length > 0) {
	                int length = Math.min(buf.length, 5120);
	                try {
	                    return new String(buf, 0, length, wrapper.getCharacterEncoding());
	                }
	                catch (UnsupportedEncodingException ex) {
	                }
	            }
	        }
	        return "[unknown]";
	    }

	    private void updateResponse(HttpServletResponse response) throws IOException {
	        ContentCachingResponseWrapper responseWrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
	        responseWrapper.copyBodyToResponse();
	    }

}

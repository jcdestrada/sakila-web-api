/**
 * 
 */
package com.sakila.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SakilaException extends RuntimeException {
	public static String NO_DATA_FOUND_MSG = "No Data Found";
	private static final long serialVersionUID = -3273695726969903417L;
	Throwable rootCause;
	private ERROR_CODE messageCode;
	private String message;
	private static Logger logger = LoggerFactory.getLogger(SakilaException.class);

	public enum ERROR_CODE {
		REPOSITORY_ERROR, SERVICE_ERROR, CONTROLLER_ERROR,
	}

	public SakilaException(ERROR_CODE messageCode, Throwable t) {
		this.messageCode = messageCode;
		this.rootCause = t;
		this.message = t.getMessage();
		log();
	}

	public SakilaException(ERROR_CODE messageCode, String message) {
		this.messageCode = messageCode;
		this.message = message;
		log();
	}

	private void log() {
		logger.error(this.message, rootCause);
	}

	public ERROR_CODE getMessageCode() {
		return this.messageCode;
	}

	public String getMessage() {
		return this.message;
	}
}

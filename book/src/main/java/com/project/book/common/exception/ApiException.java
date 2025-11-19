package com.project.book.common.exception;

import org.springframework.http.HttpStatus;

import com.project.book.common.response.ResponseCode;

public class ApiException extends RuntimeException {
	private static final long serialVersionUID = -1179299781904521091L;
	private HttpStatus httpStatus;
	private ResponseCode status;
	private String message;

	/**
	 * Instantiates a new Api exception.
	 *
	 * @param httpStatus the http status
	 * @param responseCode  the api status
	 * @param message    the message
	 */
	public ApiException(HttpStatus httpStatus, ResponseCode responseCode, String message) {
		super();
		this.httpStatus = httpStatus;
		this.status = responseCode;
		this.message = message;
	}

	/**
	 * Instantiates a new Api exception.
	 *
	 * @param httpStatus the http status
	 * @param responseCode  the api status
	 */
	public ApiException(HttpStatus httpStatus, ResponseCode responseCode) {
		super();
		this.httpStatus = httpStatus;
		this.status = responseCode;
		this.message = responseCode.getMessage();
	}

	/**
	 * Instantiates a new Api exception.
	 *
	 * @param httpStatus the http status
	 * @param message    the message
	 */
	public ApiException(HttpStatus httpStatus, String message) {
		super();
		this.httpStatus = httpStatus;
		this.status = ResponseCode.INTERNAL_SERVER_ERROR;
		this.message = message;
	}

	/**
	 * Instantiates a new Api exception.
	 *
	 * @param responseCode the api status
	 * @param message   the message
	 */
	public ApiException(ResponseCode responseCode, String message) {
		super();
		this.httpStatus = responseCode.getStatus();
		this.status = responseCode;
		this.message = message;
	}

	/**
	 * Instantiates a new Api exception.
	 *
	 * @param responseCode the api status
	 */
	public ApiException(ResponseCode responseCode) {
		super();
		this.httpStatus = responseCode.getStatus();
		this.status = responseCode;
		this.message = responseCode.getMessage();
	}

	/**
	 * Instantiates a new Api exception.
	 *
	 * @param message the message
	 */
	public ApiException(String message) {
		super();
		this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		this.status = ResponseCode.INTERNAL_SERVER_ERROR;
		this.message = message;
	}
}

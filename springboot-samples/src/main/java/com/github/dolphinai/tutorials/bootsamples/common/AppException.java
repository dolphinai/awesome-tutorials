package com.github.dolphinai.tutorials.bootsamples.common;

public class AppException extends Exception {

	public AppException(Throwable error) {
		super(error);
	}

	public AppException(String message, Throwable error) {
		super(message, error);
	}

}

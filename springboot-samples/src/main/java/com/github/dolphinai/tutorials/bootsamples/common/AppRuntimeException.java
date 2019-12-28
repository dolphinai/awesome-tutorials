package com.github.dolphinai.tutorials.bootsamples.common;

public class AppRuntimeException extends RuntimeException {

	public AppRuntimeException(Throwable error) {
		super(error);
	}

	public AppRuntimeException(String message, Throwable error) {
		super(message, error);
	}

}

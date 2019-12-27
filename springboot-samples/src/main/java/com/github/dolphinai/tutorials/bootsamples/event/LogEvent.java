package com.github.dolphinai.tutorials.bootsamples.event;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Slf4j
public final class LogEvent implements Serializable {

	@Getter
	private final String message;

	protected LogEvent(String message) {
		this.message = message;
		log.info("Publish event: {}", message);
	}

	public static LogEvent of(String message) {
		return new LogEvent(message);
	}
}

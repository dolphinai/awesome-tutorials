package com.github.dolphinai.tutorials.bootsamples.event;

import lombok.Getter;
import lombok.ToString;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@ToString
public final class LogEvent implements Serializable {

	@Getter
	private String message;
	@Getter
	private String module;

	private LogEvent() {
	}

	public static LogEvent of(String module, String message) {
		LogEvent event = new LogEvent();
		event.module = module;
		event.message = message;
		return event;
	}
}

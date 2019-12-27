package com.github.dolphinai.tutorials.bootsamples.event;

import com.google.common.eventbus.Subscribe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "Auditing")
public final class LogEventListener {

	@Getter
	private LogEvent event;

	@Subscribe
	public void listen(LogEvent event) {
		event = event;
		log.info("Received message: {}", event);
	}
}

package com.github.dolphinai.tutorials.bootsamples.event;

import com.google.common.eventbus.Subscribe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogEventListener {

	@Getter
	private String lastMessage;

	@Subscribe
	public void onListen(LogEvent event) {
		lastMessage = event.getMessage();
		log.info("Received message: {}", lastMessage);
	}
}

package com.github.dolphinai.tutorials.bootsamples.event;

import com.google.common.eventbus.Subscribe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "Auditing")
public final class AuditingEventListener {

	@Getter
	private AuditingEvent event;

	@Subscribe
	public void listen(AuditingEvent event) {
		event = event;
		log.info("op={}, path={}, client-ip={}, {}", event.getOperation(), event.getPath(), event.getClientAddress(), event.getMessage());
	}
}

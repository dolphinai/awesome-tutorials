package com.github.dolphinai.tutorials.d3.infrastructure.persistence.repository;

import com.github.dolphinai.tutorials.d3.infrastructure.api.OrderCancellationEvent;
import com.github.dolphinai.tutorials.d3.infrastructure.api.OrderCreatedEvent;
import com.github.dolphinai.tutorials.d3.infrastructure.api.OrderUpdatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public final class OrderEventListener {

	@EventHandler
	public void on(OrderCreatedEvent event) {
		log.info("{}", event);
	}

	@EventHandler
	public void on(OrderCancellationEvent event) {
		log.info("{}", event);
	}

	@EventHandler
	public void on(OrderUpdatedEvent event) {
		log.info("{}", event);
	}

}

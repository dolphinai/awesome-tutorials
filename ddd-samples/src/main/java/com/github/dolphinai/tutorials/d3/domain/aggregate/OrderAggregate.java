package com.github.dolphinai.tutorials.d3.domain.aggregate;

import com.github.dolphinai.tutorials.d3.domain.cmd.OrderCancellationCommand;
import com.github.dolphinai.tutorials.d3.domain.cmd.OrderCreatedCommand;
import com.github.dolphinai.tutorials.d3.domain.cmd.OrderUpdatedCommand;
import com.github.dolphinai.tutorials.d3.infrastructure.api.OrderCancellationEvent;
import com.github.dolphinai.tutorials.d3.infrastructure.api.OrderUpdatedEvent;
import com.github.dolphinai.tutorials.d3.infrastructure.api.OrderCreatedEvent;
import com.github.dolphinai.tutorials.d3.infrastructure.persistence.entity.OrderModel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.messaging.MetaData;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Getter
@Setter
@Slf4j
public final class OrderAggregate implements OrderModel {

	@AggregateIdentifier
	private String id;

	private String name;

	public OrderAggregate(){

	}

	@CommandHandler
	private void handle(OrderCreatedCommand cmd, MetaData metaData) {
		log.info("{}", cmd);
		AggregateLifecycle.apply((OrderCreatedEvent)cmd, metaData);
	}

	@CommandHandler
	private void handle(OrderUpdatedCommand cmd, MetaData metaData) {
		log.info("{}", cmd);
		AggregateLifecycle.apply((OrderUpdatedEvent)cmd, metaData);
	}

	@CommandHandler
	private void handle(OrderCancellationCommand cmd, MetaData metaData) {
		log.info("{}", cmd);
		AggregateLifecycle.apply((OrderCancellationEvent)cmd, metaData);
	}

	@EventSourcingHandler
	public void on(OrderCreatedEvent event) {
		log.info("{}", event);
		this.id = event.getId();
	}

	@EventSourcingHandler
	public void on(OrderCancellationEvent event) {
		log.info("{}", event);
	}

	@EventSourcingHandler
	public void on(OrderUpdatedEvent event) {
		log.info("{}", event);
	}


}

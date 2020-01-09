package com.github.dolphinai.tutorials.d3.domain.cmd;

import com.github.dolphinai.tutorials.d3.infrastructure.api.OrderCreatedEvent;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value(staticConstructor = "of")
public class OrderCreatedCommand implements OrderCreatedEvent {

	@TargetAggregateIdentifier
	private String id;

	private String name;

}

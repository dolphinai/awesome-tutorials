package com.github.dolphinai.tutorials.d3.domain.cmd;

import com.github.dolphinai.tutorials.d3.infrastructure.api.OrderUpdatedEvent;
import lombok.Value;
import lombok.With;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value(staticConstructor = "of")
public class OrderUpdatedCommand implements OrderUpdatedEvent {

	@TargetAggregateIdentifier
	@With
	private String id;

	private String name;

}

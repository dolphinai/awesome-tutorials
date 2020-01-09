package com.github.dolphinai.tutorials.d3.domain.cmd;

import com.github.dolphinai.tutorials.d3.infrastructure.api.OrderCancellationEvent;
import lombok.Value;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value(staticConstructor = "of")
public class OrderCancellationCommand implements OrderCancellationEvent {

	@TargetAggregateIdentifier
	private String id;

}

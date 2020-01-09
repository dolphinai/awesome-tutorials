package com.github.dolphinai.tutorials.d3.infrastructure.api;

import com.github.dolphinai.tutorials.d3.infrastructure.event.DomainEvent;

public interface OrderCancellationEvent extends DomainEvent {

	String getId();

}

package com.github.dolphinai.tutorials.d3.infrastructure.api;

import com.github.dolphinai.tutorials.d3.infrastructure.event.DomainEvent;
import com.github.dolphinai.tutorials.d3.infrastructure.persistence.entity.OrderModel;


public interface OrderCreatedEvent extends DomainEvent, OrderModel {

}

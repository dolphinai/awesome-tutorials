package com.github.dolphinai.tutorials.d3.application.command;

import com.github.dolphinai.tutorials.d3.domain.cmd.OrderCancellationCommand;
import com.github.dolphinai.tutorials.d3.domain.cmd.OrderCreatedCommand;
import com.github.dolphinai.tutorials.d3.domain.cmd.OrderUpdatedCommand;
import reactor.core.publisher.Mono;

public interface OrderCommandService {

	Mono<Void> execute(OrderCreatedCommand command);

	void execute(OrderUpdatedCommand command);

	void execute(OrderCancellationCommand command);

}

package com.github.dolphinai.tutorials.d3.application.command;

import com.github.dolphinai.tutorials.d3.domain.cmd.OrderCancellationCommand;
import com.github.dolphinai.tutorials.d3.domain.cmd.OrderCreatedCommand;
import com.github.dolphinai.tutorials.d3.domain.cmd.OrderUpdatedCommand;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class OrderCommandServiceImpl implements OrderCommandService {

	private CommandGateway commandGateway;

	@Autowired
	public void setCommandGateway(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}

	@Override
	@Timeout(value = 6, unit = TimeUnit.SECONDS)
	public Mono<Void> execute(OrderCreatedCommand command) {
		log.info("{}", command);
		//return Mono.fromFuture(commandGateway.send(command));
		commandGateway.sendAndWait(command);
		return Mono.empty();
	}

	@Override
	@Timeout(value = 6, unit = TimeUnit.SECONDS)
	public void execute(OrderUpdatedCommand command) {
		log.info("{}", command);
		commandGateway.sendAndWait(command);
	}

	@Override
	@Timeout(value = 6, unit = TimeUnit.SECONDS)
	public void execute(OrderCancellationCommand command) {
		log.info("{}", command);
		commandGateway.sendAndWait(command);
	}
}

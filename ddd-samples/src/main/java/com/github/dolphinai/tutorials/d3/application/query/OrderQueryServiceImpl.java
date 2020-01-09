package com.github.dolphinai.tutorials.d3.application.query;

import com.github.dolphinai.tutorials.d3.infrastructure.persistence.repository.OrderRepository;
import org.axonframework.commandhandling.CommandBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class OrderQueryServiceImpl implements OrderQueryService {

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Flux<OrderVo> findAll() {
		return Flux.defer(() -> Flux
			.fromIterable(
				orderRepository.findAll())
			.map(entity -> OrderDetailVo.of(entity))
		);
	}

	@Override
	public Mono<OrderDetailVo> findById(String id) {
		Objects.requireNonNull(id);
		return Mono.defer(() -> Mono
			.justOrEmpty(
				orderRepository.findById(id)
					.map(entity -> OrderDetailVo.of(entity))));
	}
}

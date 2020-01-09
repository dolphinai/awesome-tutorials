package com.github.dolphinai.tutorials.d3.ui.controller;

import com.github.dolphinai.tutorials.d3.application.command.OrderCommandService;
import com.github.dolphinai.tutorials.d3.application.query.OrderQueryService;
import com.github.dolphinai.tutorials.d3.application.query.OrderDetailVo;
import com.github.dolphinai.tutorials.d3.application.query.OrderVo;
import com.github.dolphinai.tutorials.d3.domain.cmd.OrderCancellationCommand;
import com.github.dolphinai.tutorials.d3.domain.cmd.OrderCreatedCommand;
import com.github.dolphinai.tutorials.d3.domain.cmd.OrderUpdatedCommand;
import com.github.dolphinai.tutorials.d3.ui.ResultMap;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Api("Order management API")
@RestController
@RequestMapping("/api/order")
public class OrderController {

	@Autowired
	private OrderCommandService orderCommandService;
	@Autowired
	private OrderQueryService orderQueryService;


    @GetMapping
    public Flux<OrderVo> list() {
        return orderQueryService.findAll();
    }

	@GetMapping("/{id}")
    public Mono<OrderDetailVo> getById(@PathVariable("id") String id) {
        return orderQueryService.findById(id);
    }

    @PostMapping
    public ResultMap create(String id, @RequestBody OrderCreatedCommand command) {
    	orderCommandService.execute(command);
        return ResultMap.success();
    }

    @PutMapping("/{id}")
    public ResultMap update(@PathVariable("id") String id, @RequestBody OrderUpdatedCommand command) {
        command.withId(id);
    	orderCommandService.execute(command);
    	return ResultMap.success();
    }

    @DeleteMapping("/{id}")
    public ResultMap delete(@PathVariable("id") String id) {
    	orderCommandService.execute(OrderCancellationCommand.of(id));
        return ResultMap.success();
    }

}

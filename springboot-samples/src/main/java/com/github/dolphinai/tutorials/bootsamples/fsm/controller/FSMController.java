package com.github.dolphinai.tutorials.bootsamples.fsm.controller;

import com.github.dolphinai.tutorials.bootsamples.common.ResultMap;
import com.github.dolphinai.tutorials.bootsamples.event.AuditingEvent;
import com.github.dolphinai.tutorials.bootsamples.fsm.StatemachineExecutor;
import com.google.common.eventbus.EventBus;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@Api(description = "State Machine API")
@RestController
@RequestMapping("/fsm")
public class FSMController {

	private static final String MODULE = "FSM";

	@Autowired
	@Qualifier("logEventBus")
	private EventBus logEventBus;

	@Autowired
	private StatemachineExecutor statemachineExecutor;

	@PostMapping("/start/{id}")
	public ResultMap start(@PathVariable("id") String id) {
		ResultMap resultMap = statemachineExecutor.start(id, "admin");
		logEventBus.post(AuditingEvent.of(MODULE,"Start a FSM: " + id));
		return resultMap;
	}

}

package com.github.dolphinai.tutorials.bootsamples.simple.controller;

import com.github.dolphinai.tutorials.bootsamples.common.ResultMap;
import com.github.dolphinai.tutorials.bootsamples.event.AuditingLevel;
import com.github.dolphinai.tutorials.bootsamples.simple.model.User;
import com.github.dolphinai.tutorials.bootsamples.event.AuditingEvent;
import com.google.common.eventbus.EventBus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(description = "User management API")
@RestController
@RequestMapping("/user")
public class UserController {

	private static final String MODULE = "USER";

	@Autowired
	@Qualifier("logEventBus")
	private EventBus logEventBus;

	@ApiOperation(value = "Retrieve the user via the id")
	@GetMapping("/find/{id}")
	public User findById(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) {
		logEventBus.post(AuditingEvent.of(AuditingLevel.REQUESTRESPONSE, request, response));
		return User.of(id);
	}

	@DeleteMapping("/delete/{id}")
	public ResultMap deleteById(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) {
		logEventBus.post(AuditingEvent.of(AuditingLevel.REQUESTRESPONSE, request, response));
		return ResultMap.fail("AD01");
	}

	@PutMapping("/update")
	public ResultMap update(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
		logEventBus.post(AuditingEvent.of(AuditingLevel.REQUESTRESPONSE, request, response));
		return ResultMap.success();
	}

	@PostMapping("/create")
	public ResultMap create(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
		logEventBus.post(AuditingEvent.of(AuditingLevel.REQUESTRESPONSE, request, response));
		return ResultMap.success();
	}
}

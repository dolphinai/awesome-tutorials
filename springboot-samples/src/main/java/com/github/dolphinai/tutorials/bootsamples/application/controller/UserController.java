package com.github.dolphinai.tutorials.bootsamples.application.controller;

import com.github.dolphinai.tutorials.bootsamples.common.ResultMap;
import com.github.dolphinai.tutorials.bootsamples.domain.User;
import com.github.dolphinai.tutorials.bootsamples.event.LogEvent;
import com.google.common.eventbus.EventBus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@Api(description = "User management API")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	@Qualifier("logEventBus")
	private EventBus logEventBus;

	@ApiOperation(value = "Retrieve the user via the id")
	@GetMapping("/find/{id}")
	public User findById(@PathVariable("id") String id) {
		logEventBus.post(LogEvent.of("Retrieved a user: " + id));
		return User.of(id);
	}

	@DeleteMapping("/delete/{id}")
	public ResultMap deleteById(@PathVariable("id") String id) {
		logEventBus.post(LogEvent.of("Removed a user: " + id));
		return ResultMap.fail("AD01");
	}

	@PutMapping("/update")
	public ResultMap update(@RequestBody User user) {
		logEventBus.post(LogEvent.of("Updated a user: " + user.getId()));
		return ResultMap.success();
	}

	@PostMapping("/create")
	public ResultMap create(@RequestBody User user) {
		logEventBus.post(LogEvent.of("Created a user: " + user.getId()));
		return ResultMap.success();
	}
}

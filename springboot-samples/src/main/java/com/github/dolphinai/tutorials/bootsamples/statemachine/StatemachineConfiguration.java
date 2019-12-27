package com.github.dolphinai.tutorials.bootsamples.statemachine;

import com.github.dolphinai.tutorials.bootsamples.statemachine.fsm.StatemachineExecutorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StatemachineConfiguration {


	@Bean
	public StatemachineExecutor statemachineExecutor() {
		return new StatemachineExecutorImpl();
	}
}

package com.github.dolphinai.tutorials.d3.ui.config;

import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.TrackingEventProcessorConfiguration;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.spring.config.AxonConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfigurer {

	@Autowired
	private AxonConfiguration axonConfiguration;
	@Autowired
	private EventBus eventBus;


	@Autowired
	public void config(EventProcessingConfigurer configurer) {
		configurer.registerTrackingEventProcessorConfiguration(
			c -> TrackingEventProcessorConfiguration.forParallelProcessing(2)
		);
	}

	@Autowired
	public void configure(@Qualifier("localSegment") SimpleCommandBus simpleCommandBus) {
		simpleCommandBus.registerDispatchInterceptor(new BeanValidationInterceptor<>());
	}
}

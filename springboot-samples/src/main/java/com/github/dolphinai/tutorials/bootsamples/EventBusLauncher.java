package com.github.dolphinai.tutorials.bootsamples;

import com.github.dolphinai.tutorials.bootsamples.event.AuditingEventListener;
import com.google.common.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventBusLauncher implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		log.info("## EventBus Started");
	}

	@Bean
	public AuditingEventListener logEventListener() {
		return new AuditingEventListener();
	}

	@Bean(name = "logEventBus")
	public EventBus logEventBus(AuditingEventListener logEventListener) {
		EventBus eventBus = new EventBus("logBus");
		eventBus.register(logEventListener);
		return eventBus;
	}
}

package com.github.dolphinai.tutorials.bootsamples.event;

import com.google.common.eventbus.EventBus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class AuditingEventBus extends EventBus {

	public void publish(HttpServletRequest request, HttpServletResponse response) {

	}

}

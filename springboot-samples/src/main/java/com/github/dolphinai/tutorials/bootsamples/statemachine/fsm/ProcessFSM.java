package com.github.dolphinai.tutorials.bootsamples.statemachine.fsm;

import com.github.dolphinai.tutorials.bootsamples.statemachine.ProcessContext;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.squirrelframework.foundation.fsm.annotation.AsyncExecute;
import org.squirrelframework.foundation.fsm.annotation.ContextInsensitive;
import org.squirrelframework.foundation.fsm.annotation.StateMachineParameters;
import org.squirrelframework.foundation.fsm.impl.AbstractUntypedStateMachine;

@Slf4j
@ContextInsensitive
@StateMachineParameters(stateType=String.class, eventType=String.class, contextType= ProcessContext.class)
public class ProcessFSM extends AbstractUntypedStateMachine {

	@Getter
	@Setter
	private String processId;

	@AsyncExecute
	protected void onTransition(String from, String to, String event) {
		// this action method will be invoked asynchronously
		log.info("[{}] [{}] [onTransition] {} -> {} -> {}", this.processId, this.getIdentifier(), from, event, to);
	}

	@AsyncExecute
	protected void onEntry(String from, String to, String event, ProcessContext context) {
		// this action method will be invoked asynchronously
		log.info("[{}] [{}] [onEntry] {} -> {} -> {}", context.getProcessId(), context.getProcessInstanceId(), from, event, to);
	}

	@AsyncExecute
	protected void onFinished(String from, String to, String event, ProcessContext context) {
		// this action method will be invoked asynchronously
		log.info("[{}] [{}] [onFinished] {} -> {} -> {}", context.getProcessId(), context.getProcessInstanceId(), from, event, to);
	}
}

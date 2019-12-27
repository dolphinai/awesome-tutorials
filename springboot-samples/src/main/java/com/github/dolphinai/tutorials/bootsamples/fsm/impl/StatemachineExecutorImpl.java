package com.github.dolphinai.tutorials.bootsamples.fsm.impl;

import com.github.dolphinai.tutorials.bootsamples.common.ResultMap;
import com.github.dolphinai.tutorials.bootsamples.fsm.ProcessContext;
import com.github.dolphinai.tutorials.bootsamples.fsm.StatemachineExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.squirrelframework.foundation.component.IdProvider;
import org.squirrelframework.foundation.component.SquirrelSingletonProvider;
import org.squirrelframework.foundation.fsm.StateMachineConfiguration;
import org.squirrelframework.foundation.fsm.UntypedStateMachineBuilder;
import org.squirrelframework.foundation.fsm.UntypedStateMachineImporter;

import java.io.InputStream;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class StatemachineExecutorImpl implements StatemachineExecutor, InitializingBean, DisposableBean {

	public StatemachineExecutorImpl() {

	}

	@Override
	public void afterPropertiesSet() throws Exception {
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		SquirrelSingletonProvider.getInstance().register(ExecutorService.class, executorService);
	}

	@Override
	public void destroy() throws Exception {

	}

	@Override
	public ResultMap start(String processId, String actor) {
		Objects.requireNonNull(processId);
		ProcessFSM fsm = null;
		try {
			fsm = createStatemachine(processId, "Start");
		} catch (NullPointerException e) {
			log.error("Failed to run start()", e);
			return ResultMap.fail("F001").message("Cannot found the FSM: " + processId);
		} catch (Exception e) {
			log.error("Failed to run start()", e);
			return ResultMap.fail("F002").message(e.getMessage());
		}
		fsm.setProcessId(processId);
		fsm.start();
		String id = fsm.getIdentifier();
		fsm.fire("approved", ProcessContext.of(processId, id));
		String state = (String) fsm.getCurrentState();
		return ResultMap.success().with("id", id).with("state", state);
	}

	@Override
	public ResultMap execute(ProcessContext context, String command, String comment) {
		return null;
	}

	protected ProcessFSM createStatemachine(String processId, String initialStateId) {
		String path = "/definitions/" + processId + ".scxml";
		InputStream scxmlDef = StatemachineExecutorImpl.class.getResourceAsStream(path);
		UntypedStateMachineBuilder builder = new UntypedStateMachineImporter().importDefinition(scxmlDef);
		return builder.newAnyStateMachine(initialStateId,
			StateMachineConfiguration.create().enableAutoStart(false)
				.setIdProvider(IdProvider.UUIDProvider.getInstance()),
			new Object[0]);
	}
}

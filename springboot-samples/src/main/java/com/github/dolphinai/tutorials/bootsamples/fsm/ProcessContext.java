package com.github.dolphinai.tutorials.bootsamples.fsm;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProcessContext implements Serializable {

	private String processId;
	private String processInstanceId;

	private ProcessContext(){ }

	public static ProcessContext of(String processId, String processInstanceId) {
		ProcessContext context = new ProcessContext();
		context.processId = processId;
		context.processInstanceId = processInstanceId;
		return context;
	}

}

package com.github.dolphinai.tutorials.d3.infrastructure.shared;

import lombok.Getter;

public final class CommandHandleException extends RuntimeException {

	@Getter
	public String command;

	public CommandHandleException(Throwable error) {
		super(error);
	}

	public CommandHandleException(String message, Throwable error) {
		super(message, error);
	}

	public CommandHandleException withCommand(String command) {
		this.command = command;
		return this;
	}
}

package com.github.dolphinai.tutorials.bootsamples.common;

import java.util.HashMap;
import java.util.Objects;

public final class ResultMap extends HashMap<String, Object> {

	protected ResultMap(String code, String message) {
		super(8);
		this.with("code", code);
		this.with("message", message);
	}

	public ResultMap message(String message) {
		return with("message", message);
	}

	public <T> ResultMap body(T body) {
		return with("body", body);
	}

	public <T> ResultMap with(String name, T value) {
		Objects.requireNonNull(name);
		this.put(name, value);
		return this;
	}

	public static ResultMap success() {
		return new ResultMap("000", "OK");
	}

	public static ResultMap fail(String code) {
		return new ResultMap(code, "Failed");
	}
}

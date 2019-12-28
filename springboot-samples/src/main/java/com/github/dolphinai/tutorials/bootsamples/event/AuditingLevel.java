package com.github.dolphinai.tutorials.bootsamples.event;

import java.io.Serializable;

public enum AuditingLevel implements Serializable {

	NONE("NONE", 0),
	METADATA("METADATE", 1),
	REQUEST("REQUEST", 2),
	REQUESTRESPONSE("REQUESTRESPONSE", 3);

	private String label;
	private int value;

	private AuditingLevel(String label, int value) {
		this.label = label;
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public int getValue() {
		return value;
	}
}

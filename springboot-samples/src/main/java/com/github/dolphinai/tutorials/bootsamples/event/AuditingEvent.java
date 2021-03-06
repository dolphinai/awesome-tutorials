package com.github.dolphinai.tutorials.bootsamples.event;

import lombok.*;
import org.apache.log4j.lf5.LogLevel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public final class AuditingEvent implements Serializable {

	public enum Level {
		NONE, METADATA, REQUEST, REQUEST_RESPONSE
	}

	@Getter
	@With
	private String operation;
	@Getter
	private String path;
	@Getter
	@With
	private String userId;
	@Getter
	@With
	private String clientAddress;
	@Getter
	@With
	private String message;

	public static AuditingEvent of(AuditingLevel level, HttpServletRequest request, HttpServletResponse response) {
		AuditingEvent result = new AuditingEvent();
		result.path = request.getRequestURI();
		result.operation = "Query";
		String method = request.getMethod();
		if ("POST".equalsIgnoreCase(method)) {
			result.operation = "Update";
		} else if ("DELETE".equalsIgnoreCase(method)) {
			result.operation = "Remove";
		} else if ("PUT".equalsIgnoreCase(method)) {
			result.operation = "Create";
		}

		result.clientAddress = request.getRemoteUser();

		if (Level.NONE.equals(level)) {
			return result;
		}
		StringBuilder builder = new StringBuilder();
		// metadata
		if (level.getValue() >= AuditingLevel.METADATA.getValue()) {

			builder.append("Principal=").append(request.getUserPrincipal());

		}

		// request
		if (level.getValue() >= AuditingLevel.REQUEST.getValue()) {

			builder.append("\n[Request] ").append("User-Agent=").append(request.getHeader("user-agent"));
		}

		// response
		if (level.getValue() >= AuditingLevel.REQUESTRESPONSE.getValue()) {

			builder.append("\n[Response] ");

		}

		result.message = builder.toString();
		return result;
	}
}

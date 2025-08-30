package com.minihelpdesk.dto.error;

import java.time.Instant;
import java.util.List;

public class ApiError {

	private Instant timestamp;
	private int status;
	private String error;
	private String message;
	private String path;
	private List<String> details;

	public ApiError() {
		this.timestamp = Instant.now();
	}

	public ApiError(int status, String message) {
		this.timestamp = Instant.now();
		this.status = status;
		this.message = message;
	}

	public ApiError(int status, String error, String message, String path, List<String> details) {
		this.timestamp = Instant.now();
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
		this.details = details;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public int getStatus() {
		return status;
	}

	public String getError() {
		return error;
	}

	public String getMessage() {
		return message;
	}

	public String getPath() {
		return path;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setError(String error) {
		this.error = error;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}
}

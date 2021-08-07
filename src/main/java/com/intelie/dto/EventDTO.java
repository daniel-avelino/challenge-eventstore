package com.intelie.dto;

import java.io.Serializable;

public class EventDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String type;
	private Long timestamp;

	public EventDTO() {
	}

	public EventDTO(Long id, String type, Long timestamp) {
		this.id = id;
		this.type = type;
		this.timestamp = timestamp;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
}

package com.Project.demo.model;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EventFileMapPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "EVENT_ID")
	private Long eventId;

	@Column(name = "FILE_ID")
	private Long fileId;

	public EventFileMapPK(Long eventId, Long fileId) {
		this.eventId = eventId;
		this.fileId = fileId;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
}

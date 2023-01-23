package com.Project.demo.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "EVENT_FILE_MAP")
public class EventFileMap {

	public EventFileMapPK getEventFileMapPK() {
		return eventFileMapPK;
	}

	public void setEventFileMapPK(EventFileMapPK eventFileMapPK) {
		this.eventFileMapPK = eventFileMapPK;
	}

	@EmbeddedId
	EventFileMapPK eventFileMapPK;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("eventId")
	@JoinColumn(name = "EVENT_ID", referencedColumnName = "EVENT_ID")
	private Events events;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("fileId")
	@JoinColumn(name = "FILE_ID", referencedColumnName = "FILE_ID")
	private Files files;

	public EventFileMap() {
	}

	public Events getEvents() {
		return events;
	}

	public void setEvents(Events events) {
		this.events = events;
	}

	public Files getFiles() {
		return files;
	}

	public void setFiles(Files files) {
		this.files = files;
	}

}

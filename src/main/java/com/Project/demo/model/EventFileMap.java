package com.Project.demo.model;

import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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

	@ManyToMany(fetch = FetchType.LAZY)
	// @MapsId("eventId")
	@JoinColumn(name = "EVENT_ID", referencedColumnName = "EVENT_ID")
	private List<Events> events;

	@ManyToMany(fetch = FetchType.LAZY)
	// @MapsId("fileId")
	@JoinColumn(name = "FILE_ID", referencedColumnName = "FILE_ID")
	private List<Files> files;

	public EventFileMap() {
	}

	public List<Events> getEvents() {
		return events;
	}

	public void setEvents(List<Events> events) {
		this.events = events;
	}

	public List<Files> getFiles() {
		return files;
	}

	public void setFiles(List<Files> files) {
		this.files = files;
	}

}

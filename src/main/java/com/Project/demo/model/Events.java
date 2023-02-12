package com.Project.demo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "EVENTS")
public class Events implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EVENT_ID", updatable = false, nullable = false)
	private Long eventId;

	@Column(name = "EVENT_NAME", unique = true)
	private String eventName;
	
	@Column(name = "EVENT_DETAILS")
	private String eventDetails;

	@OneToMany(fetch = FetchType.LAZY)
	@MapsId("EVENT_ID")
	@JoinColumn(name = "EVENT_ID", referencedColumnName = "EVENT_ID")
	private List<EventFileMap> eventFileMaps;

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventDetails() {
		return eventDetails;
	}

	public void setEventDetails(String eventDetails) {
		this.eventDetails = eventDetails;
	}

	public List<EventFileMap> getEventFileMaps() {
		return eventFileMaps;
	}

	public void setEventFileMaps(List<EventFileMap> eventFileMaps) {
		this.eventFileMaps = eventFileMaps;
	}

}
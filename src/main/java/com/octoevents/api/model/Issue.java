package com.octoevents.api.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "issue")
public class Issue extends IEventsAction {

	@NotNull
	private Integer number;
	
	@NotBlank
	private String url;
	
	private String title;
	
	private Date created_at;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "issue", fetch = FetchType.EAGER)
	private List<Event> events;
	
	public Issue() {
		super();
	}
	
	public Issue(Integer number, String title, String url, Date created_at) {
		super();
		this.number = number;
		this.title = title;
		this.url = url;
		this.created_at = created_at;
	}

	public Issue(Integer number, String title, String url) {
		super();
		this.number = number;
		this.title = title;
		this.url = url;
	}
	
	public Integer getNumber() {
		return number;
	}
	
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public List<Event> getEvents() {
		return events;
	}
	
	public void setEvents(List<Event> events) {
		this.events = events;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreated_at() {
		return created_at;
	}
	
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public boolean equals(Issue i) {
		return this.getUrl().equals(i.getUrl());
	}

	@Override
	public int hashCode() {
		return this.getUrl().hashCode();
	}

}

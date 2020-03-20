package com.octoevents.api.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name="event")
public class Event extends IEventsAction{
	
	@NotBlank
	private String action;
	
	@ManyToOne
	@JoinColumn(name="idissue")
	@JsonProperty(access = Access.WRITE_ONLY)
	@NotNull
	@Valid
	private Issue issue;

	public Event() {
		super();
	}

	public Event(String action, Issue issue) {
		super();
		this.action = action;
		this.issue = issue;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

}

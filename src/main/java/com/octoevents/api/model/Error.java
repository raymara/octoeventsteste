
package com.octoevents.api.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="error")
public class Error extends IEventsAction{
	
	private static final int max_size_details = 255;
	
	private String message;
	private String requestDescription;
	@Transient
	private String details;
	@Transient
	private Date date;
	@JsonIgnore
	@Size(max=max_size_details, message="A coluna 'details' não pode ultrapassar "+ max_size_details +" caracteres")
	@Valid
	private String detailsDB;
	
	
	public Error(String message, String details, Date date, String uri) {
		super();
		this.message = message;
		this.details = details;
		if(this.details.length() > max_size_details) {
			this.detailsDB = this.details.substring(0, max_size_details-1);
		}else {
			this.detailsDB = this.details;
		}
		this.date = date;
		this.requestDescription = uri;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDetailsDB() {
		return detailsDB;
	}

	public void setDetailsDB(String detailsDB) {
		
		if(detailsDB.length() > max_size_details) {
			this.detailsDB = this.detailsDB.substring(0, max_size_details-1);
		}else {
			this.detailsDB = detailsDB;
		}
	}

	public String getRequestDescription() {
		return requestDescription;
	}

	public void setRequestDescription(String uri) {
		this.requestDescription = uri;
	}	
}
package com.octoevents.api.controller;

import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.octoevents.api.controller.exception.IssueNotFoundException;
import com.octoevents.api.model.Event;
import com.octoevents.api.service.EventServiceAction;
import com.octoevents.api.util.SystemMessage;

@RestController
public class EventController {

	private EventServiceAction eventService;
	
	@Autowired 
	public void setEventService(EventServiceAction eventService){ 
		this.eventService = eventService; 
	}
	
	@GetMapping("/issues/{number}/events")
	@Produces(MediaType.APPLICATION_JSON) 
	public ResponseEntity<Object> getEventsByIssueNumber(@PathVariable Integer number) { 
		
		List<Event> listEvents; 
		listEvents = eventService.listEventsByIssueNumber(number); 
		
		if(listEvents.isEmpty()) {
			throw new IssueNotFoundException(SystemMessage.ISSUE_NOT_FOUND_MESSAGE);
		}
		
		return ResponseEntity.ok(listEvents); 
	}
		
}

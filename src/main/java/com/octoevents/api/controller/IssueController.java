package com.octoevents.api.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.octoevents.api.controller.exception.IssueNotFoundException;
import com.octoevents.api.model.Event;
import com.octoevents.api.model.Issue;
import com.octoevents.api.service.IssueServiceAction;
import com.octoevents.api.util.SystemMessage;

@RestController
public class IssueController {

	private IssueServiceAction issueService;
	
	@Autowired 
	public void setIssueService(IssueServiceAction issueService){ 
		this.issueService = issueService; 
	}
	
	@PostMapping("/events")
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseEntity<Object> addIssue(@Valid @RequestBody Event evento) {
		 
		issueService.updateIssue(evento);
		
		return ResponseEntity.created(URI.create("/issues/" + evento.getIssue().getNumber()+"/events")).build();
	}			
	
	 @GetMapping("/issues")
	 @Produces(MediaType.APPLICATION_JSON) 
	 public ResponseEntity<Object> getIssues() { 
		 
		 List<Issue> issues = (List<Issue>) issueService.listAllIssues(); 
		 
		 if(issues.isEmpty()) {
				throw new IssueNotFoundException(SystemMessage.ISSUE_NOT_FOUND_MESSAGE);
		 }
		 
		 return ResponseEntity.ok(issues); 
	 }
		
}

package com.octoevents.api.modelMock

import com.octoevents.api.model.Event;
import org.springframework.beans.factory.annotation.Autowired

class EventMockJson {
	
	var action : String;
	
	@Autowired
	var issue : IssueMockJson;
	
	constructor (action : String, issue : IssueMockJson) : super() {
		this.action = action;
		this.issue = issue;
	}
	
	constructor (event : Event) : super(){
		this.action = event.getAction();
		this.issue = IssueMockJson(event.getIssue());
	}

	
}
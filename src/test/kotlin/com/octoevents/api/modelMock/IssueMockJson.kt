package com.octoevents.api.modelMock

import com.octoevents.api.model.Issue;
import kotlin.properties.Delegates
import org.springframework.beans.factory.annotation.Autowired


class IssueMockJson {
	
	var  number : Int by Delegates.notNull<Int>();
	var  url : String;
    var  title : String;
	
	@Autowired
	lateinit var  events : Collection<EventMockJson>;
	
	
	constructor(number : kotlin.Int, url : String, title : String) : super(){
		this.number = number;
		this.url = url;
		this.title = title;
	}
	
	constructor(issue : Issue) {
		this.number = issue.getNumber();
		this.url = issue.getUrl();
		this.title = issue.getTitle();
	}


}
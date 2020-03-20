package com.octoevents.api.kotlin.integration

import org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.octoevents.api.model.Issue;
import com.octoevents.api.modelMock.EventMockJson;
import com.octoevents.api.modelMock.IssueMockJson;
import com.octoevents.api.service.IssueServiceAction;
import org.springframework.core.ParameterizedTypeReference

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EventEndpointControllerIntegrationTest {
	
	@LocalServerPort
	private lateinit var port : Integer
	
	var restTemplate =  TestRestTemplate()

	var headers =  HttpHeaders()
	
	@Autowired
	private lateinit var issueService : IssueServiceAction;

	@Test
	@Throws(Exception::class)
	fun `testRetrieveEventsByIssueNumber`() {
		
		var eventoMock = EventMockJson("closed", IssueMockJson(Issue(21, "Repo21", "test/repo21")))
		
		try {
		
			var entitysave = HttpEntity<EventMockJson>(eventoMock, headers);
	
			restTemplate.exchange(createURLWithPort("/events"), HttpMethod.POST, entitysave, String::class.java);		
	
			var entityConsult = HttpEntity<String>(null, headers);
	
			var response = restTemplate.exchange(
					createURLWithPort("/issues/"+eventoMock.issue.number+"/events"),
					HttpMethod.GET, entityConsult, String::class.java);
			
			assertTrue(response.getBody().contains(eventoMock.action));
			
		}finally {
			var issue = issueService.getIssueByUrl(eventoMock.issue.url);
			if(issue != null) {
				issueService.deleteIssue(issue);
			}
		}
	}
	
	fun `createURLWithPort`(uri : String) : String {
		return "http://localhost:" + port + uri;
	}

}
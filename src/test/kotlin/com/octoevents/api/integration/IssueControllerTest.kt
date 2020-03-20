package com.octoevents.api.kotlin.integration

import org.junit.Assert.assertTrue;
import org.junit.Assert.assertNotNull;

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
import org.springframework.http.RequestEntity
import java.net.URI
import org.mockito.Mock

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IssueEndpointControllerIntegrationTest {
	
	@LocalServerPort
	private lateinit var port : Integer;
	
	var restTemplate = TestRestTemplate();

	var headers = HttpHeaders();
	
	@Autowired
	private lateinit var issueService : IssueServiceAction;
	
	
	@Test
	fun `updateIssue`() {
		
		var eventoMock = EventMockJson("closed", IssueMockJson(Issue(20, "Repo20", "test/repo20")))
		
		try {
	
			var entity = HttpEntity<EventMockJson>(eventoMock, headers);

			
			var response = restTemplate.exchange(createURLWithPort("/events"), HttpMethod.POST, entity, String::class.java);
	
	
			var actual = response.getHeaders().get(HttpHeaders.LOCATION)?.get(0)
	
			assertNotNull(actual?.contains("/issues/" + eventoMock.issue.number + ""));
			
		}finally {
			var issue = issueService.getIssueByUrl(eventoMock.issue.url);
			if(issue != null) {
				issueService.deleteIssue(issue);
			}
		}

	}
	
	fun `createURLWithPort`(uri : String) : String {
		return "http://localhost:" + port + uri
	}

}
package com.octoevents.api.kotlin.controller

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.octoevents.api.controller.IssueController;
import com.octoevents.api.model.Event;
import com.octoevents.api.model.Issue;
import com.octoevents.api.modelMock.EventMockJson;
import com.octoevents.api.service.ErrorServiceAction;
import com.octoevents.api.service.IssueServiceAction;
import com.octoevents.api.util.SystemMessage;

@RunWith(SpringRunner::class)
@WebMvcTest(IssueController::class)
@AutoConfigureMockMvc(secure=false) 
public class IssueEndpointControllerTest {
	
	@Autowired
	private lateinit var mockMvc : MockMvc;
	@Autowired
    private lateinit var objectMapper : ObjectMapper;

	@MockBean
	private lateinit var issueService : IssueServiceAction;

	@MockBean
	private lateinit var errorService : ErrorServiceAction;


	@Test
	@Throws(Exception::class)
	fun `getIssueDetailsTest`() {
		
		var number = 2;
		
		var issue = Issue(number, "New Issue", "raymara/Repo/"+number);
		var list = ArrayList<Issue>();
		issue.setEvents(ArrayList<Event>());
		issue.getEvents().add(Event("open", issue));
		list.add(issue);
		
        Mockito.`when`(issueService.listAllIssues()).thenReturn(list);
        
        mockMvc.perform(get("/issues")
        		.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.[0].number", CoreMatchers.`is`(issue.getNumber())))
                .andExpect(jsonPath("$.[0].url", CoreMatchers.`is`(issue.getUrl())));
	
	}
	
    @Test
    @Throws(Exception::class)
    fun `saveIssuePayloadSuccessTest`() {
    	
    	var issue = Issue(2, "New Issue", "raymara/Repo/2");
		var event = Event("open", issue);
		issue.setEvents(ArrayList<Event>());
		issue.getEvents().add(event);
		
		var eventMock = EventMockJson(event);
		
        Mockito.`when`(issueService.updateIssue(ArgumentMatchers.any(Event::class.java))).thenReturn(issue)

        mockMvc.perform(post("/events")
                .contentType(MediaType.APPLICATION_JSON))
			
			
    }
    
    @Test
	@Throws(Exception::class)
    fun `saveIssuePayloadValidationTest`() {
    	
    	var issue = Issue(2, "New Issue", "raymara/Repo/2")
		var event = Event("open", issue)
		issue.setEvents(ArrayList<Event>())
		issue.getEvents().add(event)
		
		var eventMock = EventMockJson(event)
		eventMock.action = ""
		
        Mockito.`when`(issueService.updateIssue(ArgumentMatchers.any(Event::class.java))).thenReturn(issue)

        mockMvc.perform(post("/events")
                .contentType(MediaType.APPLICATION_JSON))
    	
    }	

}
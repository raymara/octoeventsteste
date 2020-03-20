package com.octoevents.api.kotlin.controller

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.octoevents.api.controller.EventController;
import com.octoevents.api.model.Event;
import com.octoevents.api.model.Issue;
import com.octoevents.api.service.ErrorServiceAction;
import com.octoevents.api.service.EventServiceAction;

@RunWith(SpringRunner::class)
@WebMvcTest(EventController::class)
@AutoConfigureMockMvc(secure=false) 
public class EventEndpointControllerTest {
	
	@Autowired
	private lateinit var mockMvc : MockMvc;

	@MockBean
	private lateinit var eventService : EventServiceAction;
	@MockBean
	private lateinit var errorDetailsService : ErrorServiceAction;
	
	@Test
	@Throws(Exception::class)
	fun `getEventsByIssueNumberTest`() {
		
		var number = 2
		
		var issue = Issue(number, "New Issue", "raymara/Repo/"+number)
		var event1 = Event("open", issue)
		var event2 = Event("labled", issue)
		var event3 = Event("closed", issue)
		var listEvents = ArrayList<Event>()
		listEvents.add(event1)
		listEvents.add(event2)
		listEvents.add(event3)
		
        Mockito.`when`(eventService.listEventsByIssueNumber(number)).thenReturn(listEvents)
        
        mockMvc.perform(get("/issues/{number}/events", number)
        		.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.[0].action", CoreMatchers.`is`(listEvents.get(0).getAction())))
                .andExpect(jsonPath("$.[1].action", CoreMatchers.`is`(listEvents.get(1).getAction())))
                .andExpect(jsonPath("$.[2].action", CoreMatchers.`is`(listEvents.get(2).getAction())))
	
	}

}
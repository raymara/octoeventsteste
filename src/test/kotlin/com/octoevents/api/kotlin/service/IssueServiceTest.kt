package com.octoevents.api.kotlin.service

import org.junit.Assert.assertEquals;
import org.junit.Assert.assertNotNull;
import org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.octoevents.api.model.Event;
import com.octoevents.api.model.Issue;
import com.octoevents.api.repository.IssueRepository;
import com.octoevents.api.service.IssueServiceAction;
import org.mockito.Mockito

@RunWith(MockitoJUnitRunner::class)
class IssueServiceTest {
	
	@InjectMocks
	private lateinit var issueService: IssueServiceAction;
	@Mock
	private lateinit var issueRepositoryMock: IssueRepository;
	
	@Test
	fun `listAllIssuesTest`() {
		
		Mockito.`when`(issueRepositoryMock.findAll()).thenReturn(this.getIssueThreeItemsList());
		
		var returnList = listOf(issueService.listAllIssues());
        
		assertEquals(returnList.size, 1);
        verify(issueRepositoryMock).findAll();
	}
	
	@Test
	fun `void getIssueByIdTest`() {
		var issue : Issue
		var returnIssue	: Issue	
		var id = 1;
		
		issue = Issue(2, "New Issue", "/Repository/2", Date());
		issue.setId(1);
		
		var op = Optional.of(issue);
		
		Mockito.`when`(issueRepositoryMock.findById(id)).thenReturn(op);
		
		returnIssue = issueService.getIssueById(id);
		
		assertNotNull(returnIssue);
		assertEquals(returnIssue, issue);
	}
	
	@Test
	fun `getIssueByUrlTest`() {
		var issue : Issue
		var returnIssue	: Issue	
		
		var url = "/Repository/2"
		
		issue = Issue(2, "New Issue", url, Date())
		issue.setId(1)
		
		Mockito.`when`(issueRepositoryMock.getIssueByUrl(url)).thenReturn(issue)
		
		returnIssue = issueService.getIssueByUrl(url)
		
		assertNotNull(returnIssue)
		assertEquals(returnIssue.getUrl(), url)
	}
	
	@Test
	fun `listIssuesByNumberTest`() {
		var issue : Issue
		var id = 1
		var url = "/Repository/2"
		var number = 2
		
		issue = Issue(number, "New Issue", url, Date())
		issue.setId(id)
		
		var issueList = listOf(issue)
		
		Mockito.`when`(issueRepositoryMock.findByNumber(number)).thenReturn(issueList)
		
		var returnIssue = listOf(issueService.listIssuesByNumber(number))
		
		assertNotNull(returnIssue)
		assertEquals(returnIssue.size, 1)
		System.out.println(returnIssue.get(0))
		System.out.println(issue)
		assertEquals(""+returnIssue.get(0)+"", "["+issue+"]")
	}

	@Test
	fun `saveIssueTest`() {		
		var issue : Issue
		var returnIssue	: Issue	
		
		issue = Issue(2, "New Issue", "raymara/Repository/2", Date())
		issue.setId(1)
		
		Mockito.`when`(issueRepositoryMock.save(issue)).thenReturn(issue);
		
		returnIssue = issueService.saveIssue(issue);
		
		assertNotNull(returnIssue);
		assertEquals(returnIssue, issue);
	}
	
	@Test
	fun `updateIssueTest`() {
		var issue : Issue
		var returnIssue	: Issue	
		var event1 : Event
		
		issue = Issue(2, "New Issue", "raymara/Repository/2", Date())
		event1 = Event("open", issue)
		
		issue.setEvents(ArrayList<Event>())
		issue.getEvents().add(event1);
		
		Mockito.`when`(issueRepositoryMock.save(issue)).thenReturn(issue);
		
		returnIssue = issueService.updateIssue(event1);
		
		assertNotNull(returnIssue);
		assertEquals(returnIssue, issue);
		assertEquals(returnIssue.getEvents().get(0).getAction(), event1.getAction());
	}
	
	fun `getIssueThreeItemsList`(): Collection<Issue>{

		var issue = Issue(1, "new Issue 01", "raymara/1", Date())
		var evento = Event("open", issue)
		issue.setEvents(ArrayList<Event>())
		issue.getEvents().add(evento)
			
		var listIssue = listOf(issue)
		
		return listIssue
	}
	
}
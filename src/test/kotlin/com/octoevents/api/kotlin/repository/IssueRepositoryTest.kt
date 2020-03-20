package com.octoevents.api.kotlin.repository

import org.junit.Assert.assertEquals;
import org.junit.Assert.assertNotNull;
import org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.octoevents.api.model.Event;
import com.octoevents.api.model.Issue;
import com.octoevents.api.repository.IssueRepository;

@RunWith(SpringRunner::class)
@DataJpaTest
class IssueRepositoryTest {
	
	@Autowired
	private lateinit var issueRepository: IssueRepository;
	private lateinit var issue: Issue;
	
	@Before
	fun `createIssueBeforeTest`() {
		var eventos = ArrayList<Event>()
		
		this.issue = Issue(1, "failed import", "raymara/1", Date())
		
		eventos.add(Event("open", this.issue))
		
		this.issue.setEvents(eventos)
	}
	
	@Test
	fun `void saveIssueTest`() {
		var issuedb : Issue
		
		issueRepository.save(this.issue)

		issuedb = issueRepository.getIssueByUrl(this.issue.getUrl())
		
		assertNotNull(issuedb)
		assertEquals(issuedb, issue)
		assertNotNull(issuedb.getEvents())
		assertEquals(issuedb.getEvents().size, 1)
		assertEquals(issuedb.getEvents().get(0).getAction(), this.issue.getEvents().get(0).getAction())
	}
	
	@Test
	fun `void addEventToIssueTest`() {
		var issuedb : Issue
		var issuedb2 : Issue		
		
		issueRepository.save(this.issue)
		
		issuedb = issueRepository.getIssueByUrl(this.issue.getUrl())
		
		issuedb.getEvents().add(Event("labeled", issuedb))
		
		issueRepository.save(issuedb)
		
		issuedb2 = issueRepository.getIssueByUrl(this.issue.getUrl())
		
		assertNotNull(issuedb2)
		assertEquals(issuedb2.getEvents().size, 2)
	}
	
	@Test
	fun `void deleteIssueTeste`() {
		var issuedb : Issue
		
		issueRepository.save(this.issue)
		
		issuedb = issueRepository.getIssueByUrl(this.issue.getUrl())
		
		issueRepository.delete(issuedb)
		
		assertNull(issueRepository.getIssueByUrl(this.issue.getUrl()))
	}
	

}

package com.octoevents.api.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.octoevents.api.model.Event;
import com.octoevents.api.model.Issue;
import com.octoevents.api.model.form.IssueService;
import com.octoevents.api.repository.IssueRepository;

@Service
public class IssueServiceAction implements IssueService {
	
	private IssueRepository issueRepository;
	
	@Autowired
	public void setIssueRepository(IssueRepository issueRepository) {
		this.issueRepository = issueRepository;
	}
	
	@Override
	public Iterable<Issue> listAllIssues() {
		return issueRepository.findAll();
	}
	
	@Override
	public Issue getIssueById(Integer id) {
		return issueRepository.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	public Issue saveIssue(Issue issue) {
		Issue issuedb;

		issuedb = this.getIssueByUrl(issue.getUrl());

		if (issuedb == null) {
			issuedb = issueRepository.save(issue);
		}

		return issuedb;
	}
	
	@Override
	@Transactional
	public void deleteIssue(Issue issue) {
		issueRepository.delete(issue);
	}
	
	@Override
	public Issue getIssueByUrl(String url) {
		return issueRepository.getIssueByUrl(url);
	}
	
	private Issue prepareIssuePersist(Event e) {
		Issue issue;

		issue = this.getIssueByUrl(e.getIssue().getUrl());

		if (issue == null) {
			issue = e.getIssue();
			if (issue.getEvents() == null) {
				issue.setEvents(new ArrayList<Event>());
			}
			issue.getEvents().add(e);
		} else {
			e.setIssue(issue);
			issue.getEvents().add(e);
		}

		return issue;
	}

	@Override
	@Transactional
	public Issue updateIssue(Event e) {
		Issue issue = this.prepareIssuePersist(e);

		return this.saveIssue(issue);
	}
	
	@Override
	public Iterable<Issue> listIssuesByNumber(Integer number) {
		Iterable<Issue> issues;
		issues = issueRepository.findByNumber(number);
		return issues;
	}
	
}

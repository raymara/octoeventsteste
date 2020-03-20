package com.octoevents.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.octoevents.api.model.Event;
import com.octoevents.api.model.Issue;
import com.octoevents.api.model.form.EventService;
import com.octoevents.api.model.form.IssueService;
import com.octoevents.api.repository.EventRepository;

@Service
public class EventServiceAction implements EventService {
	
	private EventRepository eventRepository;
	
	private IssueService issueService;
	
	@Autowired
	public void setEventRepository(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}
	
	@Autowired
	public void setIssueService(IssueService issueService) {
		this.issueService = issueService;
	}
	
	@Override
	public Iterable<Event> listAllEvents() {
		return eventRepository.findAll();
	}
	
	@Override
	public Iterable<Event> listEventsIssue(Issue issue) {
		return eventRepository.findByIssue(issue);
	}
	
	@Override
	public Event getEventById(Integer id) {
		return eventRepository.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	public Event saveEvent(Event event) {
		return eventRepository.save(event);
	}
	
	@Override
	@Transactional
	public void deleteEvent(Event event) {
		eventRepository.delete(event);
	}
	
	@Override
	public List<Event> listEventsByIssueNumber(Integer number) {
		List<Event> events = new ArrayList<>();
		Iterable<Issue> issues = issueService.listIssuesByNumber(number);
		for (Issue i : issues) {
			if (events == null) {
				events = i.getEvents();
			} else {
				events.addAll(i.getEvents());
			}
		}
		return events;
	}
}

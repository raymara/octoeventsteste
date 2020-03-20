package com.octoevents.api.model.form;


import java.util.List;

import org.springframework.stereotype.Service;

import com.octoevents.api.model.Event;
import com.octoevents.api.model.Issue;

@Service
public interface EventService {
    Iterable<Event> listAllEvents();
    
    Iterable<Event> listEventsIssue(Issue issue);
    
    Event getEventById(Integer id);

    Event saveEvent(Event event);

    void deleteEvent(Event event);
    
    List<Event> listEventsByIssueNumber(Integer number);
    
}

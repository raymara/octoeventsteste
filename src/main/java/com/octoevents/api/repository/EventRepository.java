package com.octoevents.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.octoevents.api.model.Event;
import com.octoevents.api.model.Issue;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer>{
	Iterable<Event> findByIssue(Issue issue);
}

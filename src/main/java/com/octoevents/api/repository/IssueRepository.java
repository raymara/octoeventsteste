package com.octoevents.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.octoevents.api.model.Issue;

@Repository
public interface IssueRepository extends CrudRepository<Issue, Integer>{
	
	public Issue getIssueByUrl(String url);
	
	public Iterable<Issue> findByNumber(Integer number);
}
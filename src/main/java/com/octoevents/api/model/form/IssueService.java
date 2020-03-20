package com.octoevents.api.model.form;


import org.springframework.stereotype.Service;

import com.octoevents.api.model.Event;
import com.octoevents.api.model.Issue;

@Service
public interface IssueService {
    Iterable<Issue> listAllIssues();
    
    Issue getIssueById(Integer id);
    
    Issue getIssueByUrl(String url);
    
    Iterable<Issue> listIssuesByNumber(Integer number);

    Issue saveIssue(Issue issue);

    void deleteIssue(Issue issue);
    
    Issue updateIssue(Event e);
}

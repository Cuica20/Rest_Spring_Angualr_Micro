package org.example.issuetracker.service;

import java.util.List;

import org.example.issuetracker.model.Issue;

public interface IssueService {

	List<Issue> findAll();
	
	Issue create(Issue issue);
	
	Issue find(Long id);
	
	Issue update(Issue issue);
	
	void delete(Long id);
}

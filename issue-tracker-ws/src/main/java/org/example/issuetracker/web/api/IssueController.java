package org.example.issuetracker.web.api;

import java.util.ArrayList;
import java.util.List;

import org.example.issuetracker.model.Issue;
import org.example.issuetracker.service.IssueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IssueController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	// se debe trabajar con la interface
	private IssueService issueService;

	@RequestMapping(value = "/issues", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE 														// 405
	)
	public ResponseEntity<List<Issue>> getAllIssues() {
		logger.info("> getAllIssues");
		List<Issue> issues = null;

		try {
			issues = issueService.findAll();
			if (issues == null) {
				issues = new ArrayList<Issue>();
			}

		} catch (Exception e) {
			logger.error("Ocurrio una exception.", e);
			return new ResponseEntity<List<Issue>>(issues,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		logger.info("< getAllIssues");
		return new ResponseEntity<List<Issue>>(issues, HttpStatus.OK);
	}

	@RequestMapping(value = "/issues", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Issue> createIssue(@RequestBody Issue issue) {
		logger.info("> createIssue");

		Issue createdIssue = null;

		try {
			createdIssue = issueService.create(issue);
		} catch (Exception ex) {
			logger.error("Unexpected exception caught.", ex);
			return new ResponseEntity<Issue>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		logger.info("< createIssue");
		return new ResponseEntity<Issue>(createdIssue, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/issues/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE

	)
	public ResponseEntity<Issue> getIssue(@PathVariable("id") Long id) {
		logger.info("> getIssue");

		Issue issue = null;

		try {
			issue = issueService.find(id);
			if (issue == null) {
				return new ResponseEntity<Issue>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception ex) {
			logger.error("Unexprected exception caught", ex);
			return new ResponseEntity<Issue>(issue,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		logger.info("< getIssue");
		return new ResponseEntity<Issue>(issue, HttpStatus.OK);
	}

	@RequestMapping(value = "/issues/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Issue> updateIssue(@RequestBody Issue issue) {
		logger.info("> updateIssue");

		Issue updatedIssue = null;
		try {
			updatedIssue = issueService.update(issue);
		} catch (Exception e) {
			logger.error("Unexpected Exception caught.", e);
			return new ResponseEntity<Issue>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		logger.info("< updateIssue");
		return new ResponseEntity<Issue>(updatedIssue, HttpStatus.OK);
	}

	@RequestMapping(value = "/issues/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Issue> deleteIssue(@PathVariable("id") Long issueId) {
		logger.info("> deleteIssue");

		try {
			issueService.delete(issueId);
		} catch (Exception e) {
			logger.error("Unexpected Exception caught.", e);
			return new ResponseEntity<Issue>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		logger.info("< deleteIssue");
		return new ResponseEntity<Issue>(HttpStatus.NO_CONTENT);
	}
}

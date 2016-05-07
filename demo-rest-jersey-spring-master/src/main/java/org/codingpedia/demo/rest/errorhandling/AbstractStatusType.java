package org.codingpedia.demo.rest.errorhandling;

import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.Status.Family;
import javax.ws.rs.core.Response.StatusType;

/**
 * Class used to provide custom StatusTypes, especially for the the Reason Phrase that appears in the HTTP Status Response
 */
public abstract class AbstractStatusType implements StatusType {

	public AbstractStatusType(final Family family, final int statusCode,
                          final String reasonPhrase) {
	    super();
	
	    this.family = family;
	    this.statusCode = statusCode;
	    this.reasonPhrase = reasonPhrase;
	}
	
	protected AbstractStatusType(final Status status,
	                             final String reasonPhrase) {
	    this(status.getFamily(), status.getStatusCode(), reasonPhrase);
	}
	
	public Family getFamily() { return family; }
	
	public String getReasonPhrase() { return reasonPhrase; }
	
	public int getStatusCode() { return statusCode; }

	private final Family family;
	private final int statusCode;
	private final String reasonPhrase;
	
}

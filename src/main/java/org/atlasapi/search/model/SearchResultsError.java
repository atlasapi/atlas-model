package org.atlasapi.search.model;

import com.metabroadcast.common.http.HttpStatusCode;

public class SearchResultsError {

	private HttpStatusCode code;
	
	private String message;

	public SearchResultsError() {}
	
	public SearchResultsError(HttpStatusCode code, String message) {
		this.code = code;
		this.message = message;
	}

	public HttpStatusCode getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}

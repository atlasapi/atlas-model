package org.atlasapi.search.model;

import com.metabroadcast.common.http.HttpStatusCode;

public class SearchResultsError {

	private final HttpStatusCode code;
	
	private final String message;

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

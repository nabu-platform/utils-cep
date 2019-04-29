package be.nabu.utils.cep.impl;

import java.net.URI;

import be.nabu.utils.cep.api.CEFField;
import be.nabu.utils.cep.api.HTTPComplexEvent;

public class HTTPComplexEventImpl extends NetworkedComplexEventImpl implements HTTPComplexEvent {
	private URI requestUri;
	
	@CEFField(key = "request")
	@Override
	public URI getRequestUri() {
		return requestUri;
	}
	public void setRequestUri(URI requestUri) {
		this.requestUri = requestUri;
	}
}

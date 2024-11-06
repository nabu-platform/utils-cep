/*
* Copyright (C) 2019 Alexander Verbruggen
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU Lesser General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public License
* along with this program. If not, see <https://www.gnu.org/licenses/>.
*/

package be.nabu.utils.cep.impl;

import java.net.URI;

import be.nabu.utils.cep.api.CEPField;
import be.nabu.utils.cep.api.HTTPComplexEvent;

public class HTTPComplexEventImpl extends NetworkedComplexEventImpl implements HTTPComplexEvent {
	private URI requestUri;
	private String method, userAgent, language;
	private Boolean bot;
	private Integer responseCode;
	
	@CEPField(key = "request")
	@Override
	public URI getRequestUri() {
		return requestUri;
	}
	public void setRequestUri(URI requestUri) {
		this.requestUri = requestUri;
	}
	
	@CEPField(key = "method")
	@Override
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	
	@CEPField(key = "agent")
	@Override
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	
	@CEPField(key = "lang")
	@Override
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
	@CEPField(key = "bot")
	@Override
	public Boolean getBot() {
		return bot;
	}
	public void setBot(Boolean bot) {
		this.bot = bot;
	}
	
	@CEPField(key = "response")
	@Override
	public Integer getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}

}

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

package be.nabu.utils.cep.api;

import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

public interface ComplexEvent {
	// server settings
	public String getServerGroup();
	public String getServerName();
	public String getServerHost();
	
	// the artifact id where the event originated
	public String getArtifactId();
	// a local identifier (e.g. a rule id)
	public String getLocalId();
	// a custom categorization
	public String getEventCategory();
	// the name of the event, e.g. "Port Scan" (no variables) or "HTTP Request"
	// should be human readable
	public String getEventName();
	// how many times was this same event observed
	public Integer getEventCount();
	// the severity
	public EventSeverity getSeverity();
	
	// user information
	public String getRealm();
	public String getAlias();
	public String getAuthenticationId();
	
	// impersonator information (if any)
	public String getImpersonatorRealm();
	public String getImpersonator();
	public String getImpersonatorId();
	
	public Date getCreated();
	
	// only relevant if it takes time
	public Date getStarted();
	public Date getStopped();
	public Long getDuration();
	
	// the timezone for this event
	public TimeZone getTimezone();
	
	// longer message
	public String getMessage();
	
	// a structural code for this event (e.g. error code)
	public String getCode();
	// a stack trace in case this event involves an exception
	public String getStacktrace();
	// a free-to-use context that you can use to explain the circumstances of the event
	public String getContext();
	
	// the action that was taken by the device
	public String getAction();
	// an external id for this event
	public String getExternalId();
	// the reason this event was generated
	public String getReason();
	// any structured data for this event
	public String getData();
	// if the event is correlated to something
	public String getCorrelationId();
	// if the event is correlated to a larger conversation (usually cross system)
	public String getConversationId();
	// the device that triggered it
	public String getDeviceId();
	// any additional extensions
	public Map<String, Object> getExtensions();
	
	public Double getSourceLongitude();
	public Double getSourceLatitude();
	
	public Double getDestinationLongitude();
	public Double getDestinationLatitude();
	
	public String getSessionId();
}

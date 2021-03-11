package be.nabu.utils.cep.impl;

import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

import be.nabu.utils.cep.api.CEPField;
import be.nabu.utils.cep.api.CEPIdentifiable;
import be.nabu.utils.cep.api.CEPIgnore;
import be.nabu.utils.cep.api.ComplexEvent;
import be.nabu.utils.cep.api.EventSeverity;

public class ComplexEventImpl implements ComplexEvent {
	private String artifactId, localId, eventName, realm, alias, message, serverGroup, serverName, serverHost, action, eventCategory, externalId, reason, correlationId, deviceId, externalDependency;
	private Date created, started, stopped;
	private Long duration;
	private Integer eventCount;
	private EventSeverity severity;
	private String code, stacktrace, context, sessionId;
	private TimeZone timezone;
	private String origin;
	private Map<String, Object> extensions; // = new HashMap<String, Object>()
	private Double sourceLongitude, sourceLatitude, destinationLongitude, destinationLatitude;

	@CEPField(key = "artifact")
	@Override
	public String getArtifactId() {
		return artifactId;
	}
	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}
	
	@CEPIgnore
	@Override
	public String getLocalId() {
		return localId;
	}
	public void setLocalId(String localId) {
		this.localId = localId;
	}
	
	@CEPIgnore
	@Override
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
	@CEPField(key = "srealm")
	@Override
	public String getRealm() {
		return realm;
	}
	public void setRealm(String realm) {
		this.realm = realm;
	}
	
	@CEPIdentifiable
	@CEPField(key = "suser")
	@Override
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	@CEPField(key = "msg")
	@Override
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@CEPField(key = "rt")
	@Override
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
	@CEPField(key = "start")
	@Override
	public Date getStarted() {
		return started;
	}
	public void setStarted(Date started) {
		this.started = started;
	}
	
	@CEPField(key = "end")
	@Override
	public Date getStopped() {
		return stopped;
	}
	public void setStopped(Date stopped) {
		this.stopped = stopped;
	}
	
	@CEPField(key = "dur")
	@Override
	public Long getDuration() {
		return duration;
	}
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	
	@CEPField(key = "cnt")
	@Override
	public Integer getEventCount() {
		return eventCount;
	}
	public void setEventCount(Integer eventCount) {
		this.eventCount = eventCount;
	}
	
	@CEPIgnore
	@Override
	public EventSeverity getSeverity() {
		return severity;
	}
	public void setSeverity(EventSeverity severity) {
		this.severity = severity;
	}
	
	@CEPField(key = "srvgrp")
	@Override
	public String getServerGroup() {
		return serverGroup;
	}
	public void setServerGroup(String serverGroup) {
		this.serverGroup = serverGroup;
	}
	
	@CEPField(key = "srvnm")
	@Override
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	@CEPField(key = "srvhst")
	@Override
	public String getServerHost() {
		return serverHost;
	}
	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}
	
	@CEPField(key = "code")
	@Override
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@CEPField(key = "stacktrace")
	@Override
	public String getStacktrace() {
		return stacktrace;
	}
	public void setStacktrace(String stacktrace) {
		this.stacktrace = stacktrace;
	}
	
	@CEPField(key = "context")
	@Override
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	
	@CEPField(key = "dtz")
	@Override
	public TimeZone getTimezone() {
		return timezone;
	}
	public void setTimezone(TimeZone timezone) {
		this.timezone = timezone;
	}

	@Override
	@CEPField(key = "act")
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	@Override
	@CEPField(key = "cat")
	public String getEventCategory() {
		return eventCategory;
	}
	public void setEventCategory(String eventCategory) {
		this.eventCategory = eventCategory;
	}
	
	@Override
	@CEPField(key = "externalId")
	public String getExternalId() {
		return externalId;
	}
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	
	@Override
	@CEPField(key = "reason")
	@CEPIdentifiable
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	@Override
	@CEPField(key = "correlationId")
	public String getCorrelationId() {
		return correlationId;
	}
	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}
	
	@Override
	@CEPField(key = "sdevice")
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	@Override
	@CEPIgnore
	public Map<String, Object> getExtensions() {
		return extensions;
	}
	public void setExtensions(Map<String, Object> extensions) {
		this.extensions = extensions;
	}
	
	@CEPField(key = "origin")
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	@CEPField(key = "externalDependency")
	public String getExternalDependency() {
		return externalDependency;
	}
	public void setExternalDependency(String externalDependency) {
		this.externalDependency = externalDependency;
	}
	
	@CEPField(key = "slong")
	public Double getSourceLongitude() {
		return sourceLongitude;
	}
	public void setSourceLongitude(Double sourceLongitude) {
		this.sourceLongitude = sourceLongitude;
	}
	
	@CEPField(key = "slat")
	public Double getSourceLatitude() {
		return sourceLatitude;
	}
	public void setSourceLatitude(Double sourceLatitude) {
		this.sourceLatitude = sourceLatitude;
	}
	
	@CEPField(key = "dlong")
	public Double getDestinationLongitude() {
		return destinationLongitude;
	}
	public void setDestinationLongitude(Double destinationLongitude) {
		this.destinationLongitude = destinationLongitude;
	}
	
	@CEPField(key = "dlat")
	public Double getDestinationLatitude() {
		return destinationLatitude;
	}
	public void setDestinationLatitude(Double destinationLatitude) {
		this.destinationLatitude = destinationLatitude;
	}
	
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
}

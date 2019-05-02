package be.nabu.utils.cep.impl;

import java.util.Date;
import java.util.TimeZone;

import be.nabu.utils.cep.api.CEPField;
import be.nabu.utils.cep.api.CEPIdentifiable;
import be.nabu.utils.cep.api.CEPIgnore;
import be.nabu.utils.cep.api.ComplexEvent;
import be.nabu.utils.cep.api.EventSeverity;

public class ComplexEventImpl implements ComplexEvent {
	private String artifactId, localId, eventName, realm, alias, message, serverGroup, serverName, serverHost;
	private Date created, started, stopped;
	private Long duration;
	private Integer eventCount;
	private EventSeverity severity;
	private String code, stacktrace, context;
	private TimeZone timezone;

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
	
}

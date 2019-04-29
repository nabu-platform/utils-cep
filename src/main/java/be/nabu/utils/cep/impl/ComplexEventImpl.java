package be.nabu.utils.cep.impl;

import java.util.Date;

import be.nabu.utils.cep.api.CEFField;
import be.nabu.utils.cep.api.CEFIgnore;
import be.nabu.utils.cep.api.ComplexEvent;
import be.nabu.utils.cep.api.Severity;

public class ComplexEventImpl implements ComplexEvent {
	private String artifactId, localId, eventName, realm, alias, message, serverGroup, serverName, serverHost;
	private Date created, started, stopped;
	private Long duration;
	private Integer eventCount;
	private Severity severity;

	@CEFIgnore
	@Override
	public String getArtifactId() {
		return artifactId;
	}
	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}
	
	@CEFIgnore
	@Override
	public String getLocalId() {
		return localId;
	}
	public void setLocalId(String localId) {
		this.localId = localId;
	}
	
	@CEFIgnore
	@Override
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
	@CEFField(key = "srealm")
	@Override
	public String getRealm() {
		return realm;
	}
	public void setRealm(String realm) {
		this.realm = realm;
	}
	
	@CEFField(key = "suser")
	@Override
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	@CEFField(key = "msg")
	@Override
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@CEFField(key = "rt")
	@Override
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
	@CEFField(key = "start")
	@Override
	public Date getStarted() {
		return started;
	}
	public void setStarted(Date started) {
		this.started = started;
	}
	
	@CEFField(key = "end")
	@Override
	public Date getStopped() {
		return stopped;
	}
	public void setStopped(Date stopped) {
		this.stopped = stopped;
	}
	
	@CEFField(key = "dur")
	@Override
	public Long getDuration() {
		return duration;
	}
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	
	@CEFField(key = "cnt")
	@Override
	public Integer getEventCount() {
		return eventCount;
	}
	public void setEventCount(Integer eventCount) {
		this.eventCount = eventCount;
	}
	
	@CEFIgnore
	@Override
	public Severity getSeverity() {
		return severity;
	}
	public void setSeverity(Severity severity) {
		this.severity = severity;
	}
	
	@CEFField(key = "srvgrp")
	@Override
	public String getServerGroup() {
		return serverGroup;
	}
	public void setServerGroup(String serverGroup) {
		this.serverGroup = serverGroup;
	}
	
	@CEFField(key = "srvnm")
	@Override
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	@CEFField(key = "srvhst")
	@Override
	public String getServerHost() {
		return serverHost;
	}
	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}
}

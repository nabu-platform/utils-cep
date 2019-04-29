package be.nabu.utils.cep.api;

import java.util.Date;

public interface ComplexEvent {
	// server settings
	public String getServerGroup();
	public String getServerName();
	public String getServerHost();
	
	// the artifact id where the event originated
	public String getArtifactId();
	// a local identifier (e.g. a rule id)
	public String getLocalId();
	// the name of the event, e.g. "Port Scan" (no variables) or "HTTP Request"
	// should be human readable
	public String getEventName();
	// how many times was this same event observed
	public Integer getEventCount();
	// the severity
	public Severity getSeverity();
	
	// user information
	public String getRealm();
	public String getAlias();
	
	public Date getCreated();
	
	// only relevant if it takes time
	public Date getStarted();
	public Date getStopped();
	public Long getDuration();
	
	// longer message
	public String getMessage();
}

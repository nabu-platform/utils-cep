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

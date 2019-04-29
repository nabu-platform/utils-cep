package be.nabu.utils.cep.api;

import java.util.Date;
import java.util.Map;

public interface CommonEvent {
	public Date getCreated();
	public String getHost();
	
	// current version is 0
	public Integer getVersion();
	
	// uniquely identify the creator of the event
	public String getDeviceVendor();
	public String getDeviceProduct();
	public String getDeviceVersion();
	
	// a unique event identifier for that unique creator
	public String getDeviceEventClassId();
	
	// a human readable description of the event (no variables, do those in extensions)
	public String getName();
	
	public Severity getSeverity();
	
	public Map<String, Object> getExtensions();
	
}

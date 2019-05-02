package be.nabu.utils.cep.api;

import java.net.URI;

public interface HTTPComplexEvent extends NetworkedComplexEvent {
	public String getMethod();
	public URI getRequestUri();
	public String getUserAgent();
	public String getLanguage();
	public Boolean getBot();
	public Integer getResponseCode();
}

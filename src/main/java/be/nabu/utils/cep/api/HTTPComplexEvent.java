package be.nabu.utils.cep.api;

import java.net.URI;

public interface HTTPComplexEvent extends NetworkedComplexEvent {
	public URI getRequestUri();
}

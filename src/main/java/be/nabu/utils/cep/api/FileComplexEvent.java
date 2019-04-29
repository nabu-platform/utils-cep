package be.nabu.utils.cep.api;

import java.net.URI;

public interface FileComplexEvent extends ComplexEvent {
	public URI getFileUri();
	public Long getFileSize();
}

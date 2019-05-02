package be.nabu.utils.cep.impl;

import java.net.URI;

import be.nabu.utils.cep.api.CEPField;
import be.nabu.utils.cep.api.FileComplexEvent;

public class FileComplexEventImpl extends NetworkedComplexEventImpl implements FileComplexEvent {
	private URI fileUri;
	private Long fileSize;
	
	@CEPField(key = "fname")
	@Override
	public URI getFileUri() {
		return fileUri;
	}
	public void setFileUri(URI fileUri) {
		this.fileUri = fileUri;
	}
	
	@CEPField(key = "fsize")
	@Override
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
}

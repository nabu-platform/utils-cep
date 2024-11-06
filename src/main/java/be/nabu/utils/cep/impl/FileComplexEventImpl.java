/*
* Copyright (C) 2019 Alexander Verbruggen
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU Lesser General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public License
* along with this program. If not, see <https://www.gnu.org/licenses/>.
*/

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

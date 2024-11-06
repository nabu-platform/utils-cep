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
	
	public EventSeverity getSeverity();
	
	public Map<String, Object> getExtensions();
	
}

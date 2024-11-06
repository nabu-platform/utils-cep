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

public enum CommonEventExtension {
	TIMEZONE("dtz", "deviceTimeZone", 255, "The timezone for the device generating the event", String.class),
	CREATED("rt", "deviceReceiptTime", 0, "The time at which the event related to the activity was received. The format is MMM dd yyyy HH:mm:ss or milliseconds since epoch (Jan 1 st 1970)", Object.class),
	HOST("shost", "sourceHostName", 1023, "Identifies the source that an event refers to in an IP network. The format should be a fully qualified domain name (DQDN) associated with the source node, when a mode is available. Examples: 'host' or 'host.domain.com'.", String.class)
	;
	private String name;
	private String fullName;
	private Integer length;
	private String meaning;
	private Class<?> dataType;

	private CommonEventExtension(String name, String fullName, Integer length, String meaning, Class<?> dataType) {
		this.name = name;
		this.fullName = fullName;
		this.length = length;
		this.meaning = meaning;
		this.dataType = dataType;
	}

	public String getName() {
		return name;
	}

	public String getFullName() {
		return fullName;
	}

	public Integer getLength() {
		return length;
	}

	public String getMeaning() {
		return meaning;
	}

	public Class<?> getDataType() {
		return dataType;
	}
}

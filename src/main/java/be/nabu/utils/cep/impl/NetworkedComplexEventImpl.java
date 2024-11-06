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

import be.nabu.utils.cep.api.CEPField;
import be.nabu.utils.cep.api.CEPIdentifiable;
import be.nabu.utils.cep.api.NetworkedComplexEvent;

public class NetworkedComplexEventImpl extends ComplexEventImpl implements NetworkedComplexEvent {
	private String sourceHost, sourceIp, destinationHost, destinationIp, applicationProtocol, networkProtocol, transportProtocol;
	private Integer sourcePort, destinationPort;
	private Long sizeIn, sizeOut;
	
	@CEPIdentifiable
	@CEPField(key = "shost")
	@Override
	public String getSourceHost() {
		return sourceHost;
	}
	public void setSourceHost(String sourceHost) {
		this.sourceHost = sourceHost;
	}
	
	@CEPIdentifiable
	@CEPField(key = "src")
	@Override
	public String getSourceIp() {
		return sourceIp;
	}
	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}
	
	@CEPField(key = "dhost")
	@Override
	public String getDestinationHost() {
		return destinationHost;
	}
	public void setDestinationHost(String destinationHost) {
		this.destinationHost = destinationHost;
	}
	
	@CEPField(key = "dst")
	@Override
	public String getDestinationIp() {
		return destinationIp;
	}
	public void setDestinationIp(String destinationIp) {
		this.destinationIp = destinationIp;
	}
	
	@CEPField(key = "proto")
	@Override	
	public String getApplicationProtocol() {
		return applicationProtocol;
	}
	public void setApplicationProtocol(String applicationProtocol) {
		this.applicationProtocol = applicationProtocol;
	}
	
	@CEPField(key = "network")
	@Override
	public String getNetworkProtocol() {
		return networkProtocol;
	}
	public void setNetworkProtocol(String networkProtocol) {
		this.networkProtocol = networkProtocol;
	}
	
	@CEPField(key = "transport")
	@Override
	public String getTransportProtocol() {
		return transportProtocol;
	}
	public void setTransportProtocol(String transportProtocol) {
		this.transportProtocol = transportProtocol;
	}
	@CEPField(key = "spt")
	@Override
	public Integer getSourcePort() {
		return sourcePort;
	}
	public void setSourcePort(Integer sourcePort) {
		this.sourcePort = sourcePort;
	}
	
	@CEPField(key = "dpt")
	@Override
	public Integer getDestinationPort() {
		return destinationPort;
	}
	public void setDestinationPort(Integer destinationPort) {
		this.destinationPort = destinationPort;
	}

	@CEPField(key = "in")
	@Override
	public Long getSizeIn() {
		return sizeIn;
	}
	public void setSizeIn(Long sizeIn) {
		this.sizeIn = sizeIn;
	}
	
	@CEPField(key = "out")
	@Override
	public Long getSizeOut() {
		return sizeOut;
	}
	public void setSizeOut(Long sizeOut) {
		this.sizeOut = sizeOut;
	}
}

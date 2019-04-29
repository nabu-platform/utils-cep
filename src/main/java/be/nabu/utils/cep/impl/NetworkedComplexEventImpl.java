package be.nabu.utils.cep.impl;

import be.nabu.utils.cep.api.CEFField;
import be.nabu.utils.cep.api.NetworkedComplexEvent;

public class NetworkedComplexEventImpl extends ComplexEventImpl implements NetworkedComplexEvent {
	private String sourceHost, sourceIp, destinationHost, destinationIp, protocol;
	private Integer sourcePort, destinationPort;
	private Long sizeIn, sizeOut;
	
	@CEFField(key = "shost")
	@Override
	public String getSourceHost() {
		return sourceHost;
	}
	public void setSourceHost(String sourceHost) {
		this.sourceHost = sourceHost;
	}
	
	@CEFField(key = "src")
	@Override
	public String getSourceIp() {
		return sourceIp;
	}
	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}
	
	@CEFField(key = "dhost")
	@Override
	public String getDestinationHost() {
		return destinationHost;
	}
	public void setDestinationHost(String destinationHost) {
		this.destinationHost = destinationHost;
	}
	
	@CEFField(key = "dst")
	@Override
	public String getDestinationIp() {
		return destinationIp;
	}
	public void setDestinationIp(String destinationIp) {
		this.destinationIp = destinationIp;
	}
	
	@CEFField(key = "proto")
	@Override
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	
	@CEFField(key = "spt")
	@Override
	public Integer getSourcePort() {
		return sourcePort;
	}
	public void setSourcePort(Integer sourcePort) {
		this.sourcePort = sourcePort;
	}
	
	@CEFField(key = "dpt")
	@Override
	public Integer getDestinationPort() {
		return destinationPort;
	}
	public void setDestinationPort(Integer destinationPort) {
		this.destinationPort = destinationPort;
	}

	@CEFField(key = "in")
	@Override
	public Long getSizeIn() {
		return sizeIn;
	}
	public void setSizeIn(Long sizeIn) {
		this.sizeIn = sizeIn;
	}
	
	@CEFField(key = "out")
	@Override
	public Long getSizeOut() {
		return sizeOut;
	}
	public void setSizeOut(Long sizeOut) {
		this.sizeOut = sizeOut;
	}
}

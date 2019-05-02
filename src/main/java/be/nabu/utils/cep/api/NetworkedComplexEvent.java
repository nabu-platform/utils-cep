package be.nabu.utils.cep.api;

public interface NetworkedComplexEvent extends ComplexEvent {
	public String getSourceHost();
	public String getSourceIp();
	public Integer getSourcePort();
	public String getDestinationHost();
	public String getDestinationIp();
	public Integer getDestinationPort();
	public String getApplicationProtocol();
	public String getTransportProtocol();
	public String getNetworkProtocol();
	public Long getSizeIn();
	public Long getSizeOut();
}

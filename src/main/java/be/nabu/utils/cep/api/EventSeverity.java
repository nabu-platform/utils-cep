package be.nabu.utils.cep.api;

public enum EventSeverity {
	TRACE(0),
	DEBUG(2),
	INFO(4),
	WARNING(6),
	ERROR(8),
	CRITICAL(10)
	;
	
	private int level;
	
	private EventSeverity(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}

}

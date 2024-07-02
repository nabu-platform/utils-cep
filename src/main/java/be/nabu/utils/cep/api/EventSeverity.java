package be.nabu.utils.cep.api;

/**
 * The severity is either a string or integer value (https://www.microfocus.com/documentation/arcsight/arcsight-smartconnectors-8.4/pdfdoc/cef-implementation-standard/cef-implementation-standard.pdf)
 * 
 * String values: Unknown, Low, Medium, High, and Very-High.
 * Int values: 0-3=Low, 4-6=Medium, 7- 8=High, and 9-10=Very-High
 *
 * an example of levels: https://docs.fortinet.com/document/fortigate/7.4.3/fortios-log-message-reference/671442/cef-priority-levels
 */
public enum EventSeverity {
	// extremely detailed logging information
	TRACE(0),
	// detailed logging information
	DEBUG(2),
	// normal logging information, it should probably be captured to understand the circumstances
	INFO(4),
	// something might be wrong, functionality could be affected
	WARNING(5),
	// slightly more important logging information, it does not indicate a problem but should definitely be captured
	NOTIFICATION(6),
	// something went wrong and functionality could be affected
	ERROR(7),
	// something went wrong and functionality is definitely affected
	CRITICAL(8),
	// immediate action is required
	ALERT(9),
	// the system is unusable/not responding
	EMERGENCY(10)
	;
	
	private int level;
	
	private EventSeverity(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}

}

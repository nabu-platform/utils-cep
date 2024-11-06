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

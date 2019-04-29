package be.nabu.utils.cep.api;

public enum Severity {
	LOW(0, 3, "Low"),
	MEDIUM(4, 6, "Medium"),
	HIGH(7, 8, "High"),
	VERY_HIGH(9, 10, "Very-High")
	;
	
	private int from;
	private int to;
	private String name;

	private Severity(int from, int to, String name) {
		this.from = from;
		this.to = to;
		this.name = name;
	}

	public int getFrom() {
		return from;
	}

	public int getTo() {
		return to;
	}

	public String getName() {
		return name;
	}
	
}

package mcstuff.rpg.ruleset.ability;

public enum E_AbilityType {
	NONE("-"),
	ANY("*"),
	
	STRENGTH("STR"),
	DEXTERITY("DEX"),
	CONSTITUTION("CON"),
	INTELLIGENCE("INT"),
	WISDOM("WIS"),
	CHARISMA("CHA"),
	;
	String abbreviation;
	private E_AbilityType(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
}

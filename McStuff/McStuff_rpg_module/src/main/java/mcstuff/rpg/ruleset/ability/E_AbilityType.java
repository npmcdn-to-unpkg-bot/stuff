/* Copyright (C) 2016+ Michael Cassidy - All Rights Reserved
	This file is a part of McStuff

    McStuff is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    McStuff is distributed as sample code in the hope that it will be 
    useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with McStuff.  If not, see <http://www.gnu.org/licenses/>. 
 */
package mcstuff.rpg.ruleset.ability;

public enum E_AbilityType {
	NONE("-"),
	ANY("*"),

	STRENGTH("STR"),
	DEXTERITY("DEX"),
	CONSTITUTION("CON"),
	INTELLIGENCE("INT"),
	WISDOM("WIS"),
	CHARISMA("CHA"),;
	String abbreviation;

	private E_AbilityType(final String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getAbbreviation() {
		return abbreviation;
	}
}

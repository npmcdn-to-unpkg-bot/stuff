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
package mcstuff.rpg.ruleset.alignment;

public enum E_Alignment {
	NONE(E_LawfulOrChaotic.NONE, E_GoodOrEvil.NONE),
	ANY(E_LawfulOrChaotic.ANY, E_GoodOrEvil.ANY),

	LAWFUL_GOOD(E_LawfulOrChaotic.LAWFUL, E_GoodOrEvil.GOOD),
	LAWFUL_NEUTRAL(E_LawfulOrChaotic.LAWFUL, E_GoodOrEvil.NEUTRAL),
	LAWFUL_EVIL(E_LawfulOrChaotic.LAWFUL, E_GoodOrEvil.EVIL),
	TRUE_NEUTRAL(E_LawfulOrChaotic.NEUTRAL, E_GoodOrEvil.NEUTRAL),
	CHAOTIC_GOOD(E_LawfulOrChaotic.CHAOTIC, E_GoodOrEvil.GOOD),
	CHAOTIC_NETRAL(E_LawfulOrChaotic.CHAOTIC, E_GoodOrEvil.NEUTRAL),
	CHAOTIC_EVIL(E_LawfulOrChaotic.CHAOTIC, E_GoodOrEvil.EVIL),;
	E_LawfulOrChaotic lawfulOrChaotic;
	E_GoodOrEvil goodOrEvil;

	private E_Alignment(final E_LawfulOrChaotic lawfulOrChaotic, final E_GoodOrEvil goodOrEvil) {
		this.lawfulOrChaotic = lawfulOrChaotic;
		this.goodOrEvil = goodOrEvil;
	}

	public E_GoodOrEvil getGoodOrEvil() {
		return goodOrEvil;
	}

	public E_LawfulOrChaotic getLawfulOrChaotic() {
		return lawfulOrChaotic;
	}
}

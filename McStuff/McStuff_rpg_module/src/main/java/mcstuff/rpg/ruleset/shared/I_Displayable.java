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
package mcstuff.rpg.ruleset.shared;

import org.apache.commons.lang3.text.WordUtils;

public interface I_Displayable {
	static String getDisplay(final Enum<?> enumValue) {
		String sValue = enumValue.toString().replace("__", "-");
		sValue = sValue.replace("_", " ");
		return WordUtils.capitalizeFully(sValue, ' ', '-');
	}

	static String getDisplay(final Object obj) {
		return obj.toString();
	}
}

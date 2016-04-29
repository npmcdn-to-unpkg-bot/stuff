package mcstuff.rpg.ruleset.core;

import org.apache.commons.lang3.text.WordUtils;

public interface I_Displayable {
	static String getDisplay(Enum<?> enumValue) {
		String sValue = enumValue.toString().replace("__", "-");
		sValue = sValue.replace("_", " ");
		return WordUtils.capitalizeFully(sValue,' ','-');
	}
	
	static String getDisplay(Object obj) {
		return obj.toString();
	}
}

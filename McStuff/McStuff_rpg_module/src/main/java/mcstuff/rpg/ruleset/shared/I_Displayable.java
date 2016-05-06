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

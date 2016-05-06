package mcstuff.rpg.ruleset.value;

import java.util.Set;

public class ModifiableIntegerValue extends ModifiableValue<Integer> {

	public ModifiableIntegerValue(final Integer value) {
		super(value);
	}

	public ModifiableIntegerValue(final Integer value, final Set<Modifier<Integer>> modifiers) {
		super(value, modifiers);
	}

	@Override
	public Integer getModifiedValue() {
		int iValue = getValue();
		for (final Modifier<Integer> modifier : getModifiers()) {
			iValue += modifier.getValue();
		}
		return iValue;
	}

}

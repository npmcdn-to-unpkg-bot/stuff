package rpg.ruleset.value;

import java.util.Set;

public class ModifiableIntegerValue extends ModifiableValue<Integer> {

	public ModifiableIntegerValue(Integer value) {
		super(value);
	}

	public ModifiableIntegerValue(Integer value, Set<Modifier<Integer>> modifiers) {
		super(value, modifiers);
	}

	@Override
	public Integer getModifiedValue() {
		int iValue = getValue();
		for(Modifier<Integer> modifier : getModifiers()) {
			iValue += modifier.getValue();
		}
		return iValue;
	}

}

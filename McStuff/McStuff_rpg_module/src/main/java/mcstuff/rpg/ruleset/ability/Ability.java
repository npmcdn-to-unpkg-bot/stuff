package mcstuff.rpg.ruleset.ability;

import mcstuff.rpg.ruleset.value.ModifiableIntegerValue;

import javafx.beans.property.ReadOnlyObjectWrapper;

public class Ability {
	private ReadOnlyObjectWrapper<E_AbilityType> abilityType = new ReadOnlyObjectWrapper<>();
	private ReadOnlyObjectWrapper<ModifiableIntegerValue> value = new ReadOnlyObjectWrapper<>();

	public Ability(final E_AbilityType abilityType) {
		super();
		this.abilityType.setValue(abilityType);
		value.setValue(new ModifiableIntegerValue(0));
	}

	public Ability(final E_AbilityType abilityType, final Integer rawValue) {
		super();
		this.abilityType.setValue(abilityType);
		value.setValue(new ModifiableIntegerValue(rawValue));
	}

	public ReadOnlyObjectWrapper<E_AbilityType> getAbilityType() {
		return abilityType;
	}

	public ReadOnlyObjectWrapper<ModifiableIntegerValue> getValue() {
		return value;
	}

	public void setAbilityType(final ReadOnlyObjectWrapper<E_AbilityType> abilityType) {
		this.abilityType = abilityType;
	}

	public void setValue(final ReadOnlyObjectWrapper<ModifiableIntegerValue> value) {
		this.value = value;
	}

}

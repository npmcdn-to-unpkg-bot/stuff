package mcstuff.rpg.ruleset.ability;

import javafx.beans.property.ReadOnlyObjectWrapper;
import mcstuff.rpg.ruleset.value.ModifiableIntegerValue;

public class Ability {
	private ReadOnlyObjectWrapper<E_AbilityType> abilityType = new ReadOnlyObjectWrapper<>();
	private ReadOnlyObjectWrapper<ModifiableIntegerValue> value = new ReadOnlyObjectWrapper<>();
	
	public Ability(E_AbilityType abilityType) {
		super();
		this.abilityType.setValue(abilityType);
		this.value.setValue(new ModifiableIntegerValue(0));
	}
	
	public ReadOnlyObjectWrapper<E_AbilityType> getAbilityType() {
		return abilityType;
	}

	public void setAbilityType(ReadOnlyObjectWrapper<E_AbilityType> abilityType) {
		this.abilityType = abilityType;
	}

	public ReadOnlyObjectWrapper<ModifiableIntegerValue> getValue() {
		return value;
	}

	public void setValue(ReadOnlyObjectWrapper<ModifiableIntegerValue> value) {
		this.value = value;
	}

	public Ability(E_AbilityType abilityType, Integer rawValue) {
		super();
		this.abilityType.setValue(abilityType);
		this.value.setValue(new ModifiableIntegerValue(rawValue));
	}
	
}

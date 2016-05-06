package mcstuff.rpg.ruleset.modifier;

import javafx.beans.property.ReadOnlyObjectWrapper;

public class ModifierTarget {
	private final ReadOnlyObjectWrapper<E_ModifierTargetType> targetType = new ReadOnlyObjectWrapper<>();
	private final ReadOnlyObjectWrapper<I_ModifierQualifier> qualifier = new ReadOnlyObjectWrapper<>();

	public ModifierTarget(final E_ModifierTargetType targetType, final I_ModifierQualifier qualifier) {
		super();
		this.targetType.setValue(targetType);
		this.qualifier.setValue(qualifier);
	}

	public final mcstuff.rpg.ruleset.modifier.I_ModifierQualifier getQualifier() {
		return qualifierProperty().get();
	}

	public final mcstuff.rpg.ruleset.modifier.E_ModifierTargetType getTargetType() {
		return targetTypeProperty().get();
	}

	public final javafx.beans.property.ReadOnlyObjectProperty<mcstuff.rpg.ruleset.modifier.I_ModifierQualifier> qualifierProperty() {
		return qualifier.getReadOnlyProperty();
	}

	public final javafx.beans.property.ReadOnlyObjectProperty<mcstuff.rpg.ruleset.modifier.E_ModifierTargetType> targetTypeProperty() {
		return targetType.getReadOnlyProperty();
	}

}

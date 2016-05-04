package mcstuff.rpg.ruleset.core;

import javafx.beans.property.ReadOnlyObjectWrapper;

public class ModifierTarget {
	private ReadOnlyObjectWrapper<E_ModifierTargetType> targetType = new ReadOnlyObjectWrapper<>();
	private ReadOnlyObjectWrapper<I_ModifierQualifier> qualifier = new ReadOnlyObjectWrapper<>();
	
	public ModifierTarget(E_ModifierTargetType targetType, I_ModifierQualifier qualifier) {
		super();
		this.targetType.setValue(targetType);
		this.qualifier.setValue(qualifier);
	}

	public final javafx.beans.property.ReadOnlyObjectProperty<mcstuff.rpg.ruleset.core.E_ModifierTargetType> targetTypeProperty() {
		return this.targetType.getReadOnlyProperty();
	}
	

	public final mcstuff.rpg.ruleset.core.E_ModifierTargetType getTargetType() {
		return this.targetTypeProperty().get();
	}
	

	public final javafx.beans.property.ReadOnlyObjectProperty<mcstuff.rpg.ruleset.core.I_ModifierQualifier> qualifierProperty() {
		return this.qualifier.getReadOnlyProperty();
	}
	

	public final mcstuff.rpg.ruleset.core.I_ModifierQualifier getQualifier() {
		return this.qualifierProperty().get();
	}

}

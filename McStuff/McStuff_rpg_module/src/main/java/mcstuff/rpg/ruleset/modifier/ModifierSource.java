package mcstuff.rpg.ruleset.modifier;

import javafx.beans.property.ReadOnlyObjectWrapper;

public class ModifierSource {
	private final ReadOnlyObjectWrapper<E_ModifierSourceType> sourceType = new ReadOnlyObjectWrapper<>();
	private final ReadOnlyObjectWrapper<I_ModifierQualifier> qualifier = new ReadOnlyObjectWrapper<>();

	public ModifierSource(final E_ModifierSourceType sourceType, final I_ModifierQualifier qualifier) {
		super();
		this.sourceType.setValue(sourceType);
		this.qualifier.setValue(qualifier);
	}

	public final mcstuff.rpg.ruleset.modifier.I_ModifierQualifier getQualifier() {
		return qualifierProperty().get();
	}

	public final mcstuff.rpg.ruleset.modifier.E_ModifierSourceType getSourceType() {
		return sourceTypeProperty().get();
	}

	public final javafx.beans.property.ReadOnlyObjectProperty<mcstuff.rpg.ruleset.modifier.I_ModifierQualifier> qualifierProperty() {
		return qualifier.getReadOnlyProperty();
	}

	public final javafx.beans.property.ReadOnlyObjectProperty<mcstuff.rpg.ruleset.modifier.E_ModifierSourceType> sourceTypeProperty() {
		return sourceType.getReadOnlyProperty();
	}

}

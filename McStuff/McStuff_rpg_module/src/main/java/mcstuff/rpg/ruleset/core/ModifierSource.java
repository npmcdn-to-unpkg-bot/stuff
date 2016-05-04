package mcstuff.rpg.ruleset.core;

import javafx.beans.property.ReadOnlyObjectWrapper;

public class ModifierSource {
	private ReadOnlyObjectWrapper<E_ModifierSourceType> sourceType = new ReadOnlyObjectWrapper<>();
	private ReadOnlyObjectWrapper<I_ModifierQualifier> qualifier = new ReadOnlyObjectWrapper<>();
	
	public ModifierSource(E_ModifierSourceType sourceType, I_ModifierQualifier qualifier) {
		super();
		this.sourceType.setValue(sourceType);
		this.qualifier.setValue(qualifier);
	}

	public final javafx.beans.property.ReadOnlyObjectProperty<mcstuff.rpg.ruleset.core.E_ModifierSourceType> sourceTypeProperty() {
		return this.sourceType.getReadOnlyProperty();
	}
	

	public final mcstuff.rpg.ruleset.core.E_ModifierSourceType getSourceType() {
		return this.sourceTypeProperty().get();
	}
	

	public final javafx.beans.property.ReadOnlyObjectProperty<mcstuff.rpg.ruleset.core.I_ModifierQualifier> qualifierProperty() {
		return this.qualifier.getReadOnlyProperty();
	}
	

	public final mcstuff.rpg.ruleset.core.I_ModifierQualifier getQualifier() {
		return this.qualifierProperty().get();
	}
	
	
	
	
}

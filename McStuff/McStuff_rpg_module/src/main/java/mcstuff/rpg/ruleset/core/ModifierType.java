package mcstuff.rpg.ruleset.core;

import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;

public class ModifierType {
	private ReadOnlyObjectWrapper<ModifierSource> source;
	private ReadOnlyObjectWrapper<ModifierTarget> target;
	private ReadOnlyBooleanWrapper stackable = new ReadOnlyBooleanWrapper(false);
	
	public ModifierType(ModifierSource source, ModifierTarget target) {
		this.source.setValue(source);
		this.target.setValue(target);
	}
	
	public ModifierType(ModifierSource source, ModifierTarget target, boolean stackable) {
		this.source.setValue(source);
		this.target.setValue(target);
		this.stackable.setValue(stackable);
	}

	public final javafx.beans.property.ReadOnlyObjectProperty<mcstuff.rpg.ruleset.core.ModifierSource> sourceProperty() {
		return this.source.getReadOnlyProperty();
	}
	

	public final mcstuff.rpg.ruleset.core.ModifierSource getSource() {
		return this.sourceProperty().get();
	}
	

	public final javafx.beans.property.ReadOnlyObjectProperty<mcstuff.rpg.ruleset.core.ModifierTarget> targetProperty() {
		return this.target.getReadOnlyProperty();
	}
	

	public final mcstuff.rpg.ruleset.core.ModifierTarget getTarget() {
		return this.targetProperty().get();
	}
	

	public final javafx.beans.property.ReadOnlyBooleanProperty stackableProperty() {
		return this.stackable.getReadOnlyProperty();
	}
	

	public final boolean isStackable() {
		return this.stackableProperty().get();
	}
	
}

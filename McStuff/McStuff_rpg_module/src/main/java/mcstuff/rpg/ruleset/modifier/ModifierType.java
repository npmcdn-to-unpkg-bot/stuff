package mcstuff.rpg.ruleset.modifier;

import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;

public class ModifierType {
	private ReadOnlyObjectWrapper<ModifierSource> source;
	private ReadOnlyObjectWrapper<ModifierTarget> target;
	private final ReadOnlyBooleanWrapper stackable = new ReadOnlyBooleanWrapper(false);

	public ModifierType(final ModifierSource source, final ModifierTarget target) {
		this.source.setValue(source);
		this.target.setValue(target);
	}

	public ModifierType(final ModifierSource source, final ModifierTarget target, final boolean stackable) {
		this.source.setValue(source);
		this.target.setValue(target);
		this.stackable.setValue(stackable);
	}

	public final mcstuff.rpg.ruleset.modifier.ModifierSource getSource() {
		return sourceProperty().get();
	}

	public final mcstuff.rpg.ruleset.modifier.ModifierTarget getTarget() {
		return targetProperty().get();
	}

	public final boolean isStackable() {
		return stackableProperty().get();
	}

	public final javafx.beans.property.ReadOnlyObjectProperty<mcstuff.rpg.ruleset.modifier.ModifierSource> sourceProperty() {
		return source.getReadOnlyProperty();
	}

	public final javafx.beans.property.ReadOnlyBooleanProperty stackableProperty() {
		return stackable.getReadOnlyProperty();
	}

	public final javafx.beans.property.ReadOnlyObjectProperty<mcstuff.rpg.ruleset.modifier.ModifierTarget> targetProperty() {
		return target.getReadOnlyProperty();
	}

}

package mcstuff.rpg.ruleset.modifier;

import java.util.Date;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;

import mcstuff.rpg.ruleset.value.Value;

public class Modifier<V> extends Value<V> {
	private final ReadOnlyObjectWrapper<ModifierType> modifierType = new ReadOnlyObjectWrapper<>();
	private final ObjectProperty<Date> expiresOn = new SimpleObjectProperty<>();

	public Modifier(final ModifierType modifierType, final V value) {
		super(value);
		this.modifierType.setValue(modifierType);
	}

	public Modifier(final ModifierType modifierType, final V value, final Date expiresOn) {
		super(value);
		this.modifierType.setValue(modifierType);
		this.expiresOn.setValue(expiresOn);
	}

	public final ObjectProperty<Date> expiresOnProperty() {
		return this.expiresOn;
	}

	public final java.util.Date getExpiresOn() {
		return this.expiresOnProperty().get();
	}

	public final ModifierType getModifierType() {
		return this.modifierTypeProperty().get();
	}

	public final javafx.beans.property.ReadOnlyObjectProperty<ModifierType> modifierTypeProperty() {
		return this.modifierType.getReadOnlyProperty();
	}

	public final void setExpiresOn(final java.util.Date expiresOn) {
		this.expiresOnProperty().set(expiresOn);
	}

}

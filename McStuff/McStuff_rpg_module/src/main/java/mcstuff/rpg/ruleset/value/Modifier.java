package mcstuff.rpg.ruleset.value;

import java.util.Date;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import mcstuff.rpg.ruleset.core.ModifierType;

public class Modifier<V> extends Value<V> {
	private ReadOnlyObjectWrapper<ModifierType> modifierType = new ReadOnlyObjectWrapper<>();
	private ObjectProperty<Date> expiresOn = new SimpleObjectProperty<>();

	public Modifier(ModifierType modifierType, V value) {
		super(value);		
		this.modifierType.setValue(modifierType);
	}
	
	public Modifier(ModifierType modifierType, V value, Date expiresOn) {
		super(value);		
		this.modifierType.setValue(modifierType);
		this.expiresOn.setValue(expiresOn);
	}

	public final javafx.beans.property.ReadOnlyObjectProperty<ModifierType> modifierTypeProperty() {
		return this.modifierType.getReadOnlyProperty();
	}	

	public final ModifierType getModifierType() {
		return this.modifierTypeProperty().get();
	}

	public final ObjectProperty<Date> expiresOnProperty() {
		return this.expiresOn;
	}

	public final java.util.Date getExpiresOn() {
		return this.expiresOnProperty().get();
	}

	public final void setExpiresOn(final java.util.Date expiresOn) {
		this.expiresOnProperty().set(expiresOn);
	}
	
}

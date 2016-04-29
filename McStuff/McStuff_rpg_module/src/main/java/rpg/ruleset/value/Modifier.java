package rpg.ruleset.value;

import java.util.Date;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import rpg.ruleset.core.E_ModifierType;

public class Modifier<T> extends Value<T> {
	private ReadOnlyObjectWrapper<E_ModifierType> modifierType = new ReadOnlyObjectWrapper<>();
	private ObjectProperty<Date> expiresOn = new SimpleObjectProperty<>();

	public Modifier(E_ModifierType modifierType, T value) {
		super(value);		
		this.modifierType.setValue(modifierType);
	}
	
	public Modifier(E_ModifierType modifierType, T value, Date expiresOn) {
		super(value);		
		this.modifierType.setValue(modifierType);
		this.expiresOn.setValue(expiresOn);
	}

	public final javafx.beans.property.ReadOnlyObjectProperty<rpg.ruleset.core.E_ModifierType> modifierTypeProperty() {
		return this.modifierType.getReadOnlyProperty();
	}	

	public final rpg.ruleset.core.E_ModifierType getModifierType() {
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

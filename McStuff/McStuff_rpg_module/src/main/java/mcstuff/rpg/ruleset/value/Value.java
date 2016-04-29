package mcstuff.rpg.ruleset.value;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Value<T> {
	private ObjectProperty<T> value = new SimpleObjectProperty<>();
	
	public Value(T value) {
		super();
		this.value.setValue(value);
	}

	public final ObjectProperty<T> valueProperty() {
		return this.value;
	}
	
	public final T getValue() {
		return this.valueProperty().get();
	}

	public final void setValue(final T value) {
		this.valueProperty().set(value);
	}
	
}

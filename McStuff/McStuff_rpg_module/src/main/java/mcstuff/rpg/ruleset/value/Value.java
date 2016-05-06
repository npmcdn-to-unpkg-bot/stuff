package mcstuff.rpg.ruleset.value;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Value<V> {
	private final ObjectProperty<V> value = new SimpleObjectProperty<>();

	public Value(final V value) {
		super();
		this.value.setValue(value);
	}

	public final V getValue() {
		return this.valueProperty().get();
	}

	public final void setValue(final V value) {
		this.valueProperty().set(value);
	}

	public final ObjectProperty<V> valueProperty() {
		return this.value;
	}

}

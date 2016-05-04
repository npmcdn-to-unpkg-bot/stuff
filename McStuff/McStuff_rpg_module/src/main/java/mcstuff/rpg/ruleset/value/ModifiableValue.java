package mcstuff.rpg.ruleset.value;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import javafx.beans.property.ReadOnlySetProperty;
import javafx.beans.property.ReadOnlySetWrapper;
import javafx.collections.ObservableSet;

public abstract class ModifiableValue<V> extends Value<V> {
	
	private ReadOnlySetWrapper<Modifier<V>> modifiers = new ReadOnlySetWrapper<>();

	public ModifiableValue(V value) {
		super(value);
	}

	public ModifiableValue(V value, Set<Modifier<V>> modifiers) {
		super(value);
		this.modifiers.addAll(modifiers);
	}

	public final ReadOnlySetProperty<Modifier<V>> modifiersProperty() {
		return this.modifiers.getReadOnlyProperty();
	}
	
	public final ObservableSet<Modifier<V>> getModifiers() {
		return this.modifiersProperty().get();
	}
	
	public abstract V getModifiedValue();
	
	public void refreshModifiers() {
		for(Iterator<Modifier<V>> itModifier = modifiers.iterator(); itModifier.hasNext();) {
			Modifier<V> modifier = itModifier.next();
			if(modifier.getExpiresOn().after(new Date())) {
				itModifier.remove();
			}
		}
	}
	
}

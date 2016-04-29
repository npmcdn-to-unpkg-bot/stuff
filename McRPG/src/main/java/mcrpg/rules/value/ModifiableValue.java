package mcrpg.rules.value;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import javafx.beans.property.ReadOnlySetProperty;
import javafx.beans.property.ReadOnlySetWrapper;
import javafx.collections.ObservableSet;

public abstract class ModifiableValue<T> extends Value<T> {
	
	private ReadOnlySetWrapper<Modifier<T>> modifiers = new ReadOnlySetWrapper<>();

	public ModifiableValue(T value) {
		super(value);
	}

	public ModifiableValue(T value, Set<Modifier<T>> modifiers) {
		super(value);
		this.modifiers.addAll(modifiers);
	}

	public final ReadOnlySetProperty<Modifier<T>> modifiersProperty() {
		return this.modifiers.getReadOnlyProperty();
	}
	
	public final ObservableSet<Modifier<T>> getModifiers() {
		return this.modifiersProperty().get();
	}
	
	public abstract T getModifiedValue();
	
	public void refreshModifiers() {
		for(Iterator<Modifier<T>> itModifier = modifiers.iterator(); itModifier.hasNext();) {
			Modifier<T> modifier = itModifier.next();
			if(modifier.getExpiresOn().after(new Date())) {
				itModifier.remove();
			}
		}
	}
	
}

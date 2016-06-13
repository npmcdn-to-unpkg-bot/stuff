/* Copyright (C) 2016+ Michael Cassidy - All Rights Reserved
	This file is a part of McStuff

    McStuff is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    McStuff is distributed as sample code in the hope that it will be 
    useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with McStuff.  If not, see <http://www.gnu.org/licenses/>. 
 */
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

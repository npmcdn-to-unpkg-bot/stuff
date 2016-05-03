package mcstuff.rpg.ruleset.core;

public enum E_ModifierType {
	;
	E_ModifierSource source;
	E_ModifierTarget target;
	private E_ModifierType(E_ModifierSource source, E_ModifierTarget target) {
		this.source = source;
		this.target = target;
	}
}

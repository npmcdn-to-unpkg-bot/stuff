package mcstuff.rpg.ruleset.core;

public enum E_ModifierType {
	;
	E_ModifierSource source;
	E_ModifierTarget target;
	private E_ModifierType(E_ModifierSource source, E_ModifierTarget target) {
		this.source = source;
		this.target = target;
	}
	public E_ModifierSource getSource() {
		return source;
	}
	public E_ModifierTarget getTarget() {
		return target;
	}
}

package mcstuff.rpg.ruleset.alignment;

public enum E_Alignment {
	NONE(E_LawfulOrChaotic.NONE, E_GoodOrEvil.NONE),
	ANY(E_LawfulOrChaotic.ANY, E_GoodOrEvil.ANY),

	LAWFUL_GOOD(E_LawfulOrChaotic.LAWFUL, E_GoodOrEvil.GOOD),
	LAWFUL_NEUTRAL(E_LawfulOrChaotic.LAWFUL, E_GoodOrEvil.NEUTRAL),
	LAWFUL_EVIL(E_LawfulOrChaotic.LAWFUL, E_GoodOrEvil.EVIL),
	TRUE_NEUTRAL(E_LawfulOrChaotic.NEUTRAL, E_GoodOrEvil.NEUTRAL),
	CHAOTIC_GOOD(E_LawfulOrChaotic.CHAOTIC, E_GoodOrEvil.GOOD),
	CHAOTIC_NETRAL(E_LawfulOrChaotic.CHAOTIC, E_GoodOrEvil.NEUTRAL),
	CHAOTIC_EVIL(E_LawfulOrChaotic.CHAOTIC, E_GoodOrEvil.EVIL),;
	E_LawfulOrChaotic lawfulOrChaotic;
	E_GoodOrEvil goodOrEvil;

	private E_Alignment(final E_LawfulOrChaotic lawfulOrChaotic, final E_GoodOrEvil goodOrEvil) {
		this.lawfulOrChaotic = lawfulOrChaotic;
		this.goodOrEvil = goodOrEvil;
	}

	public E_GoodOrEvil getGoodOrEvil() {
		return goodOrEvil;
	}

	public E_LawfulOrChaotic getLawfulOrChaotic() {
		return lawfulOrChaotic;
	}
}

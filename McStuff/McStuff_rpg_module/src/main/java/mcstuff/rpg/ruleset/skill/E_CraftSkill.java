package mcstuff.rpg.ruleset.skill;

public enum E_CraftSkill implements I_SkillQualifier {
	ALCHEMY,
	ARMOR,
	BASKETS,
	BOOKS,
	BOWS,
	CALLIGRAPHY,
	CARPENTRY,
	CLOTH,
	CLOTHING,
	GLASS,
	JEWELRY,
	LEATHER,
	LOCKS,
	PAINTINGS,
	POTTERY,
	SCULPTURES,
	SHIPS,
	SHOES,
	STONEMASONRY,
	TRAPS,
	WEAPONS;
	@Override
	public E_SkillType getQualifiedSkill() {
		return E_SkillType.CRAFT;
	}

}

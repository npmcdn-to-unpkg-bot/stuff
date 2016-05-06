package mcstuff.rpg.ruleset.skill;

public enum E_PerformSkill implements I_SkillQualifier {
	ACT,
	COMEDY,
	DANCE,
	KEYBOARD_INSTRUMENTS,
	ORATORY,
	PERCUSSION_INSTRUMENTS,
	SRTING_INSTRUMENTS,
	WIND_INSTRUMENTS,
	SING,;
	@Override
	public E_SkillType getQualifiedSkill() {
		return E_SkillType.PERFORM;
	}

}

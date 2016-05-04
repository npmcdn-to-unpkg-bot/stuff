package mcstuff.rpg.ruleset.skill;

public enum E_PerformSkill implements I_SkillQualifier {
	;

	@Override
	public E_SkillType getQualifiedSkill() {
		return E_SkillType.PERFORM;
	}

}

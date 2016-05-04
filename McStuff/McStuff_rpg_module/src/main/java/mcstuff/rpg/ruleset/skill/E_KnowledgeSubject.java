package mcstuff.rpg.ruleset.skill;

public enum E_KnowledgeSubject implements I_SkillQualifier {
	ARCANA,
	DUNGEONEERING,
	ENGINEERING,
	GEOGRAPHY,
	HISTORY,
	LOCAL,
	NATURE,
	NOBILITY,
	PLANES,
	RELIGION,
	;
	@Override
	public E_SkillType getQualifiedSkill() {
		return E_SkillType.KNOWLEDGE;
	}
}

package mcstuff.rpg.ruleset.skill;

public interface I_SkillQualifier {
	E_SkillType getQualifiedSkill();

	static boolean hasQualifiers(E_SkillType skillType) {
		if (skillType == E_SkillType.CRAFT || skillType == E_SkillType.KNOWLEDGE || skillType == E_SkillType.PERFORM
				|| skillType == E_SkillType.PROFESSION)
			return true;
		else
			return false;
	}
	
	static Enum<? extends I_SkillQualifier>[] getSkillQualifiers(E_SkillType skillType) {
		if(skillType == E_SkillType.CRAFT) {
			return E_CraftSkill.values();
		} else if(skillType == E_SkillType.KNOWLEDGE) {
			return E_KnowledgeSubject.values();
		} else if(skillType == E_SkillType.PERFORM) {
			return E_PerformSkill.values();
		} else if(skillType == E_SkillType.PROFESSION) {
			return E_ProfessionSkill.values();
		}
		return null;
	}
}

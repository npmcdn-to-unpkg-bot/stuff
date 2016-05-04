package mcstuff.rpg.ruleset.core;

import java.util.HashSet;
import java.util.Set;

import mcstuff.rpg.ruleset.characterClass.E_ClassType;
import mcstuff.rpg.ruleset.skill.E_SkillType;

public interface I_ModifierQualifier {
	static Set<I_ModifierQualifier> getQualifiers(E_ModifierSourceType sourceType) {
		Set<I_ModifierQualifier> qualifiers = new HashSet<>();
		switch(sourceType) {
		case CLASS:
			fillClassQualifiers(qualifiers);
			break;
		default:
			break;
		
		}
		return qualifiers;
	}
	
	static Set<I_ModifierQualifier> getQualifiers(E_ModifierTargetType targetType) {
		Set<I_ModifierQualifier> qualifiers = new HashSet<>();
		switch(targetType) {
		case SKILL:
			fillSkillQualifiers(qualifiers);
			break;
		default:
			break;
		
		}
		return qualifiers;
	}
	
	static void fillClassQualifiers(Set<I_ModifierQualifier> qualifiers) {
		for(E_ClassType classType : E_ClassType.values()) {
			qualifiers.add(classType);
		}
	}
	
	static void fillSkillQualifiers(Set<I_ModifierQualifier> qualifiers) {
		for(E_SkillType skillType : E_SkillType.values()) {
			qualifiers.add(skillType);
		}
	}
}

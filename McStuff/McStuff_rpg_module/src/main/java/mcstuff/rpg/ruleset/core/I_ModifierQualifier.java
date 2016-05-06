package mcstuff.rpg.ruleset.core;

import java.util.HashSet;
import java.util.Set;

import mcstuff.rpg.ruleset.characterClass.E_ClassType;
import mcstuff.rpg.ruleset.skill.E_SkillType;

public interface I_ModifierQualifier {
	static void fillClassQualifiers(final Set<I_ModifierQualifier> qualifiers) {
		for (final E_ClassType classType : E_ClassType.values()) {
			qualifiers.add(classType);
		}
	}

	static void fillSkillQualifiers(final Set<I_ModifierQualifier> qualifiers) {
		for (final E_SkillType skillType : E_SkillType.values()) {
			qualifiers.add(skillType);
		}
	}

	static Set<I_ModifierQualifier> getQualifiers(final E_ModifierSourceType sourceType) {
		final Set<I_ModifierQualifier> qualifiers = new HashSet<>();
		switch (sourceType) {
			case CLASS:
				fillClassQualifiers(qualifiers);
				break;
			default:
				break;

		}
		return qualifiers;
	}

	static Set<I_ModifierQualifier> getQualifiers(final E_ModifierTargetType targetType) {
		final Set<I_ModifierQualifier> qualifiers = new HashSet<>();
		switch (targetType) {
			case SKILL:
				fillSkillQualifiers(qualifiers);
				break;
			default:
				break;

		}
		return qualifiers;
	}
}

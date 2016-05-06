package mcstuff.rpg.ruleset.skill;

public enum E_ProfessionSkill implements I_SkillQualifier {
	ARCHITECT,
	BAKER,
	BARRISTER,
	BREWER,
	BUTCHER,
	CLERK,
	COOK,
	COURTESAN,
	DRIVER,
	ENGINEER,
	FARMER,
	FISHERMAN,
	GAMBLER,
	GARDENER,
	HERBALIST,
	INNKEEPER,
	LIBRARIAN,
	MERCHANT,
	MIDWIFE,
	MILLER,
	MINER,
	PORTER,
	SAILOR,
	SCRIBE,
	SHEPHERD,
	STABLE_MASTER,
	SOLDIER,
	TANNER,
	TRAPPER,
	WOODCUTTER;
	@Override
	public E_SkillType getQualifiedSkill() {
		return E_SkillType.PROFESSION;
	}

}

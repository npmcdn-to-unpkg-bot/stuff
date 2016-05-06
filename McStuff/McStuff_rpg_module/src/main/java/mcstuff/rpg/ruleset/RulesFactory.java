package mcstuff.rpg.ruleset;

import mcstuff.rpg.ruleset.value.Value;

public class RulesFactory {
	private static class RulesFactoryHolder {
		private static final RulesFactory INSTANCE = new RulesFactory();
	}

	public static RulesFactory getInstance() {
		return RulesFactoryHolder.INSTANCE;
	}

	private RulesFactory() {
	}

	public <T> Value<T> createNewValue(final T rawValue) {
		return new Value<>(rawValue);
	}
}

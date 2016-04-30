package mcstuff.rpg.ruleset;

import mcstuff.rpg.ruleset.value.Value;

public class RulesFactory {
    private RulesFactory() { }

    private static class RulesFactoryHolder {
            private static final RulesFactory INSTANCE = new RulesFactory();
    }

    public static RulesFactory getInstance() {
            return RulesFactoryHolder.INSTANCE;
    }
    
    public Value<?> createNewValue(Object rawValue) {
    	return new Value<>(rawValue);
    }
}

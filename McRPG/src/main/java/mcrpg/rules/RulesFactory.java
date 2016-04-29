package mcrpg.rules;

public class RulesFactory {
    private RulesFactory() { }

    private static class RulesFactoryHolder {
            private static final RulesFactory INSTANCE = new RulesFactory();
    }

    public static RulesFactory getInstance() {
            return RulesFactoryHolder.INSTANCE;
    }
}

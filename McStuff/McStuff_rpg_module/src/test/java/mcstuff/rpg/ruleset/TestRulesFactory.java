package mcstuff.rpg.ruleset;
import org.junit.Assert;
import org.junit.Test;

import mcstuff.rpg.ruleset.value.Value;

public class TestRulesFactory {

	@Test
	public void testCreateNewValue() {
		RulesFactory rulesFactory = RulesFactory.getInstance();
		Value<Integer> intValue = rulesFactory.createNewValue(new Integer(120));
		Assert.assertEquals(intValue.getValue().longValue(),120L);
	}

}

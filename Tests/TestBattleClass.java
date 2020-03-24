import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestBattleClass {

    @Test
    public void verifyClassLoader() throws DiceFormatException {
        BattleClass paladin = new BattleClass();
        paladin.Load("paladin");

        assertEquals(paladin.getClassType(), "Paladin");

        BattleClass druid = new BattleClass();
        druid.Load("druid");

        assertEquals(druid.getClassType(), "Druid");
    }

    @Test
    public void verifyProficiency() throws DiceFormatException{
        BattleClass barbarian = new BattleClass();
        barbarian.Load("barbarian");

        assertTrue(barbarian.proficiencyInClass("Intimidation"));

    }
}

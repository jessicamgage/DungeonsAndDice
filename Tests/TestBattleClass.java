import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestBattleClass {

    @Test
    public void verifyClassLoader() throws DiceFormatException {
        BattleClass paladin = new BattleClass();
        paladin.Load("paladin");

        assertEquals(paladin.getClassType(), "Paladin");
        assertEquals(paladin.getHitDie(), 10);

        BattleClass druid = new BattleClass();
        druid.Load("druid");

        assertEquals(druid.getClassType(), "Druid");
        assertEquals(druid.getHitDie(), 8);
    }

    @Test
    public void verifyProficiency() throws DiceFormatException{
        BattleClass barbarian = new BattleClass();
        barbarian.Load("barbarian");

        assertTrue(barbarian.proficiencyInClass("Intimidation"));

    }
}

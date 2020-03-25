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

        assertTrue(barbarian.proficiencyInClass("STR"));
        assertTrue(barbarian.proficiencyInClass("Nature"));
        assertTrue(barbarian.proficiencyInClass("Simple weapons"));
        assertEquals(barbarian.getNumberOfProficiencies(), 2);

        BattleClass monk = new BattleClass();
        monk.Load("monk");

        assertTrue(monk.proficiencyInClass("Insight"));
        assertTrue(monk.proficiencyInClass("STR"));
        assertTrue(monk.proficiencyInClass("Simple weapons"));
        assertEquals(monk.getNumberOfProficiencies(), 2);

        BattleClass bard = new BattleClass();
        bard.Load("bard");

        assertTrue(bard.proficiencyInClass("Persuasion"));
        assertTrue(bard.proficiencyInClass("DEX"));
        assertTrue(bard.proficiencyInClass("Drum"));
        assertEquals(bard.getNumberOfProficiencies(), 3);

    }
}

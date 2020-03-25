import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCharacterClass {

    @Test
    public void verifyClassLoader() throws DiceFormatException {
        CharacterClass paladin = new CharacterClass();
        paladin.Load("paladin");

        assertEquals(paladin.getClassType(), "Paladin");
        assertEquals(paladin.getHitDie(), 10);

        CharacterClass druid = new CharacterClass();
        druid.Load("druid");

        assertEquals(druid.getClassType(), "Druid");
        assertEquals(druid.getHitDie(), 8);
    }

    @Test
    public void verifyProficiency() throws DiceFormatException{
        CharacterClass barbarian = new CharacterClass();
        barbarian.Load("barbarian");

        assertTrue(barbarian.proficiencyInClass("STR"));
        assertTrue(barbarian.proficiencyInClass("Nature"));
        assertTrue(barbarian.proficiencyInClass("Simple weapons"));
        assertEquals(barbarian.getNumberOfProficiencies(), 2);

        CharacterClass monk = new CharacterClass();
        monk.Load("monk");

        assertTrue(monk.proficiencyInClass("Insight"));
        assertTrue(monk.proficiencyInClass("STR"));
        assertTrue(monk.proficiencyInClass("Simple weapons"));
        assertEquals(monk.getNumberOfProficiencies(), 2);

        CharacterClass bard = new CharacterClass();
        bard.Load("bard");

        assertTrue(bard.proficiencyInClass("Persuasion"));
        assertTrue(bard.proficiencyInClass("DEX"));
        assertTrue(bard.proficiencyInClass("Drum"));
        assertEquals(bard.getNumberOfProficiencies(), 3);

    }
}

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestRace {

    @Test
    public void verifyRace() throws Exception{

        Race elf = new Race("elf", new Dice("3d6"), new Dice("3d6+2"), new Dice("3d6"),
                new Dice("3d6"), new Dice("3d6"), new Dice("3d    6 + 2"));

        assertEquals("human", "human");
        assertEquals("centaur", "centaur");
        assertTrue(elf == elf);
        assertEquals(elf.getName(), "elf");
    }

    @Test
    public void verifyLoadCharacter() throws Exception{
        Race dragonborn = new Race();
        dragonborn.Load("dragonborn");

        assertEquals(dragonborn.getStrScore().getText(), "3d6+2");

        Race gnome = new Race();
        gnome.Load("gnome");

        assertEquals(gnome.getIntScore().getText(), "3d6+2");
        assertEquals(gnome.getStrScore().getText(), "3d6");

        Race kobold = new Race();
        kobold.Load("kobold");

        assertEquals(kobold.getDexScore().getText(), "3d6+2");
        assertEquals(kobold.getIntScore().getText(), "3d6");
    }


    @Test
    public void verifyRacialAttributes() throws Exception{
        Race dragonborn = new Race();
        dragonborn.Load("dragonborn");

        assertEquals(dragonborn.getWalkSpeed(), 30);
        assertEquals(dragonborn.getDashSpeed(), 60);

        Race gnome = new Race();
        gnome.Load("gnome");

        assertEquals(gnome.getWalkSpeed(), 25);
        assertEquals(gnome.getDashSpeed(), 50);
    }

    @Test
    public void verifyLoadCharacterNotValid() throws Exception{
        Race dragonborn = new Race();
        dragonborn.Load("dragonborn");

        assertNotEquals(dragonborn.getStrScore().getText(), "3d6");

        Race gnome = new Race();
        gnome.Load("gnome");

        assertNotEquals(gnome.getStrScore(), "3d6+2");

        Race centaur = new Race();

        assertThrows(RaceNotFoundException.class, () -> centaur.Load("centaur"));
    }


    @Test
    public void verifyInvalidStats() throws Exception{
        Race dwarf = new Race("dwarf", new Dice("3d6"), new Dice("3d6"), new Dice("3d6"),
                new Dice("3d6"), new Dice("3d6"), new Dice("3d6"));

        Race human = new Race("dwarf", new Dice("3d6"), new Dice("3d6"), new Dice("3d6"),
                new Dice("3d6"), new Dice("3d6"), new Dice("3d6"));

        assertNotEquals("dwarf", "elf");
        assertFalse(dwarf == human);
    }

    @Test
    public void verifyInvalidRacialAttributes() throws Exception{
        Race dragonborn = new Race();
        dragonborn.Load("dragonborn");

        assertNotEquals(dragonborn.getWalkSpeed(), 25);
        assertNotEquals(dragonborn.getDashSpeed(), 50);

        Race gnome = new Race();
        gnome.Load("gnome");

        assertNotEquals(gnome.getWalkSpeed(), 30);
        assertNotEquals(gnome.getDashSpeed(), 60);
    }

    @Test
    public void verifyLanguages() throws Exception{
        Race dwarf = new Race();
        dwarf.Load("dwarf");

        assertTrue(dwarf.isDefaultLanguage("Dwarvish"));
        assertFalse(dwarf.isDefaultLanguage("Elvish"));
        assertFalse(dwarf.isLearnableLanguage("Sylvan"));

        assertEquals(dwarf.getHowManyChosenLanguages(), 0);

        Race human = new Race();
        human.Load("human");

        assertTrue(human.isDefaultLanguage("Common"));
        assertTrue(human.isLearnableLanguage("Dwarvish"));
        assertTrue(human.isLearnableLanguage("Elvish"));
        assertFalse(human.isLearnableLanguage("Gibberish"));

        assertEquals(human.getHowManyChosenLanguages(), 1);
    }
}

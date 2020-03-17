import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;

import static org.junit.jupiter.api.Assertions.*;

public class TestRace {

    @Test
    public void verifyRace() throws DiceFormatException{

        Race elf = new Race("elf", new Dice("3d6"), new Dice("3d6+2"), new Dice("3d6"),
                new Dice("3d6"), new Dice("3d6"), new Dice("3d    6 + 2"));

        assertEquals("human", "human");
        assertEquals("centaur", "centaur");
        assertTrue(elf == elf);
    }

    @Test
    public void verifyInvalidStats() throws DiceFormatException{
        Race dwarf = new Race("dwarf", new Dice("3d6"), new Dice("3d6"), new Dice("3d6"),
                new Dice("3d6"), new Dice("3d6"), new Dice("3d6"));

        Race human = new Race("dwarf", new Dice("3d6"), new Dice("3d6"), new Dice("3d6"),
                new Dice("3d6"), new Dice("3d6"), new Dice("3d6"));

        assertNotEquals("dwarf", "elf");
        assertFalse(dwarf == human);
    }

    @Test
    public void verifyLoadCharacter() throws DiceFormatException, RaceNotFoundException{
        Race dragonborn = new Race();
        dragonborn.Load("dragonborn");

        assertEquals(dragonborn.getStrScore().getText(), "3d6+2");

        Race gnome = new Race();
        gnome.Load("gnome");

        assertEquals(gnome.getIntScore().getText(), "3d6+2");
        assertEquals(gnome.getStrScore().getText(), "3d6");

        Race kobold = new Race();
        kobold.Load("kobold");

        assertEquals(kobold.getStrScore().getText(), "3d6-2");
        assertEquals(kobold.getDexScore().getText(), "3d6+2");
    }

    @Test
    public void verifyLoadCharacterNotValid() throws DiceFormatException, RaceNotFoundException{
        Race dragonborn = new Race();
        dragonborn.Load("dragonborn");

        assertNotEquals(dragonborn.getStrScore().getText(), "3d6");

        Race gnome = new Race();
        gnome.Load("gnome");

        assertNotEquals(gnome.getStrScore(), "3d6+2");

        Race centaur = new Race();

        assertThrows(RaceNotFoundException.class, () -> centaur.Load("centaur"));
    }
}

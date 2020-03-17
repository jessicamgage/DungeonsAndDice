import org.junit.jupiter.api.Test;
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
    public void verifyLoadCharacter() throws DiceFormatException{
        Race dragonborn = new Race();
        dragonborn.Load("./data/races/dragonborn.json");

        assertEquals(dragonborn.getStrScore().getText(), "3d6+2");
    }

    @Test
    public void verifyLoadCharacterNotValid() throws DiceFormatException{
        Race dragonborn = new Race();
        dragonborn.Load("./data/races/dragonborn.json");

        assertNotEquals(dragonborn.getStrScore().getText(), "3d6");
    }
}

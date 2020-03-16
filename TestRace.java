import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TestRace {
    @Test
    public void verifyRace() throws DiceFormatException{
        Race human = new Race("human", new Dice("3d6+1"));
        Race elf = new Race("elf", new Dice("3d6"), new Dice("3d6+2"), new Dice("3d6"),
                new Dice("3d6"), new Dice("3d6"), new Dice("3d    6 + 2"));

        assertEquals("human", "human");
        assertEquals("elf", "elf");
        assertTrue(human == human);
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
}

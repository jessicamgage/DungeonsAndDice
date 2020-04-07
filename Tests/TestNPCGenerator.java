import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TestNPCGenerator {
    @Test
    public void verifyRaceGenerator() throws Exception{
        NPCGenerator generator = new NPCGenerator();
        String chosen = generator.raceRandomizer();

        assertNotEquals(null, chosen);
        assertTrue(generator.properFileNameFormat(generator.getRaceFile()));
        assertFalse(generator.properNameFormat(generator.getRaceString()));

    }

    @Test
    public void verifyRaceGeneration() throws Exception{
        NPCGenerator koboldGen = new NPCGenerator();
        Race kobold = new Race();

        koboldGen.setRaceString("kobold");
        koboldGen.setRaceFile("kobold.json");

        koboldGen.Load(kobold);

        assertEquals(koboldGen.getRaceString(), "kobold");
        assertEquals(koboldGen.getRaceFile(), "kobold.json");
        assertTrue(kobold.hasRacialAbility("Darkvision"));
    }
}

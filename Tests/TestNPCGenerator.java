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
    public void testRandomRaceAbility() throws Exception{
        NPCGenerator generator = new NPCGenerator();

        Race unknownNPC = new Race();
        generator.Load(unknownNPC);

        assertTrue(unknownNPC.hasRacialAbility(unknownNPC.getRacialAbility()));
    }

    @Test
    public void verifyPredeterminedRaceGeneration() throws Exception{
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

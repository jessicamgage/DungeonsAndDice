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
        generator.GenerateRace(unknownNPC);

        assertTrue(unknownNPC.hasRacialAbility(unknownNPC.getRacialAbility()));
    }

    @Test
    public void verifyPredeterminedRaceGeneration() throws Exception{
        NPCGenerator koboldGen = new NPCGenerator();
        Race kobold = new Race();

        koboldGen.setRaceString("kobold");
        koboldGen.setRaceFile("kobold.json");

        koboldGen.GenerateRace(kobold);

        assertEquals(koboldGen.getRaceString(), "kobold");
        assertEquals(koboldGen.getRaceFile(), "kobold.json");
        assertTrue(kobold.hasRacialAbility("Darkvision"));
    }

    @Test
    public void verifyClassAndRaceGeneration() throws Exception{
        NPCGenerator raceOfClass = new NPCGenerator();

        Race raceOfNPC = new Race();
        CharacterClass classOfNPC = new CharacterClass();

        raceOfClass.GenerateRaceAndClass(raceOfNPC, classOfNPC);

        assertNotNull(raceOfClass.getClassString());
        assertNotNull(raceOfClass.getClassFile());
        assertNotNull(raceOfClass.getRaceFile());
        assertNotNull(raceOfClass.getRaceString());

        assertTrue(raceOfNPC.hasRacialAbilities(raceOfNPC.getRacialAbilities()));
        assertTrue(raceOfNPC.hasRacialAbility(raceOfNPC.getRacialAbility()));
        assertNotNull(classOfNPC.getHitDie());
    }
}

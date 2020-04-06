import org.junit.jupiter.api.Test;

import java.util.GregorianCalendar;

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
}

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TestEncounterBuilder {
    @Test
    public void verifyEncounterBuilder() throws Exception{
        EncounterBuilder goblinCave = new EncounterBuilder();
        Monster goblin = new Monster();
        goblin.Load("goblin");

        goblinCave.generateEncounter(2.0);
        assertTrue(goblinCave.getEncounterRating() > 0);
        assertTrue(goblinCave.getEnemiesInEncounter() > 1);
    }
}

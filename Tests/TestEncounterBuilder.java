import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TestEncounterBuilder {
    @Test
    public void verifyEncounterBuilder() throws Exception{
        EncounterBuilder goblinCave = new EncounterBuilder();
        Monster goblin = new Monster();
        goblin.Load("goblin");

        goblinCave.generateEncounter(4.0);
        assertTrue(goblinCave.getEncounterRating() > 0);
        assertTrue(goblinCave.getEnemiesInEncounter() > 1);
        assertTrue(goblin.getChallengeRating() == 0.25);
    }

    @Test
    public void verifyProperCR() throws Exception{
        EncounterBuilder forest = new EncounterBuilder();
        Monster owlbear = new Monster();
        owlbear.Load("owlbear");

        forest.generateEncounter(3.0);
        assertEquals(forest.getMonsterRaceString(), "owlbear");
        assertEquals(3, owlbear.getChallengeRating());
    }
}

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestMonster {
    @Test
    public void verifyManualMonsterGeneration() throws Exception{
        Monster goblin = new Monster();
        goblin.Load("goblin");

        assertNotNull(goblin.getHitPoints());
        assertNotNull(goblin.getStrScore());
        assertNotNull(goblin.getDexScore());
        assertEquals(2, goblin.getDexMod());

        assertEquals(goblin.getHitPoints(), 7 + goblin.getConMod());
        assertEquals(goblin.getArmorClass(), 15);
        assertEquals(goblin.getChallengeRating(), 0.25);
    }

    @Test
    public void verifyMonsterGenerator() throws Exception{
        Monster randomMonster = new Monster();
        randomMonster.generateMonster();

        assertNotNull(randomMonster.getRaceString());
    }
}

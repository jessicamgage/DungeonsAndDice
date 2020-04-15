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

    @Test
    public void verifyEnemyStatus() throws Exception {
        Monster petDragon = new Monster();
        petDragon.setRaceString("adult-red-dragon");
        petDragon.Load("adult-red-dragon");

        petDragon.setHitPoints(petDragon.getTotalHitPoints());
        assertSame("This monster appears to not be hurt at all.", petDragon.getMonsterState(petDragon));
        petDragon.setHitPoints(10);
        assertSame("This monster looks like it could pass out any second.", petDragon.getMonsterState(petDragon));
    }
}

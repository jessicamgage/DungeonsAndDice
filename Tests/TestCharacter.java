import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestCharacter {
    private Race human;

    @Test
    public void verifyCharacterCreation(){
        Character character = new Character();
        character.setName("bob");
        character.setRace(this.human);

        character.RollStats();

        assertTrue(character.getChaScore() > 0);
        assertTrue(character.getWisScore() > 0);
        assertTrue(character.getIntScore() > 0);
        assertTrue(character.getConScore() > 0);
        assertTrue(character.getDexScore() > 0);
        assertTrue(character.getStrScore() > 0);

    }

    @Test
    public void verifyDamageDone() throws DiceFormatException{
        Character bob = new Character();
        bob.DealDamage("piercing", "2d6");

        assertTrue(bob.getDealtDamage() > 1);

        bob.Weapon("greatsword");

        int numberOfAttacks = 50;

        for(int i = 0; i < numberOfAttacks; i++){
            int damageDone = bob.getDealtDamage();
            assertTrue(1 < damageDone && damageDone < 13);
        }
    }

    @BeforeEach
    void setUp() throws DiceFormatException {
        this.human = new Race("human", new Dice("3d6+1"), new Dice("3d6+1"),
                new Dice("3d6+1"), new Dice("3d6+1"), new Dice("3d6+1"), new Dice("3d6+1"));
    }

}
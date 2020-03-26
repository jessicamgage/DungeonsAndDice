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

        character.rollStats();

        assertTrue(character.getChaScore() > 0);
        assertTrue(character.getWisScore() > 0);
        assertTrue(character.getIntScore() > 0);
        assertTrue(character.getConScore() > 0);
        assertTrue(character.getDexScore() > 0);
        assertTrue(character.getStrScore() > 0);

    }

    @Test
    public void verifyDeathSaves() throws DiceFormatException {
        Character bob = new Character();
        bob.deathSavingThrows();

        assertTrue(bob.getDeathSavesFailed() <= 3 || bob.getDeathSavesPassed() <= 3);
        assertTrue(bob.getDeathSavesFailed() < 4);
        assertTrue(bob.getDeathSavesPassed() < 4);
    }

    @Test
    public void verifyInventoryAddition(){
        Character humanMan = new Character();
        Item javelin = new Item();

        humanMan.addToInventory(javelin);
        assertTrue(humanMan.itemHeld(javelin));
    }

    @Test
    public void verifyInventoryRemoval(){
        Character itemHolder = new Character();
        Item javelin = new Item();

        itemHolder.addToInventory(javelin);
        itemHolder.removeFromInventory(javelin);

        assertFalse(itemHolder.itemHeld(javelin));
    }

    @Test
    public void verifyBuyingAndSelling() throws NotEnoughMoneyException{
        Character shopKeeper = new Character();
        shopKeeper.setGoldHeld(10.10);

        Character playableCharacter = new Character();
        playableCharacter.setGoldHeld(60.50);

        Item greatsword = new Item();
        greatsword.Load("weapons", "greatsword");
        greatsword.setItemDirectory("weapons");

        shopKeeper.addToInventory(greatsword);

        shopKeeper.sellItem(greatsword);

        playableCharacter.buyItem(greatsword);
        playableCharacter.addToInventory(greatsword);

        assertTrue(playableCharacter.itemHeld(greatsword)); //passed
        assertFalse(shopKeeper.itemHeld(greatsword)); //passed

        assertEquals(shopKeeper.getGoldHeld(), 60.10);
        assertEquals(playableCharacter.getGoldHeld(), 10.50);
    }

    @BeforeEach
    void setUp() throws DiceFormatException {
        this.human = new Race("human", new Dice("3d6+1"), new Dice("3d6+1"),
                new Dice("3d6+1"), new Dice("3d6+1"), new Dice("3d6+1"), new Dice("3d6+1"));
    }

}
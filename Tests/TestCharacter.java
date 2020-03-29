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

        assertTrue(playableCharacter.itemHeld(greatsword));
        assertFalse(shopKeeper.itemHeld(greatsword));

        assertEquals(shopKeeper.getGoldHeld(), 60.10);
        assertEquals(playableCharacter.getGoldHeld(), 10.50);
    }

    @Test
    public void verifyCharacterStatGeneration() throws Exception{
        Character mister = new Character();
        CharacterClass fighter = new CharacterClass();
        fighter.Load("fighter");

        mister.setCharacterStats("human", "fighter", 1);

        assertEquals(mister.getArmorClass(), (10 + mister.getDexMod()));
        assertTrue(mister.getHitPoints() == (10 + mister.getConMod()));

        Character millie = new Character();
        CharacterClass monk = new CharacterClass();
        monk.Load("monk");

        millie.setCharacterStats("elf", "monk", 1);

        assertEquals(millie.getArmorClass(), (10 + millie.getDexMod() + millie.getWisMod()));
        assertTrue(millie.getHitPoints() == (8 + millie.getConMod()));

        Character squishyWizard = new Character();
        CharacterClass wizard = new CharacterClass();
        wizard.Load("wizard");

        squishyWizard.setCharacterStats("tiefling", "wizard", 1);

        squishyWizard.setDexScore(7);
        squishyWizard.setConScore(5);
        squishyWizard.setDexMod(-2);
        squishyWizard.setConMod(-3);

        squishyWizard.setArmorClass(10 + squishyWizard.getDexMod());
        squishyWizard.setHitPoints(wizard.getHitDie() + squishyWizard.getConMod());

        assertEquals(squishyWizard.getDexScore(), 7);
        assertEquals(squishyWizard.getArmorClass(), 8);
        assertEquals(squishyWizard.getHitPoints(), 3);
    }

    @BeforeEach
    void setUp() throws DiceFormatException {
        this.human = new Race("human", new Dice("3d6+1"), new Dice("3d6+1"),
                new Dice("3d6+1"), new Dice("3d6+1"), new Dice("3d6+1"), new Dice("3d6+1"));
    }

}
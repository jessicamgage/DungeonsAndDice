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
        Item ballBearings = new Item();

        humanMan.addToInventory(javelin, humanMan);
        humanMan.addToInventory(ballBearings, humanMan);
        assertTrue(humanMan.itemHeld(javelin));

        Character chicken = new Character();
        Character human = new Character();

        Item egg = new Item();
        Item knife = new Item();

        chicken.addToInventory(knife, chicken);
        human.addToInventory(egg, human);

        assertTrue(human.itemHeld(egg));
        assertTrue(chicken.itemHeld(knife));
    }

    @Test
    public void verifyInventoryRemoval(){
        Character itemHolder = new Character();
        Item javelin = new Item();

        itemHolder.addToInventory(javelin, itemHolder);
        itemHolder.removeFromInventory(javelin, itemHolder);

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

        shopKeeper.addToInventory(greatsword, shopKeeper);
        shopKeeper.sellItemAsMerchant(greatsword, shopKeeper);

        playableCharacter.buyItem(greatsword, playableCharacter);
        playableCharacter.addToInventory(greatsword, playableCharacter);

        assertTrue(playableCharacter.itemHeld(greatsword));
        assertFalse(shopKeeper.itemHeld(greatsword));

        assertEquals(shopKeeper.getGoldHeld(), 60.10);
        assertEquals(playableCharacter.getGoldHeld(), 10.50);
    }

    @Test
    public void verifyUsingGoldOutsideShopping() throws Exception{
        Character rogue = new Character();
        rogue.setGoldHeld(50);

        assertThrows(NotEnoughMoneyException.class, () -> rogue.spendMoney(rogue, 60));

        Character paladin = new Character();
        paladin.setGoldHeld(70);

        paladin.spendMoney(paladin, 50);
        assertEquals(20, paladin.getGoldHeld());

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

        //Have to manually adjust the modifiers because while the stats themselves are overridden, the original
        // modifier is still generated and set within setCharacterStats(). Test is only to ensure that AC and HP
        //are directly a result of only the character's AC-determining stats and CON + hit die, respectively.

        squishyWizard.setArmorClass(10 + squishyWizard.getDexMod());
        squishyWizard.setHitPoints(wizard.getHitDie() + squishyWizard.getConMod());

        assertEquals(squishyWizard.getDexScore(), 7);
        assertEquals(squishyWizard.getArmorClass(), 8);
        assertEquals(squishyWizard.getHitPoints(), 3);
    }

    @Test
    public void verifyLevelingUp() throws Exception{
        Character morgana = new Character();
        CharacterClass bard = new CharacterClass();
        bard.Load("bard");

        morgana.setCharacterStats("human", "bard", 4);

        assertTrue(morgana.getHitPoints() > (bard.getHitDie() + morgana.getConMod()));

        //Ensuring that leveling up increases the amount of HP a character has.

        morgana.levelUp(morgana, bard);

        assertEquals(5, morgana.getLevel());
        assertTrue(bard.getHitDie() < morgana.getHitPoints()
                && morgana.getHitPoints() <= (bard.getHitDie()*5) + (morgana.getConMod()*5));
    }

    @BeforeEach
    void setUp() throws DiceFormatException {
        this.human = new Race("human", new Dice("3d6+1"), new Dice("3d6+1"),
                new Dice("3d6+1"), new Dice("3d6+1"), new Dice("3d6+1"), new Dice("3d6+1"));
    }

}
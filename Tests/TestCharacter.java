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
        bob.deathSavingThrows(bob);

        assertTrue(bob.getDeathSavesFailed() <= 3 || bob.getDeathSavesPassed() <= 3);
        assertTrue(bob.getDeathSavesFailed() < 4);
        assertTrue(bob.getDeathSavesPassed() < 4);

        Character ragingBarbarian = new Character();
        ragingBarbarian.deathSavingThrows(ragingBarbarian);

        ragingBarbarian.setDeathSavesPassed(3);
        ragingBarbarian.setDeathSavesFailed(1);

        assertEquals(ragingBarbarian.isCharacterAlive(), true);
        assertEquals(ragingBarbarian.isCharacterConscious(), false);

        Character murderhobo = new Character();

        murderhobo.deathSavingThrows(murderhobo);

        murderhobo.setDeathSavesFailed(3);
        murderhobo.setDeathSavesPassed(1);

        assertNull(murderhobo.isCharacterConscious());
        assertFalse(murderhobo.isCharacterAlive());
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
    public void verifyNonGoldMoney() throws Exception{
        Character copperEarner = new Character();
        copperEarner.setGoldHeld(10);

        Item arrow = new Item();
        arrow.Load("ammunition", "arrow");
        arrow.setItemDirectory("Ammunition");

        copperEarner.addToInventory(arrow, copperEarner);
        copperEarner.sellItemAsMerchant(arrow, copperEarner);

        assertEquals(copperEarner.getGoldHeld(), 10.05);
    }

    @Test
    public void verifyItemUsage() throws Exception{
        Character itemHolder = new Character();
        Item javelin = new Weapon();
        javelin.setItemDirectory("weapons");
        javelin.Load("weapons", "javelin");
        itemHolder.setHeldItem(javelin);

        assertEquals(itemHolder.getHeldItem(), javelin);
        assertTrue(itemHolder.useHeldWeapon(javelin) > 0);
    }

    @Test
    public void verifyCharacterStatGeneration() throws Exception{
        Character mister = new Character();
        CharacterClass fighter = new CharacterClass();
        fighter.Load("fighter");

        mister.setCharacterStats("human", "fighter", 1);

        assertEquals(mister.getArmorClass(), (10 + mister.getDexMod()));
        assertEquals(mister.getHitPoints(), (10 + mister.getConMod()));

        Character millie = new Character();
        CharacterClass monk = new CharacterClass();
        monk.Load("monk");

        millie.setCharacterStats("elf", "monk", 1);

        assertEquals(millie.getArmorClass(), (10 + millie.getDexMod() + millie.getWisMod()));
        assertEquals(millie.getHitPoints(), (8 + millie.getConMod()));

        Character squishyWizard = new Character();
        CharacterClass wizard = new CharacterClass();
        wizard.Load("wizard");

        squishyWizard.setCharacterStats("tiefling", "wizard", 1);

        squishyWizard.setDexScore(7);
        squishyWizard.setConScore(5);

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
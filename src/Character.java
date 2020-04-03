import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;

public class Character {
    private String name;
    private Race race;
    private CharacterClass charClass;
    private AbilityScoreModifier modifier;
    private Item heldItem;

    private int strScore;
    private int dexScore;
    private int conScore;
    private int intScore;
    private int wisScore;
    private int chaScore;

    private int strMod;
    private int dexMod;
    private int conMod;
    private int intMod;
    private int wisMod;
    private int chaMod;

    private long armorClass;
    private long hitPoints;
    private long level;

    private ArrayList<Item> inventory = new ArrayList<>();
    private double goldHeld;

    private int deathSavesPassed;
    private int deathSavesFailed;
    private Boolean characterConscious = true;
    private Boolean characterAlive = true;

    public double getGoldHeld() {
        return goldHeld;
    }

    public void setGoldHeld(double goldHeld) {
        this.goldHeld = goldHeld;
    }

    public double earnMoneyForItem(Item item, Character character){
        String itemDirectory = item.getItemDirectory();
        String itemType = item.getItemType().toLowerCase();

        item.Load(itemDirectory, itemType);

        String currency = item.getCostType();
        double quantity = item.getCost() / (4 - (.125 * character.getChaMod()));

        if(currency.equalsIgnoreCase("cp")){
            setGoldHeld(goldHeld += (quantity/100));
        }else if(currency.equalsIgnoreCase("sp")){
            setGoldHeld(goldHeld += (quantity/10));
        }else if(currency.equalsIgnoreCase("gp")){
            setGoldHeld(goldHeld += quantity);
        }else if(currency.equalsIgnoreCase("pp")){
            setGoldHeld(goldHeld += (quantity*10));
        }

        return goldHeld;
    }

    public double earnMoneyAsMerchant(Item item, Character character){
        String itemDirectory = item.getItemDirectory();
        String itemType = item.getItemType().toLowerCase();

        item.Load(itemDirectory, itemType);

        String currency = item.getCostType();
        double quantity = item.getCost();

        if(currency.equalsIgnoreCase("cp")){
            setGoldHeld(goldHeld += (quantity/100));
        }else if(currency.equalsIgnoreCase("sp")){
            setGoldHeld(goldHeld += (quantity/10));
        }else if(currency.equalsIgnoreCase("gp")){
            setGoldHeld(goldHeld += quantity);
        }else if(currency.equalsIgnoreCase("pp")){
            setGoldHeld(goldHeld += (quantity*10));
        }

        return goldHeld;
    }

    public double spendMoneyOnItem(Item item, Character character){
        String itemDirectory = item.getItemDirectory();
        String itemType = item.getItemType().toLowerCase();

        item.Load(itemDirectory, itemType);

        String currency = item.getCostType();
        double quantity = item.getCost();

        if(currency.equalsIgnoreCase("cp")){
            character.setGoldHeld(goldHeld -= (quantity/100));
        }else if(currency.equalsIgnoreCase("sp")){
            character.setGoldHeld(goldHeld -= (quantity/10));
        }else if(currency.equalsIgnoreCase("gp")){
            character.setGoldHeld(goldHeld -= quantity);
        }else if(currency.equalsIgnoreCase("pp")){
            character.setGoldHeld(goldHeld -= (quantity*10));
        }

        return goldHeld;
    }

    public double spendMoney(Character character, double gold) throws Exception {

        //This method is built separately from the spendMoneyOnItem method in case a character wants to use money
        //for something outside of shopping, such as renting a room at a tavern or paying off a bounty.

        if(goldHeld > gold){
            character.setGoldHeld(goldHeld -= gold);
        }else{
            throw new NotEnoughMoneyException("Sorry, you don't have enough gold for that.");
        }

        return goldHeld;
    }

    public ArrayList getInventory() {
        return this.inventory;
    }

    public void setInventory(){
        this.inventory = inventory;
    }

    public void addToInventory(Item item, Character character) {
        this.inventory.add(item);
    }

    public void removeFromInventory(Item item, Character character){
        getInventory();

        if(inventory.contains(item)){
            inventory.remove(item);
            setInventory();
        }else{
            System.out.println("Sorry, that item cannot be removed because you do not have it in your inventory.");
        }
    }

    public void buyItem(Item item, Character character) throws NotEnoughMoneyException{
        try{

            if(item.getCost() < goldHeld){
                spendMoneyOnItem(item, character);
                addToInventory(item, character);
            }else{
                throw new NotEnoughMoneyException("Sorry, you don't have enough money for that item.");
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void sellItem(Item item, Character character){
        try{
            removeFromInventory(item, character);
            earnMoneyForItem(item, character);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void sellItemAsMerchant(Item item, Character shopkeeper){
        try{
            earnMoneyAsMerchant(item, shopkeeper);
            removeFromInventory(item, shopkeeper);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean itemHeld(Item item){
        return inventory.contains(item);
    }

    public Item getHeldItem(){
        return heldItem;
    }

    public void setHeldItem(Item heldItem){
        this.heldItem = heldItem;
    }

    public long useHeldWeapon(Item heldItem) throws Exception{
        String itemType = heldItem.getItemType();
        String itemDirectory = heldItem.getItemDirectory();
        heldItem.Load(itemDirectory, itemType);

        long dealtDamage = 0;

        try{
            Weapon heldWeapon = new Weapon();
            heldWeapon.Load(itemType);

            dealtDamage = heldWeapon.getDamageRange().roll();

        }catch(Exception e){
            e.printStackTrace();
        }

        return dealtDamage;
    }

    public void useHeldItem(Item heldItem){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public int getStrScore() {
        return strScore;
    }

    public void setStrScore(int strScore){
        this.strScore = strScore;
        strMod = modifier.setAbilityScoreModifier(strScore);
    }

    public long getDexScore() {
        return dexScore;
    }

    public void setDexScore(int dexScore){
        this.dexScore = dexScore;
        dexMod = modifier.setAbilityScoreModifier(dexScore);
    }

    public long getConScore() {
        return conScore;
    }

    public void setConScore(int conScore){
        this.conScore = conScore;
        conMod = modifier.setAbilityScoreModifier(conScore);
    }

    public long getIntScore() {
        return intScore;
    }

    public void setIntScore(int intScore){
        this.intScore = intScore;
        intMod = modifier.setAbilityScoreModifier(intScore);
    }

    public long getWisScore() {
        return wisScore;
    }

    public void setWisScore(int wisScore){
        this.wisScore = wisScore;
        wisMod = modifier.setAbilityScoreModifier(wisScore);
    }

    public long getChaScore() {
        return chaScore;
    }

    public void setChaScore(int chaScore){
        this.chaScore = chaScore;
        chaMod = modifier.setAbilityScoreModifier(chaScore);
    }

    public long getStrMod() {
        return strMod;
    }

    public void setStrMod(int strMod){
        this.strMod = strMod;
    }

    public long getDexMod() {
        return dexMod;
    }

    public void setDexMod(int dexMod){
        this.dexMod = dexMod;
    }

    public long getConMod() {
        return conMod;
    }

    public void setConMod(int conMod){
        this.conMod = conMod;
    }

    public long getIntMod() {
        return intMod;
    }

    public void setIntMod(int intMod){
        this.intMod = intMod;
    }

    public long getWisMod() {
        return wisMod;
    }

    public void setWisMod(int wisMod){
        this.wisMod = wisMod;
    }

    public long getChaMod() {
        return chaMod;
    }

    public void setChaMod(int chaMod){
        this.chaMod = chaMod;
    }

    public long getArmorClass(){
        return armorClass;
    }

    public void setArmorClass(long armorClass) {
        this.armorClass = armorClass;
    }

    public long getLevel() {
        return level;
    }

    public void setLevel(long level) {
        this.level = level;
    }

    public long rollHitPoints(String charClassType, int level) throws Exception{
        charClass.Load(charClassType);
        Integer hitDice = (int)charClass.getHitDie();

        String HPDice = ("d" + hitDice.toString());
        do{
            if(level == 1){
                this.hitPoints = hitPoints + charClass.getHitDie() + getConMod();
            }else{
                this.hitPoints = (new Dice(HPDice).roll()) + getConMod();
            }
            level--;
        }while(level >= 1);

        return hitPoints;
    }

    public void setCharacterStats(String raceType, String charClassType, int level) throws Exception {
        Race race = new Race();
        this.race = race;
        race.Load(raceType);

        rollStats();

        AbilityScoreModifier modifier = new AbilityScoreModifier();
        this.modifier = modifier;

        strMod = modifier.setAbilityScoreModifier(strScore);
        dexMod = modifier.setAbilityScoreModifier(dexScore);
        conMod = modifier.setAbilityScoreModifier(conScore);
        intMod = modifier.setAbilityScoreModifier(intScore);
        wisMod = modifier.setAbilityScoreModifier(wisScore);
        chaMod = modifier.setAbilityScoreModifier(chaScore);

        CharacterClass charClass = new CharacterClass();
        this.charClass = charClass;
        charClass.Load(charClassType);

        if(charClassType.equalsIgnoreCase("barbarian")){
            setArmorClass(10 + conMod + dexMod);
        }else if(charClassType.equalsIgnoreCase("bard")){
            setArmorClass(10 + dexMod);
        }else if(charClassType.equalsIgnoreCase("cleric")){
            setArmorClass(10 + dexMod);
        }else if(charClassType.equalsIgnoreCase("druid")){
            setArmorClass(10 + dexMod);
        }else if(charClassType.equalsIgnoreCase("fighter")){
            setArmorClass(10 + dexMod);
        }else if(charClassType.equalsIgnoreCase("monk")){
            setArmorClass(10 + dexMod + wisMod);
        }else if(charClassType.equalsIgnoreCase("paladin")){
            setArmorClass(10 + dexMod);
        }else if(charClassType.equalsIgnoreCase("ranger")){
            setArmorClass(10 + dexMod);
        }else if(charClassType.equalsIgnoreCase("rogue")){
            setArmorClass(10 + dexMod);
        }else if(charClassType.equalsIgnoreCase("sorcerer")){
            setArmorClass(10 + dexMod);
        }else if(charClassType.equalsIgnoreCase("warlock")){
            setArmorClass(10 + dexMod);
        }else if(charClassType.equalsIgnoreCase("wizard")){
            setArmorClass(10 + dexMod);
        }else{
            throw new Exception();
        }

        setLevel(level);
        setHitPoints(rollHitPoints(charClassType, level));
    }

    public void levelUp(Character character, CharacterClass charClass) throws Exception{
        level += 1;

        int newHitPoints = new Dice("d" + charClass.getHitDie()).roll();
        hitPoints += (newHitPoints + character.getConMod());

        setHitPoints(hitPoints);
    }

    public long getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(long hitPoints){
        this.hitPoints = hitPoints;

    }

    Character(){}

    public void rollStats(){
        this.strScore = this.race.getStrScore().roll();
        this.dexScore = this.race.getDexScore().roll();
        this.conScore = this.race.getConScore().roll();
        this.intScore = this.race.getIntScore().roll();
        this.wisScore = this.race.getWisScore().roll();
        this.chaScore = this.race.getChaScore().roll();
    }

    public long move(long feet){
        return feet;
    }

    public void deathSavingThrows(Character character) throws DiceFormatException{
        Dice saveThrow = new Dice("1d20");
        int saveValue = saveThrow.roll();

        while(deathSavesPassed < 3 && deathSavesFailed < 3){

            if(saveValue == 20){
                character.setDeathSavesPassed(deathSavesPassed += 2);
            }else if(saveValue >= 10){
                character.setDeathSavesPassed(deathSavesPassed += 1);
            }else if(saveValue == 1){
                character.setDeathSavesFailed(deathSavesFailed += 2);
            }else{
                character.setDeathSavesFailed(deathSavesFailed += 1);
            }

            if(character.deathSavesPassed >= 3){
                character.setCharacterConscious(false);
                character.setCharacterAlive(true);
            }else{
                character.setCharacterConscious(null);
                character.setCharacterAlive(false);
            }

        };
    }

    public Boolean isCharacterConscious() {
        return characterConscious;
    }

    public void setCharacterConscious(Boolean characterConscious) {
        this.characterConscious = characterConscious;
    }

    public Boolean isCharacterAlive() {
        return characterAlive;
    }

    public void setCharacterAlive(Boolean characterAlive) {
        this.characterAlive = characterAlive;
    }

    public int getDeathSavesPassed() {
        return deathSavesPassed;
    }

    public void setDeathSavesPassed(int deathSavesPassed) {
        this.deathSavesPassed = deathSavesPassed;
        if(deathSavesPassed >= 3){
            characterConscious = false;
            characterAlive = true;
        }
    }

    public int getDeathSavesFailed() {
        return deathSavesFailed;
    }

    public void setDeathSavesFailed(int deathSavesFailed) {
        this.deathSavesFailed = deathSavesFailed;
        if(deathSavesFailed >= 3){
            characterConscious = null;
            characterAlive = false;
        }
    }

}
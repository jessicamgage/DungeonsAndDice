import java.util.ArrayList;

public class Character {
    private String name;
    private Race race;
    private CharacterClass charClass;
    private AbilityScoreModifier modifier;

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

    private int armorClass;
    private int hitPoints;
    private int takenDamage;
    private int dealtDamage;
    private int restoredHitPoints;

    private int deathSavesPassed;
    private int deathSavesFailed;

    private ArrayList<Item> inventory;
    private double goldHeld;

    public double getGoldHeld() {
        return goldHeld;
    }

    public void setGoldHeld(double goldHeld) {
        this.goldHeld = goldHeld;
    }

    public double earnMoney(Item item, String currency, long quantity){
        String itemDirectory = item.getItemDirectory();
        String itemType = item.getItemType().toLowerCase();

        item.Load(itemDirectory, itemType);

        currency = item.getCostType();
        quantity = item.getCost();

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

    public double spendMoney(Item item, String currency, long quantity){
        String itemDirectory = item.getItemDirectory();
        String itemType = item.getItemType().toLowerCase();

        item.Load(itemDirectory, itemType);

        currency = item.getCostType();
        quantity = item.getCost();

        if(currency.equalsIgnoreCase("cp")){
            setGoldHeld(goldHeld -= (quantity/100));
        }else if(currency.equalsIgnoreCase("sp")){
            setGoldHeld(goldHeld -= (quantity/10));
        }else if(currency.equalsIgnoreCase("gp")){
            setGoldHeld(goldHeld -= quantity);
        }else if(currency.equalsIgnoreCase("pp")){
            setGoldHeld(goldHeld -= (quantity*10));
        }

        return goldHeld;
    }

    public ArrayList getInventory() {
        return this.inventory;
    }

    public void setInventory(){
        this.inventory = inventory;
    }

    public void addToInventory(Item item) {
        inventory = new ArrayList<Item>();

        this.inventory.add(item);
    }

    public void removeFromInventory(Item item){
        getInventory();

        if(inventory.contains(item)){
            inventory.remove(item);
            setInventory();
        }else{
            System.out.println("Sorry, that item cannot be removed because you do not have it in your inventory.");
        }
    }

    public void buyItem(Item item) throws NotEnoughMoneyException{
        try{
            Item itemType = new Item();

            if(itemType.getCost() < goldHeld){
                spendMoney(item, itemType.getCostType(), item.getCost());
                addToInventory(item);
            }else{
                throw new NotEnoughMoneyException("Sorry, you don't have enough money for that item.");
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void sellItem(Item item){
        try{
            Item itemType = new Item();
            removeFromInventory(item);

            earnMoney(item, itemType.getCostType(), itemType.getCost());

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public boolean itemHeld(Item item){
        return inventory.contains(item);
    }

    public int getDeathSavesPassed() {
        return deathSavesPassed;
    }

    public void setDeathSavesPassed(int deathSavesPassed) {
        this.deathSavesPassed = deathSavesPassed;
    }

    public int getDeathSavesFailed() {
        return deathSavesFailed;
    }

    public void setDeathSavesFailed(int deathSavesFailed) {
        this.deathSavesFailed = deathSavesFailed;
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

    public int getDexScore() {
        return dexScore;
    }

    public int getConScore() {
        return conScore;
    }

    public int getIntScore() {
        return intScore;
    }

    public int getWisScore() {
        return wisScore;
    }

    public int getChaScore() {
        return chaScore;
    }

    public void setChaScore(int chaScore) {
        this.chaScore = chaScore;
    }

    public int getStrMod() {
        return strMod;
    }

    public int getDexMod() {
        return dexMod;
    }

    public int getConMod() {
        return conMod;
    }

    public int getIntMod() {
        return intMod;
    }

    public int getWisMod() {
        return wisMod;
    }

    public int getChaMod() {
        return chaMod;
    }

    public int getArmorClass(){
        return armorClass;
    }

    public void setArmorClass(int armorClass) {
        this.armorClass = armorClass;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints){
        this.hitPoints = hitPoints;
    }

    public void setCharacterStats(String raceType, String charClassType, AbilityScoreModifier modifier) throws
            Exception {
        Race race = new Race();
        this.race = race;
        race.Load(raceType);

        this.strScore = race.getStrScore().roll();
        this.dexScore = race.getDexScore().roll();
        this.conScore = race.getConScore().roll();
        this.intScore = race.getIntScore().roll();
        this.wisScore = race.getWisScore().roll();
        this.chaScore = race.getChaScore().roll();

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
    }

    public int getTakenDamage(){
        return takenDamage;
    }

    public void setTakenDamage(int takenDamage){
        this.hitPoints -= takenDamage;
    }

    public int getDealtDamage(){
        return dealtDamage;
    }

    public void setDealtDamage(){
        this.hitPoints -= dealtDamage;
    }

    public int getRestoredHitPoints(){
        return restoredHitPoints;
    }

    public void setRestoredHitPoints(int restoredHitPoints){
        this.hitPoints += restoredHitPoints;
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

    public void deathSavingThrows() throws DiceFormatException{
        Dice saveThrow = new Dice("1d20");
        int saveValue = saveThrow.roll();

        do{
            if(saveValue == 20){
                this.deathSavesPassed += 2;
            }else if(saveValue >= 10){
                this.deathSavesPassed += 1;
            }else if(saveValue == 1){
                this.deathSavesFailed += 2;
            }else{
                this.deathSavesFailed += 1;
            }
        }while(deathSavesFailed <= 3 ^ deathSavesPassed <= 3);
    }

    public void walk(int walkFeet){}
    public void dash(){}
}
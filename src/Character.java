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

    private long armorClass;
    private long hitPoints;
    private long level;
    private long takenDamage;
    private long dealtDamage;
    private long restoredHitPoints;

    private int deathSavesPassed;
    private int deathSavesFailed;

    private ArrayList<Item> inventory = new ArrayList<>();
    private double goldHeld;

    public double getGoldHeld() {
        return goldHeld;
    }

    public void setGoldHeld(double goldHeld) {
        this.goldHeld = goldHeld;
    }

    public double earnMoney(Item item, String currency, long quantity, Character character){
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

    public void buyItem(Item item) throws NotEnoughMoneyException{
        try{
            Item itemType = new Item();
            Character character = new Character();

            if(itemType.getCost() < goldHeld){
                spendMoney(item, itemType.getCostType(), item.getCost());
                addToInventory(item, character);
            }else{
                throw new NotEnoughMoneyException("Sorry, you don't have enough money for that item.");
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void sellItem(Item item){
        try{
            Character character = new Character();
            Item itemType = new Item();
            removeFromInventory(item, character);

            earnMoney(item, itemType.getCostType(), itemType.getCost(), character);

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

    public void setStrScore(int strScore){
        this.strScore = strScore;
    }

    public long getDexScore() {
        return dexScore;
    }

    public void setDexScore(int dexScore){
        this.dexScore = dexScore;
    }

    public long getConScore() {
        return conScore;
    }

    public void setConScore(int conScore){
        this.conScore = conScore;
    }

    public long getIntScore() {
        return intScore;
    }

    public void setIntScore(int intScore){
        this.intScore = intScore;
    }

    public long getWisScore() {
        return wisScore;
    }

    public void setWisScore(int wisScore){
        this.wisScore = wisScore;
    }

    public long getChaScore() {
        return chaScore;
    }

    public void setChaScore(int chaScore){
        this.chaScore = chaScore;
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
        CharacterClass charClass = new CharacterClass();
        this.charClass = charClass;
        charClass.Load(charClassType);

        Integer hitDice = (int)charClass.getHitDie();

        String HPDice = ("d" + hitDice.toString());

        do{
            if(level == 1){
                this.hitPoints = charClass.getHitDie() + getConMod();
            }else{
                this.hitPoints = (new Dice(HPDice).roll()) + getConMod();
            }
            level--;
        }while(level >= 1);

        return hitPoints;
    }

    public long getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(long hitPoints){
        this.hitPoints = hitPoints;

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

        setHitPoints(rollHitPoints(charClassType, level));
    }

    public long getTakenDamage(){
        return takenDamage;
    }

    public void setTakenDamage(int takenDamage){
        this.hitPoints -= takenDamage;
    }

    public long getDealtDamage(){
        return dealtDamage;
    }

    public void setDealtDamage(){
        this.hitPoints -= dealtDamage;
    }

    public long getRestoredHitPoints(){
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
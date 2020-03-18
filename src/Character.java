public class Character {
    private String name;
    private Race race;
    private int strScore;
    private int dexScore;
    private int conScore;
    private int intScore;
    private int wisScore;
    private int chaScore;

    private int armorClass;
    private int hitPoints = 10;
    private int takenDamage;
    private int dealtDamage;
    private int restoredHitPoints;

    private String weapon;

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

    public void setStrScore(int strScore) {
        this.strScore = strScore;
    }

    public int getDexScore() {
        return dexScore;
    }

    public void setDexScore(int dexScore) {
        this.dexScore = dexScore;
    }

    public int getConScore() {
        return conScore;
    }

    public void setConScore(int conScore) {
        this.conScore = conScore;
    }

    public int getIntScore() {
        return intScore;
    }

    public void setIntScore(int intScore) {
        this.intScore = intScore;
    }

    public int getWisScore() {
        return wisScore;
    }

    public void setWisScore(int wisScore) {
        this.wisScore = wisScore;
    }

    public int getChaScore() {
        return chaScore;
    }

    public void setChaScore(int chaScore) {
        this.chaScore = chaScore;
    }

    public int getArmorClass(){
        return armorClass;
    }

    public void setArmorClass(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints){
        this.hitPoints = hitPoints;
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


    Character(){

    }

    public void RollStats(){
        this.strScore = this.race.getStrScore().Roll();
        this.dexScore = this.race.getDexScore().Roll();
        this.conScore = this.race.getConScore().Roll();
        this.intScore = this.race.getIntScore().Roll();
        this.wisScore = this.race.getWisScore().Roll();
        this.chaScore = this.race.getChaScore().Roll();
    }

    public void Attack(String weapon) throws DiceFormatException{
        //use setDealtDamage method to take away from this instance of HP, using Weapon as a parameter to determine
        //the range of damage that can be dealt

        Weapon weapon1 = new Weapon();
        weapon1.WeaponAttack("longsword");

    }

    public void DealDamage(String damageRange) throws DiceFormatException{
//        if(damageType.equalsIgnoreCase("slashing")){
//            damageType = "slashing";
//        }else if(damageType.equalsIgnoreCase("piercing")){
//            damageType = "piercing";
//        } else if(damageType.equalsIgnoreCase("bludgeoning")){
//            damageType = "bludgeoning";
//        }

        this.dealtDamage = new Dice(damageRange).Roll();

        setHitPoints(dealtDamage);

    }

}
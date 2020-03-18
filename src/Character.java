import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

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
    private int hitPoints;
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

    public void Attack(String weapon){
        //use setDealtDamage method to take away from this instance of HP, using Weapon as a parameter to determine
        //the range of damage that can be dealt
    }

    public String Weapon(String weapon){
        JSONParser jsonParser = new JSONParser();

        try{
            FileReader fileReader = new FileReader("data/items/" + weapon + ".json");

            Object obj = jsonParser.parse(fileReader);
            JSONObject json = (JSONObject) obj;

            this.weapon = (String) json.get("name");

            JSONArray damages = (JSONArray) json.get("damage");
            damages.forEach(damage -> {
                String damageType = (String) ((JSONObject) damage).get("damage_type");
                String damageRange = (String) ((JSONObject) damage).get("damage_dice");

                try {
                    this.DealDamage(damageType, damageRange);
                } catch (DiceFormatException e) {
                    e.printStackTrace();
                }
            }
            );

        }catch(Exception e){

        }

        return weapon;
    }

    public void DealDamage(String damageType, String damageRange) throws DiceFormatException{
//        if(damageType.equalsIgnoreCase("slashing")){
//
//        }else if(damageType.equalsIgnoreCase("piercing")){
//
//        } else if(damageType.equalsIgnoreCase("bludgeoning")){
//
//        }else{
//
//        }
        //add after damageRange is working

        this.dealtDamage = new Dice(damageRange).Roll();

    }

}
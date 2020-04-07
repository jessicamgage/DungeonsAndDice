import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class NPCGenerator {
    private Race race;
    private String raceFile;
    private String raceString;

    private CharacterClass characterClass;
    private String classFile;
    private String classString;

    private Character character;

    private Weapon weapon;
    private String weaponFile;
    private String weaponString;

    private long hitPoints;
    private long armorClass;

    private int level;
    private boolean NPCHostile;
    
    public String raceRandomizer() throws Exception{
        File raceDirectory = new File("data/races");
        String raceTypes[] = raceDirectory.list();

        ArrayList<String> raceFixedFont = new ArrayList<>();

        for(String race: raceTypes){
            race = race.replaceAll("(.json)", "");

            raceFixedFont.add(race);
        }

        try {
            setRaceString(raceString);
            setRaceFile(raceFile);
        }catch (Exception e){
            Object[] finalizedList = raceFixedFont.toArray();

            Random raceRandomizer = new Random();
            int raceChoice = raceRandomizer.nextInt(raceFixedFont.size());

            setRaceString(finalizedList[raceChoice].toString());
            setRaceFile(raceTypes[raceChoice]);

        }

        return raceString;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public boolean properFileNameFormat(String raceFile){
        return raceFile.contains(".json");
    }

    public String getRaceFile(){
        return raceFile;
    }

    public void setRaceFile(String raceFile){
        this.raceFile = raceFile;
    }

    public boolean properNameFormat(String race){
        return race.contains(".json");
    }

    public String getRaceString() {
        return raceString;
    }

    public void setRaceString(String raceString) {
        if(raceString == null){
            throw new NullPointerException();
        }

        this.raceString = raceString;
    }

    public void generateRace(Race race) throws Exception{
        raceRandomizer();
        race.Load(raceString);
    }

    public String classRandomizer() throws Exception{
        File classDirectory = new File("data/classes");
        String classTypes[] = classDirectory.list();

        ArrayList<String> classFixedFont = new ArrayList<>();

        for(String classType: classTypes){
            classType = classType.replaceAll("(.json)", "");

            classFixedFont.add(classType);
        }

        try {
            setClassString(classString);
            setClassFile(classFile);
        }catch (Exception e){
            Object[] finalizedList = classFixedFont.toArray();

            Random classRandomizer = new Random();
            int classChoice = classRandomizer.nextInt(classFixedFont.size());

            setClassString(finalizedList[classChoice].toString());
            setClassFile(classTypes[classChoice]);
        }

        return classString;
    }

    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(CharacterClass characterClass) {
        this.characterClass = characterClass;
    }

    public String getClassFile() {
        return classFile;
    }

    public void setClassFile(String classFile) {
        this.classFile = classFile;
    }

    public String getClassString() {
        return classString;
    }

    public void setClassString(String classString) {
        if(classString == null){
            throw new NullPointerException();
        }
        this.classString = classString;
    }

    public void generateRaceAndClass(Race race, CharacterClass characterClass) throws Exception{
        raceRandomizer();
        race.Load(raceString);

        classRandomizer();
        characterClass.Load(classString);
    }

    //Character is not generated because all of its methods are dependent on instances of class. Therefore a Character
    //randomizer is unnecessary and all of its needed methods are within the NPCGenerator.

    public String weaponRandomizer() throws Exception{
        File weaponDirectory = new File("data/items/weapons");
        String weaponTypes[] = weaponDirectory.list();

        ArrayList<String> weaponFixedFont = new ArrayList<>();

        for(String weapon: weaponTypes){
            weapon = weapon.replaceAll("(.json)", "");

            weaponFixedFont.add(weapon);
        }

        try {
            setWeaponString(weaponString);
            setWeaponFile(weaponFile);
        }catch (Exception e){
            Object[] finalizedList = weaponFixedFont.toArray();

            Random weaponRandomizer = new Random();
            int weaponChoice = weaponRandomizer.nextInt(weaponFixedFont.size());

            setWeaponString(finalizedList[weaponChoice].toString());
            setWeaponFile(weaponTypes[weaponChoice]);

        }

        return weaponString;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public String getWeaponFile() {
        return weaponFile;
    }

    public void setWeaponFile(String weaponFile) {
        this.weaponFile = weaponFile;
    }

    public String getWeaponString() {
        return weaponString;
    }

    public void setWeaponString(String weaponString) {
        if(weaponString == null){
            throw new NullPointerException();
        }else{
            this.weaponString = weaponString;
        }
    }

    public void generateWeapon(Weapon weapon) throws Exception{
        weaponRandomizer();
        weapon.Load(weaponString);
    }

    public int randomizeLevel(){
        NPCHostile = false;

        Random levelRand = new Random();
        level = levelRand.nextInt(21);

        return level;
    }

    public int randomizeLevel(int PCLevel){
        NPCHostile = true;

        Random levelRand = new Random();
        level = levelRand.nextInt(PCLevel);

        //build separate method to generate # and level of NPC's in EncounterBuilder
        //build EncounterBuilder to generate a total number of NPC's whose challenge rating, which can be pulled
        //from an enemy repository similar to race/class

        return level;
    }

    public void generateNPC() throws Exception{
        Race randomizedRace = new Race();
        randomizedRace.Load(raceRandomizer());

        CharacterClass randomizedClass = new CharacterClass();
        randomizedClass.Load(classRandomizer());

        Weapon randomizedWeapon = new Weapon();
        randomizedWeapon.Load(weaponRandomizer());

        Character randomizedCharacter = new Character();
        randomizedCharacter.setCharacterStats(getRaceString(),getClassFile(), randomizeLevel());
        randomizedCharacter.addToInventory(getWeapon(), randomizedCharacter);
    }

    public long getHitPoints(){
        return hitPoints;
    }

    public void setHitPoints(long hitPoints){
        this.hitPoints = hitPoints;
    }

    public long getArmorClass(){
        return armorClass;
    }

    public void setArmorClass(long armorClass){
        this.armorClass = armorClass;
    }
}

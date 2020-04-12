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
        race.Load(raceRandomizer());
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
        race.Load(raceRandomizer());
        characterClass.Load(classRandomizer());
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

    public void generateNPC() throws Exception{
        Race randomizedRace = new Race();
        randomizedRace.Load(raceRandomizer());

        CharacterClass randomizedClass = new CharacterClass();
        randomizedClass.Load(classRandomizer());

        Weapon randomizedWeapon = new Weapon();
        randomizedWeapon.Load(weaponRandomizer());

        Character randomizedCharacter = new Character();
        randomizedCharacter.setCharacterStats(getRaceString(), getClassString(), randomizeLevel());
        setHitPoints(randomizedCharacter.getHitPoints());
        setArmorClass(randomizedCharacter.getArmorClass());

        setStrScore((int)randomizedCharacter.getStrScore());
        setDexScore((int)randomizedCharacter.getDexScore());
        setConScore((int)randomizedCharacter.getConScore());
        setIntScore((int)randomizedCharacter.getIntScore());
        setWisScore((int)randomizedCharacter.getWisScore());
        setChaScore((int)randomizedCharacter.getChaScore());

        AbilityScoreModifier modifier = new AbilityScoreModifier();

        strMod = modifier.setAbilityScoreModifier(strScore);
        dexMod = modifier.setAbilityScoreModifier(dexScore);
        conMod = modifier.setAbilityScoreModifier(conScore);
        intMod = modifier.setAbilityScoreModifier(intScore);
        wisMod = modifier.setAbilityScoreModifier(wisScore);
        chaMod = modifier.setAbilityScoreModifier(chaScore);

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

    public int getStrMod(){
        return strMod;
    }

    public void setStrMod(int strMod) {
        this.strMod = strMod;
    }

    public int getDexMod() {
        return dexMod;
    }

    public void setDexMod(int dexMod) {
        this.dexMod = dexMod;
    }

    public int getConMod() {
        return conMod;
    }

    public void setConMod(int conMod) {
        this.conMod = conMod;
    }

    public int getIntMod() {
        return intMod;
    }

    public void setIntMod(int intMod) {
        this.intMod = intMod;
    }

    public int getWisMod() {
        return wisMod;
    }

    public void setWisMod(int wisMod) {
        this.wisMod = wisMod;
    }

    public int getChaMod() {
        return chaMod;
    }

    public void setChaMod(int chaMod) {
        this.chaMod = chaMod;
    }
}

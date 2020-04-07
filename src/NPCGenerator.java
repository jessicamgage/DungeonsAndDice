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
    private String charFile;
    private String charString;

    private Weapon weapon;
    private String weaponFile;
    private String weaponString;

    private int level;

//create race, charclass, weapon, level randomizer based of pc level for npcs that are to be fought
    //create overloaded method without an instance of a level generator for non fought npcs

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

    public void GenerateRace(Race race) throws Exception{
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

    public void GenerateRaceAndClass(Race race, CharacterClass characterClass) throws Exception{
        raceRandomizer();
        race.Load(raceString);

        classRandomizer();
        characterClass.Load(classString);
    }

    //GenerateNPC(){
    // Race randomizedRace = new Race();
    // randomizedRace.Load(raceRandomizer());

    // CharacterClass randomizedClass = new CharacterClass();
    // randomizedClass.Load(classRandomizer());

    // Character randomizedChar = new Character();
    // randomizedChar.Load(charRandomizer());

    // Weapon randomizedWeapon = new Weapon();
    // randomizedWeapon.Load(weaponRandomizer());

    //
    // }

}

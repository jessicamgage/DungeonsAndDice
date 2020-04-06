import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class NPCGenerator {
    private Race race;
    private CharacterClass characterClass;
    private Character character;
    private Weapon weapon;
    private int level;

    private String raceFile;
    private String raceString;

//create race, charclass, weapon, level randomizer based of pc level for npcs that are to be fought
    //create overloaded method without an instance of a level generator for non fought npcs

    public String raceRandomizer() throws Exception{
        File raceDirectory = new File("data/races");
        String raceTypes[] = raceDirectory.list();

        ArrayList<String> raceFixedFont = new ArrayList<>();

        for(String race: raceTypes){
            setRaceFile(race);
            race = race.replaceAll("(.json)", "");

            raceFixedFont.add(race);
        }

        Random raceRandomizer = new Random();
        int raceChoice = raceRandomizer.nextInt(raceFixedFont.size());

        Object[] finalizedList = raceFixedFont.toArray();

        setRaceString(finalizedList[raceChoice].toString());
        setRaceFile(raceTypes[raceChoice]);

        return (String) finalizedList[raceChoice];
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
        this.raceString = raceString;
    }

    public void Load(Race race, CharacterClass characterClass, Character character, Weapon weapon) throws Exception{
        character.setRace(race);

    }
}

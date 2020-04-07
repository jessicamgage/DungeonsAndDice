import java.io.File;
import java.io.FileNotFoundException;
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

    public void generate(){
        //creates instance of an NPC. use Load method to pull all parameters, use getters to pull information about
        //the npc to print out (i.e. "A " + npc.getRace() " approaches you. They appear to be a " + npc.getClass()"
    }

    public void Load(Race race) throws Exception{
        raceRandomizer();
        race.Load(raceString);
    }

    public void Load(Race race, CharacterClass characterClass, Character character, Weapon weapon) throws Exception{
        character.setRace(race);
        race.Load(getRaceFile());

    }
}

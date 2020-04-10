import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class EncounterBuilder {
    private Monster monsterSpecies;
    private String monsterRaceString;
    private String monsterRaceFile;

    private ArrayList<Monster> encounter = new ArrayList<>();
    private double encounterRating;
    private int enemiesInEncounter;

    public Monster getMonsterSpecies() {
        return monsterSpecies;
    }

    public void setMonsterSpecies(Monster monsterSpecies) {
        this.monsterSpecies = monsterSpecies;
    }

    public double getEncounterRating(){
        return encounterRating;
    }

    public void setEncounterRating(double encounterRating){
        this.encounterRating = encounterRating;
    }

    public ArrayList getEncounter(){
        return encounter;
    }

    public void setEncounter(ArrayList encounter){
        this.encounter = encounter;
    }

    public String getMonsterRaceString() {
        return monsterRaceString;
    }

    public void setMonsterRaceString(String monsterRaceString) {
        this.monsterRaceString = monsterRaceString;
    }

    public String getMonsterRaceFile() {
        return monsterRaceFile;
    }

    public void setMonsterRaceFile(String monsterRaceFile) {
        this.monsterRaceFile = monsterRaceFile;
    }

    public void generateEncounter(double PCLevel) throws Exception{
        File monsterDirectory = new File("data/monsters");
        String monsterTypes[] = monsterDirectory.list();

        ArrayList<String> monsterFixedFont = new ArrayList<>();

        for(String monster: monsterTypes){
            monster = monster.replaceAll("(.json)", "");

            monsterFixedFont.add(monster);
        }

        try{
            setMonsterRaceString(monsterRaceString);
            setMonsterRaceFile(monsterRaceFile);
        }catch (Exception e){
            Object[] finalizedList = monsterFixedFont.toArray();

            Random monsterRandomizer = new Random();
            int monsterChoice = monsterRandomizer.nextInt(monsterFixedFont.size());

            setMonsterRaceString(finalizedList[monsterChoice].toString());
            setMonsterRaceFile(monsterTypes[monsterChoice]);

        }

        Monster monsterType = new Monster();

        while(encounterRating < PCLevel){
            while(monsterType.getChallengeRating() >= PCLevel* 0.5){
                String monster = monsterType.monsterRandomizer();
                monsterType.setRaceString(monster);
                monsterType.setRaceFile(monster + ".json");

            }
            encounter.add(monsterType);
            encounterRating += monsterType.getChallengeRating();
            enemiesInEncounter += 1;
            encounterRating += 0.5; //This is designed so that every time an enemy is added to an encounter, it gets harder.
            //This is to prevent level-one players from being overrun by four goblins, which is significantly harder
            //than their 0.25 challenge rating would suggest.
        }
    }

    public int getEnemiesInEncounter() {
        return enemiesInEncounter;
    }

    public void setEnemiesInEncounter(int enemiesInEncounter) {
        this.enemiesInEncounter = enemiesInEncounter;
    }
}


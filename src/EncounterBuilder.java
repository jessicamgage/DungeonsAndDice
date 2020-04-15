import java.util.ArrayList;
import java.util.HashMap;

public class EncounterBuilder {
    private Monster monsterSpecies;
    private String monsterRaceString;
    private String monsterRaceFile;

    private ArrayList<Monster> encounter = new ArrayList<>();
    private double encounterRating;
    private int enemiesInEncounter;

    private HashMap <String, String> playerEncounterInfo = new HashMap<>();

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

    public HashMap<String, String> getPlayerEncounterInfo() {
        return playerEncounterInfo;
    }

    public void setPlayerEncounterInfo(HashMap<String, String> playerEncounterInfo) {
        this.playerEncounterInfo = playerEncounterInfo;
    }

    public String getMonsterRaceString() {
        return monsterRaceString;
    }

    public void setMonsterRaceString(String monsterRaceString) {
        if(monsterRaceString == null){
            throw new NullPointerException();
        }
        this.monsterRaceString = monsterRaceString;
    }

    public String getMonsterRaceFile() {
        return monsterRaceFile;
    }

    public void setMonsterRaceFile(String monsterRaceFile) {
        if(monsterRaceString == null){
            throw new NullPointerException();
        }
        this.monsterRaceFile = monsterRaceFile;
    }

    public void generateEncounter(double PCLevel) throws Exception{
        while(encounterRating < PCLevel * 0.75){
            Monster monsterType = new Monster();
            monsterType.generateMonster();

            setMonsterRaceString(monsterType.getRaceString());
            monsterType.Load(getMonsterRaceString());
            setMonsterRaceFile(monsterType.getRaceFile());

            if(monsterType.getChallengeRating() < PCLevel * 2 //Ensuring an encounter isn't too hard
            && monsterType.getChallengeRating() >= PCLevel * 0.25){ //Ensuring that an encounter isn't made of a
                //swarm of low-level enemies

                encounter.add(monsterType);
                encounterRating += monsterType.getChallengeRating();
                enemiesInEncounter += 1;
                encounterRating += 0.75;

                //This is designed so that every time an enemy is added to an encounter, it gets harder.
                //This is to prevent level-one players from being overrun by four goblins, which is significantly harder
                //than their 0.25 challenge rating would suggest.

                playerEncounterInfo.put(monsterRaceString, monsterType.getMonsterState(monsterType));
                if(!monsterType.isCharacterAlive()){
                    playerEncounterInfo.remove(this.monsterRaceString);
                }
            }
        }
    }

    public int getEnemiesInEncounter() {
        return enemiesInEncounter;
    }

    public void setEnemiesInEncounter(int enemiesInEncounter) {
        this.enemiesInEncounter = enemiesInEncounter;
    }
}


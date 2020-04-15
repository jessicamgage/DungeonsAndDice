import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class Monster extends Character{
    private String raceString;
    private String raceFile;
    private ArrayList languages;
    private String normalAlignment; //generate method so that 70% of monsters adhere to their normal alignment

    private AbilityScoreModifier modifier;

    private boolean NPCHostile;
    private int level;

    private long strScore;
    private long dexScore;
    private long conScore;
    private long intScore;
    private long wisScore;
    private long chaScore;

    private long strMod;
    private long dexMod;
    private long conMod;
    private long intMod;
    private long wisMod;
    private long chaMod;

    private long walkSpeed;

    private ArrayList specialAbilities;

    private ArrayList senses;
    private ArrayList damageVulnerabilities;
    private ArrayList damageResistances;
    private ArrayList damageImmunities;
    private double challengeRating;

    @Override
    public long getStrScore() {
        return strScore;
    }

    public void setStrScore(long strScore) {
        this.strScore = strScore;
    }

    @Override
    public long getDexScore() {
        return dexScore;
    }

    public void setDexScore(long dexScore) {
        this.dexScore = dexScore;
    }

    @Override
    public long getConScore() {
        return conScore;
    }

    public void setConScore(long conScore) {
        this.conScore = conScore;
    }

    @Override
    public long getIntScore() {
        return intScore;
    }

    public void setIntScore(long intScore) {
        this.intScore = intScore;
    }

    @Override
    public long getWisScore() {
        return wisScore;
    }

    public void setWisScore(long wisScore) {
        this.wisScore = wisScore;
    }

    @Override
    public long getChaScore() {
        return chaScore;
    }

    public void setChaScore(long chaScore) {
        this.chaScore = chaScore;
    }


    @Override
    public long getStrMod() {
        return strMod;
    }

    public void setStrMod(long strMod) {
        this.strMod = strMod;
    }

    @Override
    public long getDexMod() {
        return dexMod;
    }

    public void setDexMod(long dexMod) {
        this.dexMod = dexMod;
    }

    @Override
    public long getConMod() {
        return conMod;
    }

    public void setConMod(long conMod) {
        this.conMod = conMod;
    }

    @Override
    public long getIntMod() {
        return intMod;
    }

    public void setIntMod(long intMod) {
        this.intMod = intMod;
    }

    @Override
    public long getWisMod() {
        return wisMod;
    }

    public void setWisMod(long wisMod) {
        this.wisMod = wisMod;
    }

    @Override
    public long getChaMod() {
        return chaMod;
    }

    public void setChaMod(long chaMod) {
        this.chaMod = chaMod;
    }

    public double getChallengeRating() {
        return challengeRating;
    }

    public void setChallengeRating(double challengeRating) {
        this.challengeRating = challengeRating;
    }

    public void Load(String race) throws Exception{
        JSONParser parser = new JSONParser();

        try {
            FileReader fileReader = new FileReader("data/monsters/" + race + ".json");
            Object obj = parser.parse(fileReader);
            JSONObject jsonObject = (JSONObject) obj;

            AbilityScoreModifier mod = new AbilityScoreModifier();

            long averageStr = (long)jsonObject.get("strength");
            long averageDex = (long)jsonObject.get("dexterity");
            long averageCon = (long)jsonObject.get("constitution");
            long averageInt = (long)jsonObject.get("intelligence");
            long averageWis = (long)jsonObject.get("wisdom");
            long averageCha = (long)jsonObject.get("charisma");

            long strMod = mod.setAbilityScoreModifier((int)averageStr);
            setStrMod(strMod);

            long dexMod = mod.setAbilityScoreModifier((int)averageDex);
            setDexMod(dexMod);

            long conMod = mod.setAbilityScoreModifier((int)averageCon);
            setConMod(conMod);

            long intMod = mod.setAbilityScoreModifier((int)averageInt);
            setIntMod(intMod);

            long wisMod = mod.setAbilityScoreModifier((int)averageWis);
            setWisMod(wisMod);

            long chaMod = mod.setAbilityScoreModifier((int)averageCha);
            setChaMod(chaMod);

            setStrScore(new Dice("3d6").roll() + (int)getStrMod());
            setDexScore(new Dice("3d6").roll() + (int)getDexMod());
            setConScore(new Dice("3d6").roll() + (int)getConMod());
            setIntScore(new Dice("3d6").roll() + (int)getIntMod());
            setWisScore(new Dice("3d6").roll() + (int)getWisMod());
            setChaScore(new Dice("3d6").roll() + (int)getChaMod());

            long totalHitPoints = (long) jsonObject.get("hit_points");
            long hitPoints = (long) jsonObject.get("hit_points");
            long armorClass = (long) jsonObject.get("armor_class");
            String CRValue = (jsonObject.get("challenge_rating")).toString();
            challengeRating = Double.parseDouble(CRValue);

            {
                setTotalHitPoints(totalHitPoints + conMod);
            }

            setHitPoints(hitPoints + conMod);
            setArmorClass(armorClass);
            setChallengeRating(challengeRating);

        }catch(Exception e){
            e.printStackTrace();
        }
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

    public String getRaceFile() {
        return raceFile;
    }

    public void setRaceFile(String raceFile) {
        if(raceFile == null){
            throw new NullPointerException();
        }
        this.raceFile = raceFile;
    }

    public String monsterRandomizer() throws Exception{
        File monsterDirectory = new File("data/monsters");
        String monsterTypes[] = monsterDirectory.list();

        ArrayList<String> monsterFixedFont = new ArrayList<>();

        for(String monster: monsterTypes){
            monster = monster.replaceAll("(.json)", "");

            monsterFixedFont.add(monster);
        }

        try {
            setRaceString(raceString);
            setRaceFile(raceFile);
        }catch (Exception e){
            Object[] finalizedList = monsterFixedFont.toArray();

            Random monsterRandomizer = new Random();
            int monsterChoice = monsterRandomizer.nextInt(monsterFixedFont.size());

            setRaceString(finalizedList[monsterChoice].toString());
            setRaceFile(monsterTypes[monsterChoice]);

        }
        return raceString;
    }

    public void generateMonster() throws Exception{
        Monster monster = new Monster();
        monster.Load(monsterRandomizer());
    }

    public String getMonsterState(Monster monster){
        int monsterHealthPortion = (int) monster.getHitPoints();
        int monsterHealthTotal = (int) monster.getTotalHitPoints();

        double monsterHealthSwitch = (monsterHealthPortion/monsterHealthTotal);

        String status;

        if(monsterHealthSwitch == 1){
            status = "This monster appears to not be hurt at all.";
        }else if(90 < monsterHealthSwitch && monsterHealthSwitch < 100){
            status = "This monster appears to have taken only a little damage.";
        }else if(75 < monsterHealthSwitch && monsterHealthSwitch < 90){
            status = "This monster has taken some damage, but is still strong.";
        }else if(50 < monsterHealthSwitch && monsterHealthSwitch < 75){
            status = "This monster is starting to look a little tired.";
        }else if (35 < monsterHealthSwitch && monsterHealthSwitch < 50){
            status = "This monster is beginning to look hurt.";
        }else if(15 < monsterHealthSwitch && monsterHealthSwitch < 35){
            status = "This monster looks exhausted and wounded.";
        }else{
            status = "This monster looks like it could pass out any second.";
        }

        return status;
    }
}

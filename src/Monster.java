import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;

public class Monster extends Character{
    private String race;
    private ArrayList languages;
    private String normalAlignment; //generate method so that 70% of monsters adhere to their normal alignment

    private AbilityScoreModifier modifier;

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
    private long challengeRating;

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

            long hitPoints = (long) jsonObject.get("hit_points");
            long armorClass = (long) jsonObject.get("armor_class");

            setHitPoints(hitPoints + conMod);
            setArmorClass(armorClass);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

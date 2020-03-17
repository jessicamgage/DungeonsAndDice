import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Race {
    private String name;
    private Dice strScore;
    private Dice dexScore;
    private Dice conScore;
    private Dice intScore;
    private Dice wisScore;
    private Dice chaScore;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dice getStrScore() {
        return strScore;
    }

    public void setStrScore(Dice strScore) {
        this.strScore = strScore;
    }

    public Dice getDexScore() {
        return dexScore;
    }

    public void setDexScore(Dice dexScore) {
        this.dexScore = dexScore;
    }

    public Dice getConScore() {
        return conScore;
    }

    public void setConScore(Dice conScore) {
        this.conScore = conScore;
    }

    public Dice getIntScore() {
        return intScore;
    }

    public void setIntScore(Dice intScore) {
        this.intScore = intScore;
    }

    public Dice getWisScore() {
        return wisScore;
    }

    public void setWisScore(Dice wisScore) {
        this.wisScore = wisScore;
    }

    public Dice getChaScore() {
        return chaScore;
    }

    public void setChaScore(Dice chaScore) {
        this.chaScore = chaScore;
    }

    Race(String name, Dice strScore, Dice dexScore, Dice conScore, Dice intScore, Dice wisScore, Dice chaScore)
            throws DiceFormatException {
        this.name = name;
        this.strScore = strScore;
        this.dexScore = dexScore;
        this.conScore = conScore;
        this.intScore = intScore;
        this.wisScore = wisScore;
        this.chaScore = chaScore;
    }

    Race(){

    }

    private void UpdateAbilityModifier(String ability, int modifier){
        if(ability.equalsIgnoreCase("STR")){
            this.strScore.setModifier(modifier);
        }else if(ability.equalsIgnoreCase("DEX")){
            this.dexScore.setModifier(modifier);
        }else if(ability.equalsIgnoreCase("CON")){
            this.conScore.setModifier(modifier);
        }else if(ability.equalsIgnoreCase("INT")){
            this.intScore.setModifier(modifier);
        }else if(ability.equalsIgnoreCase("WIS")){
            this.wisScore.setModifier(modifier);
        }else if(ability.equalsIgnoreCase("CHA")){
            this.chaScore.setModifier(modifier);
        }
    }

    public void Load(String name) throws DiceFormatException, RaceNotFoundException{
        JSONParser jsonParser = new JSONParser();

        this.strScore = new Dice("3d6");
        this.dexScore = new Dice("3d6");
        this.conScore = new Dice("3d6");
        this.intScore = new Dice("3d6");
        this.wisScore = new Dice("3d6");
        this.chaScore = new Dice("3d6");

        try{
            FileReader fileReader = new FileReader("data/races/" + name + ".json");

            Object obj = jsonParser.parse(fileReader);
            JSONObject json = (JSONObject) obj;

            this.name = (String) json.get("name");

            JSONArray bonuses = (JSONArray) json.get("ability_bonuses");
            bonuses.forEach(bonus -> {
                String ability = (String) ((JSONObject) bonus).get("name");
                long modifier = (long) ((JSONObject) bonus).get("bonus");

                this.UpdateAbilityModifier(ability, (int)modifier);
            });

        }catch(FileNotFoundException e){
            e.printStackTrace();
            throw new RaceNotFoundException("Sorry, your selected race was not found. Please try again.");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
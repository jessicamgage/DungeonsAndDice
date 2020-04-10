import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class Race {
    private String name;
    private Dice strScore;
    private Dice dexScore;
    private Dice conScore;
    private Dice intScore;
    private Dice wisScore;
    private Dice chaScore;

    private long walkSpeed;
    private ArrayList<String> defaultLanguages = new ArrayList<>();
    private ArrayList<String> possibleLanguages = new ArrayList<>();
    private Boolean languageKnownByDefault;
    private Boolean learnableLanguage;
    private long howManyChosenLanguages;
    private String knownExoticLanguage;

    private String racialAbility;
    private ArrayList<String> racialAbilities = new ArrayList<>();

    public long getWalkSpeed() {
        return walkSpeed;
    }

    public void setWalkSpeed(long walkSpeed) {
        this.walkSpeed = walkSpeed;
    }

    public long getDashSpeed(){
        return walkSpeed*2;
    }

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

    public Boolean isDefaultLanguage(String language){
        languageKnownByDefault = defaultLanguages.contains(language);

        return languageKnownByDefault;
    }

    public ArrayList getDefaultLanguages(ArrayList defaultLanguages){
        return defaultLanguages;
    }

    public void setDefaultLanguages(){
        this.defaultLanguages = defaultLanguages;
    }

    public Boolean isLearnableLanguage(String language){
        learnableLanguage = possibleLanguages.contains(language);

        return learnableLanguage;
    }

    public ArrayList<String> getPossibleLanguages() {
        return possibleLanguages;
    }

    public void setPossibleLanguages(ArrayList<String> possibleLanguages) {
        this.possibleLanguages = possibleLanguages;
    }

    public String getKnownExoticLanguage(){
        return knownExoticLanguage;
    }

    public void setKnownExoticLanguage(String exoticLanguage){
        this.defaultLanguages.add(exoticLanguage);
    }

    public long getHowManyChosenLanguages() {
        return howManyChosenLanguages;
    }

    public void setHowManyChosenLanguages(long howManyChosenLanguages) {
        this.howManyChosenLanguages = howManyChosenLanguages;
    }

    public Boolean hasRacialAbilities(ArrayList abilities){
        return racialAbilities.equals(racialAbilities);
    }

    public ArrayList getRacialAbilities(){
        return racialAbilities;
    }

    public String getRacialAbility() {
        return racialAbility;
    }

    public Boolean hasRacialAbility(String ability){
        return racialAbilities.contains(ability);
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

    Race(){}

    private void updateAbilityModifier(String ability, int modifier){
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
            this.walkSpeed = (long) json.get("speed");

            JSONArray languages = (JSONArray) json.get("languages");
            languages.forEach(language ->{
                String knownLanguage = (String) ((JSONObject) language).get("name");
                defaultLanguages.add(knownLanguage);
            });

            try{
                JSONObject languageOptions = (JSONObject) json.get("language_options");

                long howManyLanguages = (long) languageOptions.get("choose");
                setHowManyChosenLanguages(howManyLanguages);

                JSONArray chooseFrom = (JSONArray) languageOptions.get("from");

                chooseFrom.forEach(possibleLanguage -> {
                    String language = (String) ((JSONObject) possibleLanguage).get("name");
                    possibleLanguages.add(language);

                    Random languageRandomizer = new Random();
                    int chosenLanguageIndex = languageRandomizer.nextInt(possibleLanguages.size());

                    defaultLanguages.add(possibleLanguages.get(chosenLanguageIndex));
                });

            }catch (NullPointerException e){

            }

            JSONArray bonuses = (JSONArray) json.get("ability_bonuses");
            bonuses.forEach(bonus -> {
                String ability = (String) ((JSONObject) bonus).get("name");
                long modifier = (long) ((JSONObject) bonus).get("bonus");

                this.updateAbilityModifier(ability, (int)modifier);
            });

            try{
                JSONArray traits = (JSONArray) json.get("traits");
                traits.forEach(trait -> {
                    racialAbility = (String) ((JSONObject) trait).get("name");
                    racialAbilities.add(racialAbility);
                });
            }catch (NullPointerException e){
                System.out.println("This character does not have racial traits.");
            }

        }catch(FileNotFoundException e){
            e.printStackTrace();
            throw new RaceNotFoundException("Sorry, your selected race was not found. Please try again.");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
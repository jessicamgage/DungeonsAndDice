import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;

public class CharacterClass {
    private Character character;
    private String classType = "";
    private long hitDie;
    private String skillProficiency;
    private int attackBonus;

    private ArrayList<String> proficiencyList;
    private long numberOfProficiencies;

    public long getNumberOfProficiencies() {
        return numberOfProficiencies;
    }

    public void setNumberOfProficiencies(long numberOfProficiencies) {
        this.numberOfProficiencies = numberOfProficiencies;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getSkillProficiency() {
        return skillProficiency;
    }

    public String setSkillProficiency(String proficiency) {
        String trimmedText = proficiency.replaceAll("Skill: ", "");

        return trimmedText;
    }

    public boolean proficiencyInClass(String proficiencyInstance){
        return proficiencyList.contains(proficiencyInstance);
    }

    CharacterClass(){}

    CharacterClass(String classType){
        this.setClassType(classType);
    }

    public void Load(String classType) throws DiceFormatException{
        JSONParser jsonParser = new JSONParser();

        try{
            FileReader fileReader = new FileReader("data/classes/" + classType + ".json");

            Object obj = jsonParser.parse(fileReader);
            JSONObject json = (JSONObject) obj;

            this.classType = (String) json.get("name");
            this.hitDie = (long) json.get("hit_die");

            proficiencyList = new ArrayList<String>();

            JSONArray skillProficiencyArray = (JSONArray) json.get("proficiency_choices");
            skillProficiencyArray.forEach(skillProficiencyKind -> {

                long numberOfProficiencies = (long) ((JSONObject)skillProficiencyKind).get("choose");
                this.setNumberOfProficiencies(numberOfProficiencies);

                JSONArray skillProficiencyType = (JSONArray) ((JSONObject)skillProficiencyKind).get("from");

                skillProficiencyType.forEach(skillProficiencyName -> {

                    String proficiency = (String) ((JSONObject) skillProficiencyName).get("name");
                    String proficiencyInstance = setSkillProficiency(proficiency);

                    proficiencyList.add(proficiencyInstance);
                });
            });

            JSONArray savingThrowArray = (JSONArray) json.get("saving_throws");
            savingThrowArray.forEach(proficiencySaveKind -> {
                String savingThrowProficiency = (String) ((JSONObject) proficiencySaveKind).get("name");
                proficiencyList.add(savingThrowProficiency);

            });

            JSONArray armorAndWeaponArray = (JSONArray) json.get("proficiencies");
            armorAndWeaponArray.forEach(proficiencyItemKind ->{
                String itemProficiency = (String) ((JSONObject)proficiencyItemKind).get("name");
                proficiencyList.add(itemProficiency);

            });

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public long getHitDie() {
        return hitDie;
    }

    public void setHitDie(long hitDie) {
        this.hitDie = hitDie;
    }

    public int getAttackBonus() {
        return attackBonus;
    }

    public void setAttackBonus(int attackBonus) {
        this.attackBonus = attackBonus;
    }
}
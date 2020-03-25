import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;

public class BattleClass {
    private String classType = "";
    private long hitDie;
    private String skillProficiency;
    private String armorProficiency;
    private String weaponProficiency;
    private String savingThrowProficiency;
    private int attackBonus;

    private String proficiency;
    private ArrayList<String> proficiencyList;

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

        this.proficiency = trimmedText;

        return trimmedText;
    }

    public boolean proficiencyInClass(String proficiencyInstance){
        return proficiencyList.contains(proficiencyInstance);
    }

    BattleClass(){}

    BattleClass(String classType){
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

            JSONArray skillProficiencyArray = (JSONArray) json.get("proficiency_choices");
            skillProficiencyArray.forEach(skillProficiencyKind -> {

                JSONArray skillProficiencyType = (JSONArray) ((JSONObject)skillProficiencyKind).get("from");

                proficiencyList = new ArrayList<String>();

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

    public String getArmorProficiency() {
        return armorProficiency;
    }

    public void setArmorProficiency(String armorProficiency) {
        this.armorProficiency = armorProficiency;
    }

    public String getWeaponProficiency() {
        return weaponProficiency;
    }

    public void setWeaponProficiency(String weaponProficiency) {
        this.weaponProficiency = weaponProficiency;
    }

    public String getSavingThrowProficiency() {
        return savingThrowProficiency;
    }

    public void setSavingThrowProficiency(String savingThrowProficiency) {
        this.savingThrowProficiency = savingThrowProficiency;
    }

    public int getAttackBonus() {
        return attackBonus;
    }

    public void setAttackBonus(int attackBonus) {
        this.attackBonus = attackBonus;
    }
}

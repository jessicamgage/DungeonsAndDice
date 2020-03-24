import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;

public class BattleClass {
    private String classType = "";
    private Dice hitDice;
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
        boolean isPresent = proficiencyList.contains(proficiencyInstance);

        return isPresent;
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

            JSONArray proficiencyArray = (JSONArray) json.get("proficiency_choices");
            proficiencyArray.forEach(proficiencyKind -> {

                JSONArray proficiencyType = (JSONArray) ((JSONObject)proficiencyKind).get("from");
                proficiencyType.forEach(proficiencyName -> {

                    proficiencyList = new ArrayList<String>();

                    String proficiency = (String) ((JSONObject) proficiencyName).get("name");
                    String proficiencyInstance = setSkillProficiency(proficiency);

                    proficiencyList.add(proficiencyInstance);
                });
            });

        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public Dice getHitDice() {
        return hitDice;
    }

    public void setHitDice(Dice hitDice) {
        this.hitDice = hitDice;
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

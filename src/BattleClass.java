import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.Random;

public class BattleClass {
    private String classType = "";
    private Dice hitDice;
    private String skillProficiency;
    private String armorProficiency;
    private String weaponProficiency;
    private String savingThrowProficiency;
    private int attackBonus;


    public Dice getHitDice() {
        return hitDice;
    }

    public void setHitDice(Dice hitDice) {
        this.hitDice = hitDice;
    }

    public String getSkillProficiency() {
        return skillProficiency;
    }

    public void setSkillProficiency(String skillProficiency) {
        this.skillProficiency = skillProficiency;
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


    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
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

            JSONArray proficiencyChoices = (JSONArray) json.get("proficiency_choices");
            proficiencyChoices.forEach(proficiency -> {
                JSONArray proficiencyOptions = (JSONArray) ((JSONObject) proficiency).get("from");
                proficiencyOptions.forEach(skillProficiency ->{
                    skillProficiency = ((JSONObject)skillProficiency).get("name");
                    long numberOfOptions = (long) ((JSONObject) proficiency).get("choose");

                    do{
                        Random proficiencyRandomizer = new Random();
                        int proficiencySelectionNumber = proficiencyRandomizer.nextInt(proficiencyOptions.size());

                        String proficiencySelected = proficiencyOptions.get(proficiencySelectionNumber).toString();

                        this.setSkillProficiency(proficiencySelected);

                        numberOfOptions--;

                    }while(numberOfOptions > 0);

                });

            });

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

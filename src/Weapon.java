import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class Weapon extends Item {
    private String weapon;
    private String damageType;
    private Dice damageRange;
    private long damageBonus;

    private long normalWeaponRange;
    private long longWeaponRange;

    public long getNormalWeaponRange() {
        return normalWeaponRange;
    }

    public void setNormalWeaponRange(long normalWeaponRange) {
        this.normalWeaponRange = normalWeaponRange;
    }

    public long getLongWeaponRange() {
        return longWeaponRange;
    }

    public void setLongWeaponRange(long longWeaponRange) {
        this.longWeaponRange = longWeaponRange;
    }


    public long getDamageBonus() {
        return damageBonus;
    }

    public void setDamageBonus(long damageBonus) {
        this.damageBonus = damageBonus;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

    public Dice getDamageRange() {
        return damageRange;
    }

    public void setDamageRange(Dice damageRange) {
        this.damageRange = damageRange;
    }

    Weapon(){}

    Weapon(String weapon){
        this.weapon = weapon;
    }

    public void LoadWeapon(String weapon) throws DiceFormatException{
        JSONParser jsonParser = new JSONParser();

        try{
            FileReader fileReader = new FileReader("data/items/weapons/" + weapon + ".json");
            Object obj = jsonParser.parse(fileReader);
            JSONObject json = (JSONObject) obj;

            this.weapon = (String) ((JSONObject) obj).get("name");

            JSONObject info = (JSONObject) json.get("damage");
            String damageRange = (String) info.get("damage_dice");

            try {
                this.damageRange = new Dice(damageRange);
            } catch (DiceFormatException e) {
                e.printStackTrace();
            }

            this.damageBonus = (long) info.get("damage_bonus");

            JSONObject damageType = (JSONObject) info.get("damage_type");
            this.damageType = (String) damageType.get("name");

            JSONObject weaponRange = (JSONObject) json.get("range");
            this.normalWeaponRange = (long) weaponRange.get("normal");

            try{
                this.longWeaponRange = (long) weaponRange.get("long");
            }catch(NullPointerException e){
                System.out.println("This weapon does not have a long range.");
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}


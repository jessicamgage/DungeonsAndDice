import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class Weapon extends Item {
    private String weapon;
    private int dealtDamage;

    public int WeaponAttack(String weapon) throws DiceFormatException{
        JSONParser jsonParser = new JSONParser();

        //build similar to Load function in Race, remove dependencies on Character info and test to see if loads
        //properly, like with dragonborn and gnome instances

        try{
            FileReader fileReader = new FileReader("data/items/" + weapon + ".json");

            Object obj = jsonParser.parse(fileReader);
            JSONObject json = (JSONObject) obj;

            this.weapon = (String) json.get("name");

            JSONArray damages = (JSONArray) json.get("damage");
            damages.forEach(damage -> {
                        String damageType = (String) ((JSONObject) damage).get("damage_type");
                        String damageRange = (String) ((JSONObject) damage).get("damage_dice");

                        try {
                            Character attackingCharacter = new Character();
                            attackingCharacter.DealDamage(damageRange);

                            dealtDamage = attackingCharacter.getDealtDamage();

                        } catch (DiceFormatException e) {
                            e.printStackTrace();
                        }
                    }
            );

        }catch(Exception e){
            e.printStackTrace();
        }

        return dealtDamage;
    }
}

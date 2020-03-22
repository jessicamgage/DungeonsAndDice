import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class Ammunition extends Item{
    private String ammoType;

    public String getAmmoType() {
        return ammoType;
    }

    public void setAmmoType(String ammoType) {
        this.ammoType = ammoType;
    }

    public void LoadAmmunition(String ammoType){
        JSONParser jsonParser = new JSONParser();

        try{
            FileReader fileReader = new FileReader("data/items/ammunition/" + ammoType + ".json");
            Object obj = jsonParser.parse(fileReader);
            JSONObject jsonObject = (JSONObject) obj;

            this.ammoType = ammoType;

            Load("ammunition", getAmmoType());

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

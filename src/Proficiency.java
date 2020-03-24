import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.Random;

public class Proficiency {
    private String proficiencyType;

    Proficiency(){}

    public String getProficiency() {
        return proficiencyType;
    }

    public void setProficiency(String proficiencyType) {
        String trimmedText = proficiencyType.replaceAll("Skill: ", "");

        this.proficiencyType = trimmedText;
    }

    public void Load(String proficiencyType) {
        JSONParser jsonParser = new JSONParser();

        try {
            FileReader fileReader = new FileReader("data/proficiencies/skills/" + proficiencyType + ".json");

            Object obj = jsonParser.parse(fileReader);
            JSONObject json = (JSONObject) obj;

            proficiencyType = (String) json.get("name");
            setProficiency(proficiencyType);

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}

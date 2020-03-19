import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class Item {
    private long weight;
    private long costQuantity;
    private String costType;
    private String itemType;
    private String type;
    private String itemDirectory;

    public String getItemDirectory() {
        return itemDirectory;
    }

    public void setItemDirectory(String itemDirectory) {
        this.itemDirectory = itemDirectory;
    }

    public String getCostType() {
        return costType;
    }

    public void setCostType(String costType) {
        this.costType = costType;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public long getCost() {
        return costQuantity;
    }

    public void setCost(long costQuantity) {
        this.costQuantity = costQuantity;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void LoadItem(String itemDirectory, String itemType){
        JSONParser jsonParser = new JSONParser();

        try{
            FileReader fileReader = new FileReader("data/items/" + itemDirectory + "/" + itemType + ".json");
            Object obj = jsonParser.parse(fileReader);
            JSONObject jsonObject = (JSONObject) obj;

            this.itemType = (String) ((JSONObject) obj).get("name");

            JSONObject cost = (JSONObject) jsonObject.get("cost");
            this.costQuantity = (long) cost.get("quantity");
            this.costType = (String) cost.get("unit");

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

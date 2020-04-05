import java.util.Random;

public class Environment {
    private boolean isDim; //build so that all creatures can see in dim light, but attacks more than 10 feet away w/ DA
    private boolean isDark; //build so that creatures who do not have darkvision need a light to do anything but move (difficult terrain)
    private boolean isEnvironmentallyDim;
    private boolean isEnvironmentallyDark;
    private String terrain;
    private boolean isDifficultTerrain;

    public boolean isDim(Character character) {
        return this.isDim;
    }

    public void setDim(Character character, boolean dim) {
        this.isDim = dim;
    }

    public boolean isEnvironmentallyDim() {
        return isEnvironmentallyDim;
    }

    public void setEnvironmentallyDim(boolean dim){
        isEnvironmentallyDim = dim;
    }

    public boolean isDark(Character character) {
        return this.isDark;
    }

    public void setDark(Character character, boolean dark) {
        this.isDark = dark;
    }

    public boolean isEnvironmentallyDark() {
        return isEnvironmentallyDark;
    }

    public void setEnvironmentallyDark(boolean dark){
        isEnvironmentallyDark = dark;
    }

    public void dimLight(Character character, Race race){
        setEnvironmentallyDim(true);
        character.setRace(race);

        boolean canSee = race.hasRacialAbility("Darkvision");

        if(!canSee){
            this.setDim(character, true);
            //build so ranged attacks made w/ DA
        }else{
            this.setDim(character, false);
        }
    }

    public void darkLight(Character character, Race race){
        setEnvironmentallyDim(false);
        setEnvironmentallyDark(true);
        character.setRace(race);

        boolean canSee = race.hasRacialAbility("Darkvision");

        if(canSee){
            setDark(character, false);
            setDim(character,true);
        }else{
            this.setDark(character, true);
            this.setDim(character, false);
            this.setDifficultTerrain(true);
        }
    }

    public void magicalDarkness(Character character){
        setDark(character, true); //construct parameter so warlocks can see with Devil Sight
    }

    public String getTerrain(){
        return terrain;
    }

    public void setTerrain(String terrain){
        this.terrain = terrain;

        if(terrain.equalsIgnoreCase("cave")){
            setEnvironmentallyDark(true);
        }
    }

    public boolean isDifficultTerrain() {
        return isDifficultTerrain;
    }

    public void setDifficultTerrain(boolean difficultTerrain) {
        isDifficultTerrain = difficultTerrain;
    }
}

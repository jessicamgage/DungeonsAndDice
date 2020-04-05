import java.util.Random;

public class Environment {
    private boolean isDim; //build so that all creatures can see in dim light, but attacks more than 10 feet away w/ DA
    private boolean isDark; //build so that creatures who do not have darkvision need a light to do anything but move (difficult terrain)
    private String terrain;
    private boolean isDifficultTerrain;

    public boolean isDim() {
        return isDim;
    }

    public void setDim(boolean dim) {
        isDim = dim;
    }

    public boolean isDark() {
        return isDark;
    }

    public void setDark(boolean dark) {
        isDark = dark;
    }

    public void dimLight(Character character, Race race){
        setDim(true);
        character.setRace(race);

        boolean canSee = race.hasRacialAbility("Darkvision");

        if(!canSee){
            //build so ranged attacks made w/ DA
        }else{
            this.setDim(false);
        }
    }

    public void darkLight(Character character, Race race){
        setDim(false);
        setDark(true);
        character.setRace(race);

        boolean canSee = race.hasRacialAbility("Darkvision");

        if(canSee){
            this.setDark(false);
            this.setDim(true);
        }else{
            this.setDifficultTerrain(true);
        }
    }

    public void magicalDarkness(){
        setDark(true);
    }

    public String getTerrain(){
        return terrain;
    }

    public void setTerrain(String terrain){
        this.terrain = terrain;

        if(terrain.equalsIgnoreCase("cave")){
            setDark(true);
        }
    }

    public boolean isDifficultTerrain() {
        return isDifficultTerrain;
    }

    public void setDifficultTerrain(boolean difficultTerrain) {
        isDifficultTerrain = difficultTerrain;
    }
}

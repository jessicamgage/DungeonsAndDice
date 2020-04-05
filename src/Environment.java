public class Environment {
    private boolean isDim;
    private boolean isDark;
    private String terrain;

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

    public boolean darkvisionRequired(Race race){
        return true; //fix
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
}

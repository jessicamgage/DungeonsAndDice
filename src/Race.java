public class Race {
    private String name;
    private Dice strScore;
    private Dice dexScore;
    private Dice conScore;
    private Dice intScore;
    private Dice wisScore;
    private Dice chaScore;

    Race(String name, Dice str, Dice dex, Dice con, Dice intel, Dice wis, Dice cha) throws DiceFormatException {
        this.name = name;

        this.strScore = str;
        this.dexScore = dex;
        this.conScore = con;
        this.intScore = intel;
        this.wisScore = wis;
        this.chaScore = cha;
    }

    Race(String name, Dice str) throws DiceFormatException{
        this.name = name;

        this.strScore = str;
    }

    public String getPlayerRace(){
        return name;
    }

    public void setPlayerRace(String name){
        this.name = name;
    }
}
public class Race {
    private String name;
    private Dice strScore;
    private Dice dexScore;
    private Dice conScore;
    private Dice intScore;
    private Dice wisScore;
    private Dice chaScore;

    Race(String name, Dice strScore, Dice dexScore, Dice conScore, Dice intScore, Dice wisScore, Dice chaScore)
            throws DiceFormatException {
        this.name = name;

        this.strScore = strScore;
        this.dexScore = dexScore;
        this.conScore = conScore;
        this.intScore = intScore;
        this.wisScore = wisScore;
        this.chaScore = chaScore;
    }

    public String getRace(){
        return name;
    }

    public void setRace(String name){
        this.name = name;
    }
}
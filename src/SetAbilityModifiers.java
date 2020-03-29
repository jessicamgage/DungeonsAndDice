public class SetAbilityModifiers {
    private int strScore;
    private int dexScore;
    private int conScore;
    private int intScore;
    private int wisScore;
    private int chaScore;
    private int abilityScoreModifier;

    public int getStrScore() {
        return strScore;
    }

    public void setStrScore(int strScore) {
        this.strScore = strScore;
    }

    public int getDexScore() {
        return dexScore;
    }

    public void setDexScore(int dexScore) {
        this.dexScore = dexScore;
    }

    public int getConScore() {
        return conScore;
    }

    public void setConScore(int conScore) {
        this.conScore = conScore;
    }

    public int getIntScore() {
        return intScore;
    }

    public void setIntScore(int intScore) {
        this.intScore = intScore;
    }

    public int getWisScore() {
        return wisScore;
    }

    public void setWisScore(int wisScore) {
        this.wisScore = wisScore;
    }

    public int getChaScore() {
        return chaScore;
    }

    public void setChaScore(int chaScore) {
        this.chaScore = chaScore;
    }

    public int getAbilityScoreModifier(){
        return abilityScoreModifier;
    }

    public void setAbilityScoreModifier(int score){
        int modifier = 0;

        switch(score){
            case 1:
                modifier = -5;
                break;
            case 2:
            case 3:
                modifier = -4;
                break;
            case 4:
            case 5:
                modifier = -3;
                break;
            case 6:
            case 7:
                modifier = -2;
                break;
            case 8:
            case 9:
                modifier = -1;
                break;
            case 12:
            case 13:
                modifier = 1;
                break;
            case 14:
            case 15:
                modifier = 2;
                break;
            case 16:
            case 17:
                modifier = 3;
                break;
            case 18:
            case 19:
                modifier = 4;
                break;
            case 20:
            case 21:
                modifier = 5;
                break;
        }

        this.abilityScoreModifier = modifier;
    }
}

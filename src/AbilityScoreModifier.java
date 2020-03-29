public class AbilityScoreModifier {
    private int abilityScoreModifier;

    public int getAbilityScoreModifier(){
        return abilityScoreModifier;
    }

    public int setAbilityScoreModifier(int score){
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
            case 22:
            case 23:
                modifier = 6;
                break;
            case 24:
            case 25:
                modifier = 7;
                break;
            case 26:
            case 27:
                modifier = 8;
                break;
            case 28:
            case 29:
                modifier = 9;
                break;
            case 30:
                modifier = 10;
        }

        this.abilityScoreModifier = modifier;
        return modifier;
    }
}

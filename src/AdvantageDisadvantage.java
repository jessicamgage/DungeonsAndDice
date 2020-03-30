import java.util.Random;

public class AdvantageDisadvantage {
    private int num1;
    private int num2;

    public int getNum1() {
        return num1;
    }

    public int getNum2() {
        return num2;
    }

    public int randomNumberGenerator(int bound){
        Random rand = new Random();
        int numberCreated = rand.nextInt(bound);

        return numberCreated;
    }

    public int rollWithDisadvantage(){
        int num1 = randomNumberGenerator(21);
        int num2 = randomNumberGenerator(21);

        return Math.min(num1, num2);
    }

    public int rollWithAdvantage(){
        int num1 = randomNumberGenerator(21);
        int num2 = randomNumberGenerator(21);

        return Math.max(num1, num2);
    }
}

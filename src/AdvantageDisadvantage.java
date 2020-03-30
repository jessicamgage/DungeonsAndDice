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

    public int rollWithDisadvantage() throws Exception{
        int num1 = randomNumberGenerator(new Dice("1d20").roll());
        int num2 = randomNumberGenerator(new Dice("1d20").roll());

        return Math.min(num1, num2);
    }

    public int rollWithAdvantage() throws Exception{
        int num1 = randomNumberGenerator(new Dice("1d20").roll());
        int num2 = randomNumberGenerator(new Dice("1d20").roll());

        return Math.max(num1, num2);
    }
}

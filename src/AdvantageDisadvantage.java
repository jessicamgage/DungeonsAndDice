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

    public int rollWithDisadvantage() throws Exception{
        int num1 = new Dice("1d20").roll();
        int num2 = new Dice("1d20").roll();

        return Math.min(num1, num2);
    }

    public int rollWithAdvantage() throws Exception{
        int num1 = new Dice("1d20").roll();
        int num2 = new Dice("1d20").roll();

        return Math.max(num1, num2);
    }
}

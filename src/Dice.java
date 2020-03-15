import java.util.regex.*;
import java.util.Random;

public class Dice {
    static String text = "";
    static int totalNumber = 0;

    Dice(String text) {
        String dicePattern = "(\\d+)([dD])(\\d+)([+-]\\d+)";
        String dicePatternWithoutMod = "(\\d+)([dD])(\\d+)";

        Pattern patternWithMod = Pattern.compile(dicePattern);
        Matcher matcherWithMod = patternWithMod.matcher(text.replaceAll("\\s+",""));

        Pattern patternWithoutMod = Pattern.compile(dicePatternWithoutMod);
        Matcher matcherWithoutMod = patternWithoutMod.matcher(text.replaceAll("\\s+",""));

        if(matcherWithMod.find()){
            System.out.println("Dice structure: " + matcherWithMod.group(0));
            System.out.println("Quantity: " + matcherWithMod.group(1));
            System.out.println("Sides: " + matcherWithMod.group(3));
            System.out.println("Modifier: " + matcherWithMod.group(4));

            Integer diceQuantity = Integer.parseInt(matcherWithMod.group(1));
            Integer diceType = Integer.parseInt(matcherWithMod.group(3));
            Integer diceModifier = Integer.parseInt(matcherWithMod.group(4));

            while(diceQuantity > 0){
                RollNumberOfDice(diceType);
                diceQuantity--;

            }

            totalNumber += diceModifier;

        }else if(matcherWithoutMod.find()){
            System.out.println("Dice Structure: " + matcherWithoutMod.group(0));
            System.out.println("Quantity: " + matcherWithoutMod.group(1));
            System.out.println("Sides: " + matcherWithoutMod.group(3));

            Integer modlessDiceQuantity = Integer.parseInt(matcherWithoutMod.group(1));
            Integer modlessDiceType = Integer.parseInt(matcherWithoutMod.group(3));

            while(modlessDiceQuantity > 0){
                RollNumberOfDice(modlessDiceType);
                modlessDiceQuantity--;

                System.out.println(totalNumber);
            }
        }else{
            System.out.println("Invalid dice syntax.");
        }
    }

    public static void main(String[] args) {
        Dice dice = new Dice("2d4 + 5");
        Dice dice2 = new Dice("2d4+ 5");

        System.out.println(dice);
        System.out.println(dice2);
    }

    public int RollNumberOfDice(int diceType){
        Random diceRollRandomizer = new Random();
        int diceRollNumber = diceRollRandomizer.nextInt(diceType);

        diceRollNumber += 1;

        totalNumber += diceRollNumber;

        return totalNumber;
    }
}

import java.util.regex.*;
import java.util.Random;

public class Dice {
    private String text = "";
    private int quantity = 0;
    private int sides = 0;
    private int modifier = 0;
    private long seed;
    private Random rng;

    private void setSeed(long seed) {
        this.seed = seed;
        rng = new Random(seed);
    }

    private void setSeed() {
        this.seed = System.currentTimeMillis();
        rng = new Random(seed);
    }

    public String getText() {
        return text;
    }

    private void updateText() {
        this.text = this.quantity + "d" + this.sides;
        if (this.modifier > 0) {
            this.text += "+" + this.modifier;
        } else if (this.modifier < 0) {
            this.text += this.modifier;
        }
    }

    public void setText(String text) throws DiceFormatException {
        String trimmedText = text.replaceAll("\\s+", "");
        String dicePattern = "^(\\d+)?[dD](\\d+)([+-]\\d+)?$";

        Pattern pattern = Pattern.compile(dicePattern);
        Matcher matcher = pattern.matcher(trimmedText);

        if (matcher.find()) {
            // Quantity is optional, initializes to 1 by default.
            if (matcher.group(1) != null) {
                this.quantity = Integer.parseInt(matcher.group(1));
            } else {
                this.quantity = 1;
            }

            this.sides = Integer.parseInt(matcher.group(2));

            //Modifier is optional, initializes to 0 by default.
            if (matcher.group(3) != null) {
                this.modifier = Integer.parseInt(matcher.group(3));
            } else {
                this.modifier = 0;
            }

            this.updateText();

        } else {
            throw new DiceFormatException("Invalid dice format.");
        }

    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.updateText();
    }

    public int getSides() {
        return sides;
    }

    public void setSides(int sides) {
        this.sides = sides;
        this.updateText();
    }

    public int getModifier() {
        return modifier;
    }

    public void setModifier(int modifier) {
        this.modifier = modifier;
        this.updateText();
    }

    Dice() {
        this.setSeed();
    }

    Dice(long seed) {
        this.setSeed(seed);
    }

    Dice(String text) throws DiceFormatException {
        this.setSeed();
        this.setText(text);
    }

    Dice(String text, long seed) throws DiceFormatException {
        this.setSeed(seed);
        this.setText(text);
    }

    int roll() {
        int sum = 0;
        for (int i = 0; i < this.quantity; i++) {
            sum += this.rng.nextInt(this.sides) + 1;
        }

        return sum + this.modifier;
    }
}
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DiceTest {
    @Test
    public void verifyFormatValid() throws DiceFormatException{
        Dice d2 = new Dice("d2");
        assertEquals(d2.getQuantity(), 1);
        assertEquals(d2.getModifier(), 0);
        assertEquals(d2.getSides(), 2);
        assertEquals(d2.getText(), "1d2");

        Dice oneDSixPlusOne = new Dice("1d6 + 1");
        assertEquals(oneDSixPlusOne.getQuantity(), 1);
        assertEquals(oneDSixPlusOne.getSides(), 6);
        assertEquals(oneDSixPlusOne.getModifier(), 1);
        assertEquals(oneDSixPlusOne.getText(), "1d6+1");

        Dice oneDTwenty = new Dice("1d20");
        assertEquals(oneDTwenty.getQuantity(), 1);
        assertEquals(oneDTwenty.getSides(), 20);
        assertEquals(oneDTwenty.getModifier(), 0);
        assertEquals(oneDTwenty.getText(), "1d20");

        Dice oneDEightMinus4 = new Dice("1D    8 - 4    ");
        assertEquals(oneDEightMinus4.getQuantity(), 1);
        assertEquals(oneDEightMinus4.getSides(), 8);
        assertEquals(oneDEightMinus4.getModifier(), -4);
        assertEquals(oneDEightMinus4.getText(), "1d8-4");
    }

    @Test
    public void verifyFormatXdYPlusZ2(){
        assertThrows(DiceFormatException.class, () -> new Dice("1d6x1"));
        assertThrows(DiceFormatException.class, () -> new Dice("1dC+1"));
        assertThrows(DiceFormatException.class, () -> new Dice("5"));
        assertThrows(DiceFormatException.class, () -> new Dice("5X2"));
    }

    @Test
    public void verifyRandomD6() throws DiceFormatException{
        Dice d6 = new Dice("d6", 0);

        int numberOfRolls = 100;

        for(int i = 0; i < numberOfRolls; i++){
            int roll = d6.roll();
            assertTrue(roll>0 && roll<7);
        }

    }
}
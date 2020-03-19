import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestWeapon {
    @Test
    public void testWeaponInfo() throws DiceFormatException{
        Weapon javelin = new Weapon();
        javelin.LoadWeapon("javelin");

        assertEquals(javelin.getWeapon(), "Javelin");
        assertEquals(javelin.getDamageRange().getText(), "1d6");
        assertEquals(javelin.getDamageType(), "Piercing");
        assertEquals(javelin.getNormalWeaponRange(), 5);

        assertEquals(javelin.getCost(), 5);
        assertEquals(javelin.getCostType(), "sp");

        Weapon longbow = new Weapon();
        longbow.LoadWeapon("longbow");

        assertEquals(longbow.getNormalWeaponRange(), 150);
        assertEquals(longbow.getLongWeaponRange(), 600);
    }

    @Test
    public void testWeaponFailure() throws DiceFormatException{
        Weapon longbow = new Weapon();
        longbow.LoadWeapon("longbow");

        assertNotEquals(longbow.getDamageRange().getText(), "1d10");
        assertNotEquals(longbow.getDamageType(), "slashing");
    }

}

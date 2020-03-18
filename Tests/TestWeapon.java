import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestWeapon {
    @Test
    public void testWeaponRange() throws DiceFormatException{

        Weapon greatsword = new Weapon();

        int numberOfAttacks = 50;

        for(int i = 0; i < numberOfAttacks; i++){
            int dealtDamage = greatsword.WeaponAttack("greatsword");
            assertTrue(1 < dealtDamage && dealtDamage < 13);
        }

        Weapon javelin = new Weapon();

        int numberOfJavAttacks = 10;

        for(int i = 0; i < numberOfJavAttacks; i++){
            int dealtDamage = javelin.WeaponAttack("javelin");
            assertTrue(0 < dealtDamage && dealtDamage < 7);
        }
    }

}

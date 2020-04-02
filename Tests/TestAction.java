import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestAction {
    @Test
    public void verifyTakenDamage() throws Exception{
        Character boblinTheGoblin = new Character();
        Character mindflayer = new Character();
        Weapon javelin = new Weapon();

        javelin.setItemDirectory("weapons");
        javelin.Load("weapons", "javelin");
        boblinTheGoblin.addToInventory(javelin, boblinTheGoblin);
        boblinTheGoblin.setHeldItem(javelin);

        mindflayer.setHitPoints(20);
        mindflayer.setArmorClass(1);
        boblinTheGoblin.setHitPoints(10);

        Action attacking = new Action();
        attacking.attack(boblinTheGoblin, javelin, mindflayer);
        attacking.attack(boblinTheGoblin, javelin, mindflayer);

        assertEquals(10, boblinTheGoblin.getHitPoints());
        assertFalse(mindflayer.getHitPoints() == 20);
    }
}

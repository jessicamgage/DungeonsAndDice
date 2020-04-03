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

        //using the javelin still prints 'weapon does not have long range' when normal attack is being made.
        //wrap weapon attack type into if else block so that this is only printed in the circumstance that a long attack
        //has been attempted

        mindflayer.setHitPoints(20);
        mindflayer.setArmorClass(1);
        boblinTheGoblin.setHitPoints(10);

        Action attacking = new Action();
        attacking.attack(boblinTheGoblin, javelin, mindflayer);
        attacking.attack(boblinTheGoblin, javelin, mindflayer);

        assertEquals(10, boblinTheGoblin.getHitPoints());
        assertFalse(mindflayer.getHitPoints() == 20);
    }

    @Test
    public void verifyWalkAndDash() throws Exception{
        Race gnome = new Race();
        Character bobButAGnome = new Character();

        Race human = new Race();
        Character smedbacButHuman = new Character();

        Action dash = new Action();
        Action stroll = new Action();

        bobButAGnome.setRace(gnome);
        gnome.Load("gnome");

        smedbacButHuman.setRace(human);
        human.Load("human");

        assertThrows(Exception.class, () -> dash.dash(bobButAGnome, 60));
        assertThrows(Exception.class, () -> stroll.walk(bobButAGnome, 30));
        assertEquals(dash.dash(bobButAGnome, 40), 40);
        assertEquals(stroll.walk(bobButAGnome, 5), 5);
        assertEquals(bobButAGnome.move(100), 100);

        assertThrows(Exception.class, () -> dash.dash(smedbacButHuman, 61));
        assertThrows(Exception.class, () -> stroll.walk(smedbacButHuman, 35));
        assertEquals(dash.dash(smedbacButHuman, 60), 60);
        assertEquals(stroll.walk(smedbacButHuman, 30), 30);

    }
}
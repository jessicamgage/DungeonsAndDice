import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;


public class TestEnvironment {
    @Test
    public void verifyDarkness(){
        Environment murkyCave = new Environment();
        murkyCave.setDim(true);

        assertTrue(murkyCave.isDim());
        assertFalse(murkyCave.isDark());

        Environment underdark = new Environment();
        underdark.setDark(true);

        assertFalse(underdark.isDim());
        assertTrue(underdark.isDark());
    }

    @Test
    public void verifyDarkvision() throws Exception{
        Environment dimCave = new Environment();
        dimCave.setDark(true);

        Character tieflingMan = new Character();
        Race tiefling = new Race();

        tiefling.Load("tiefling");
        tieflingMan.setRace(tiefling);

        Character humanMan = new Character();
        Race human = new Race();

        human.Load("human");
        humanMan.setRace(human);

        dimCave.dimLight(tieflingMan, tiefling); //find a way to build method so that dimness is a result of race and
        assertFalse(dimCave.isDim()); //does not have to be reset with every character passed

        dimCave.dimLight(humanMan, human);
        assertTrue(dimCave.isDim());

        Environment darkCave = new Environment();
        darkCave.setDark(true);

        assertFalse(darkCave.isDim());

        darkCave.darkLight(tieflingMan, tiefling);
        assertTrue(darkCave.isDim());
        assertFalse(darkCave.isDark());

        darkCave.darkLight(humanMan, human);
        assertFalse(darkCave.isDim());
        assertTrue(darkCave.isDark());
    }
}

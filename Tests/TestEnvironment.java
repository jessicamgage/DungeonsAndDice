import org.junit.jupiter.api.Test;
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

        dimCave.dimLight(tieflingMan, tiefling);
        assertFalse(dimCave.isDim(tieflingMan));

        dimCave.dimLight(humanMan, human);
        assertTrue(dimCave.isDim(humanMan));

        Environment darkCave = new Environment();
        darkCave.setDark(true);

        assertFalse(darkCave.isDim());

        darkCave.darkLight(tieflingMan, tiefling);
        assertTrue(darkCave.isDim(tieflingMan));
        assertFalse(darkCave.isDark(tieflingMan));

        darkCave.darkLight(humanMan, human);
        assertFalse(darkCave.isDim(humanMan));
        assertTrue(darkCave.isDark(humanMan));
    }
}

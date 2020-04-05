import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestEnvironment {
    @Test
    public void verifyDarkness(){
        Environment murkyCave = new Environment();
        murkyCave.setEnvironmentallyDim(true);

        assertTrue(murkyCave.isEnvironmentallyDim());
        assertFalse(murkyCave.isEnvironmentallyDark());

        Environment underdark = new Environment();
        underdark.setEnvironmentallyDark(true);

        assertFalse(underdark.isEnvironmentallyDim());
        assertTrue(underdark.isEnvironmentallyDark());
    }

    @Test
    public void verifyDarkvision() throws Exception{
        Environment dimCave = new Environment();
        dimCave.setEnvironmentallyDim(true);

        Character tieflingMan = new Character();
        Race tiefling = new Race();

        tiefling.Load("tiefling");
        tieflingMan.setRace(tiefling);

        Character humanMan = new Character();
        Race human = new Race();

        human.Load("human");
        humanMan.setRace(human);

        assertTrue(dimCave.isEnvironmentallyDim());

        dimCave.dimLight(tieflingMan, tiefling);
        assertFalse(dimCave.isDim(tieflingMan));

        dimCave.dimLight(humanMan, human);
        assertTrue(dimCave.isDim(humanMan));

        Environment darkCave = new Environment();
        darkCave.setEnvironmentallyDark(true);

        assertFalse(darkCave.isEnvironmentallyDim());
        assertTrue(darkCave.isEnvironmentallyDark());

        darkCave.darkLight(tieflingMan, tiefling);
        assertTrue(darkCave.isDim(tieflingMan));
        assertFalse(darkCave.isDark(tieflingMan));

        darkCave.darkLight(humanMan, human);
        assertFalse(darkCave.isDim(humanMan));
        assertTrue(darkCave.isDark(humanMan));
    }

    @Test
    public void verifyMagicalDarkness(){
        Environment darknessAOE = new Environment();
        Character tieflingWithoutDevilSight = new Character();

        darknessAOE.magicalDarkness(tieflingWithoutDevilSight);
        darknessAOE.isDark(tieflingWithoutDevilSight);
    }

    @Test
    public void verifyTerrainGeneration(){
        Environment mountain = new Environment();
        mountain.setChosenTerrain("mountain");

        assertEquals(mountain.getChosenTerrain(), "mountain");
        assertEquals(mountain.getTerrain(), "mountain");

        Environment randomlyGeneratedArea = new Environment();
        String chosenArea = mountain.terrainRandomizer();

        assertNotEquals(null, chosenArea);
    }
}
